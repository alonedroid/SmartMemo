package com.alonedroid.smartmemo.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.alonedroid.smartmemo.R;
import com.alonedroid.smartmemo.dao.info.SmMemoDao;
import com.alonedroid.smartmemo.databinding.ViewMemoInputBinding;
import com.alonedroid.smartmemo.util.SmViewPlant;

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
        setOwnParam();
        setClickListener();
    }

    private void setOwnParam() {
        setBackgroundColor(ContextCompat.getColor(getContext(), R.color.thin_white));
        int pad = getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin);
        setPadding(pad, pad, pad, pad);
        setLayoutParams(SmViewPlant.lpInput());
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
