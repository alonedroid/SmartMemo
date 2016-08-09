package com.alonedroid.smartmemo.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.alonedroid.smartmemo.R;
import com.alonedroid.smartmemo.databinding.ViewCardBinding;

public class SmCardView extends CardView {

    private ViewCardBinding mBinding;

    public SmCardView(Context context) {
        this(context, null);
    }

    public SmCardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SmCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_card, this, true);
    }

    public void setMemoText(String memo) {
        mBinding.cardMemo.setText(memo);
    }

    public void markDelete(boolean isDelete) {
        if (isDelete) {
            setCardBackgroundColor(ContextCompat.getColor(getContext(), R.color.grey_600));
        } else {
            setCardBackgroundColor(Color.WHITE);
        }
    }
}
