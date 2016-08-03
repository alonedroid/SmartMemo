package com.alonedroid.smartmemo.util;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alonedroid.smartmemo.SmApplication;

public class SmViewPlant {

    public static View shipMainLeftMenu(String text, int color) {
        TextView tv = new TextView(SmApplication.context);
        tv.setTextColor(Color.WHITE);
        tv.setTypeface(Typeface.DEFAULT_BOLD);
        tv.setText(text);
        tv.setBackgroundColor(color);
        tv.setGravity(Gravity.CENTER);

        return tv;
    }

    /**
     * 縦置き用LinearLayout.LayoutParamsを生成して返す
     *
     * @param weight LinearLayoutのlayout_weightにセットする
     * @return LinearLayout.LayoutParams
     */
    public static LinearLayout.LayoutParams lpMx0(int weight) {
        return new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, weight);
    }
}
