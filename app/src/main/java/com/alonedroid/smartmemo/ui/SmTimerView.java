package com.alonedroid.smartmemo.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.alonedroid.smartmemo.R;
import com.alonedroid.smartmemo.databinding.ViewTimerBinding;
import com.alonedroid.smartmemo.util.SmViewPlant;

import java.util.Locale;

public class SmTimerView extends LinearLayout {

    private int counter;

    private ViewTimerBinding mBinding;

    public SmTimerView(Context context) {
        this(context, null);
    }

    public SmTimerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_timer, this, true);
        int pad = getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin);
        setPadding(pad, pad, pad, pad);
        setOrientation(VERTICAL);
        setLayoutParams(SmViewPlant.lpTimer());
        setBackgroundResource(R.drawable.shape_rounded_corners_primary_white_2dp);
    }

    public void setTimerTime(String time) {
        mBinding.timerTime.setText(time);
    }
}
