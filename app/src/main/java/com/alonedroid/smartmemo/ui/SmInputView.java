package com.alonedroid.smartmemo.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.alonedroid.smartmemo.R;
import com.alonedroid.smartmemo.dao.info.SmMemoDao;
import com.alonedroid.smartmemo.databinding.ViewMemoInputBinding;

public class SmInputView extends RelativeLayout {

    private ViewMemoInputBinding mBinding;

    public SmInputView(Context context) {
        this(context, null);
    }

    public SmInputView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SmInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_memo_input, this, true);
        init();
    }

    private void init() {
        setClickListener();
    }

    private void setClickListener() {
        mBinding.memoInputDone.setOnClickListener(v -> saveMemo());
    }

    private void saveMemo() {
        SmMemoDao dao = new SmMemoDao();
        dao.insertMemo(mBinding.memoInputText.getText().toString(), "");
        mBinding.memoInputText.setText("");
    }
}
