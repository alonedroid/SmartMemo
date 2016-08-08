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
import com.alonedroid.smartmemo.util.SmViewPlant;

import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

public class SmMemoListFragment extends Fragment {

    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();
    private FragmentMemoListBinding mBinding;
    private SmMemoDao mMemoDao;

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
    }

    private void init() {
        mCompositeSubscription.add(mMemoDao.fetchAllMemo()
                .filter(result -> result != null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setDummyData));
    }

    private void setDummyData(SmMemoInfo info) {
        mBinding.memoListContents.addView(SmViewPlant.shipMemoCard(info.getMemo())
                , SmViewPlant.lpCard());
    }
}
