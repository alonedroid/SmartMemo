package com.alonedroid.smartmemo.feature.memo.detail;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;

import com.alonedroid.smartmemo.R;
import com.alonedroid.smartmemo.dao.info.SmMemoDao;
import com.alonedroid.smartmemo.databinding.DialogMemoDetailBinding;

public class SmMemoDetailFragment extends DialogFragment {

    public static final String DIALOG_ID = "memoDetail";
    private static final String ARG_MEMO_ID = "argMemoId";
    private DialogMemoDetailBinding mBinding;
    private long mMemoId;

    public static SmMemoDetailFragment newInstance(long id) {
        SmMemoDetailFragment fragment = new SmMemoDetailFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_MEMO_ID, id);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mMemoId = getArguments().getLong(ARG_MEMO_ID);
        init();
        return new AlertDialog.Builder(getActivity())
                .setView(mBinding.getRoot())
                .setMessage(R.string.dialog_memo_detail_title)
                .setPositiveButton(R.string.dialog_memo_detail_positive, (dialogInterface, i) -> {
                    new SmMemoDao().updateMemo(mMemoId, mBinding.memoDetailText.getText().toString());
                })
                .setNegativeButton(R.string.dialog_memo_detail_negative, (dialogInterface, i) -> {
                    // non
                })
                .create();
    }

    private void init() {
        initViews();
        initDetail();
    }

    private void initViews() {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_memo_detail, null, false);
    }

    private void initDetail() {
        new SmMemoDao().fetchMemo(mMemoId)
                .subscribe(info -> mBinding.memoDetailText.setText(info.getMemo()));
    }
}
