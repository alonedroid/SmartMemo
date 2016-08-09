package com.alonedroid.smartmemo.feature.memo.list;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alonedroid.smartmemo.R;
import com.alonedroid.smartmemo.dao.info.SmMemoDao;
import com.alonedroid.smartmemo.dao.info.SmMemoInfo;
import com.alonedroid.smartmemo.databinding.FragmentMemoListBinding;
import com.alonedroid.smartmemo.ui.SmCardView;
import com.alonedroid.smartmemo.util.SmViewPlant;

import java.util.HashSet;

import rx.android.schedulers.AndroidSchedulers;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

public class SmMemoListFragment extends Fragment {

    private PublishSubject<Long> mSelectId = PublishSubject.create();
    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();
    private FragmentMemoListBinding mBinding;
    private SmMemoDao mMemoDao;
    private HashSet<Long> mDeleteMemoIds = new HashSet<>();

    public static SmMemoListFragment newInstance() {
        return new SmMemoListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_memo_list, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        mMemoDao = new SmMemoDao();
        init();
    }

    @Override
    public void onStop() {
        super.onStop();
        mCompositeSubscription.unsubscribe();
        mMemoDao.removeOnChangeListener();
        mMemoDao.removeMemo(mDeleteMemoIds);
    }

    private void init() {
        setMemoData();
        setOnChangeListener();
    }

    private void setMemoData() {
        mCompositeSubscription.add(mMemoDao.fetchAllMemo()
                .filter(result -> result != null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::addMemo));
    }

    private void setOnChangeListener() {
        mMemoDao.setOnChangeListener(this::reSetMemoData);
    }

    private void reSetMemoData() {
        mBinding.memoListContents.removeAllViews();
        setMemoData();
    }

    private void addMemo(SmMemoInfo info) {
        SmCardView view = SmViewPlant.shipMemoCard(info.getMemo());
        view.setOnLongClickListener(v -> markDeleteMemo(v, info.getId()));
        view.setOnClickListener(v -> mSelectId.onNext(info.getId()));
        mBinding.memoListContents.addView(view, SmViewPlant.lpCard());
    }

    private boolean markDeleteMemo(View v, long id) {
        if (mDeleteMemoIds.contains(id)) {
            unmarkDeleteMemo(v, id);
        } else {
            ((SmCardView) v).markDelete(true);
            mDeleteMemoIds.add(id);
        }

        return true;
    }

    private void unmarkDeleteMemo(View v, long id) {
        ((SmCardView) v).markDelete(false);
        mDeleteMemoIds.remove(id);
    }

    public PublishSubject<Long> getSelectId() {
        return this.mSelectId;
    }
}
