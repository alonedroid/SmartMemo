package com.alonedroid.smartmemo.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.alonedroid.smartmemo.R;

public class SmCardView extends CardView {

    private ViewDataBinding mBinding;

    public SmCardView(Context context) {
        this(context, null);
    }

    public SmCardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SmCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_card, this, true);
        init();
    }

    private void init() {

    }
}
