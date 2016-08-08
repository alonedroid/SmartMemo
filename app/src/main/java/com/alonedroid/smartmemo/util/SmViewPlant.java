package com.alonedroid.smartmemo.util;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alonedroid.smartmemo.R;
import com.alonedroid.smartmemo.SmApplication;
import com.alonedroid.smartmemo.ui.SmCardView;

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

    public static SmCardView shipMemoCard(String text) {
        SmCardView cv = new SmCardView(SmApplication.context);
        cv.setMemoText(text);
        cv.setCardBackgroundColor(Color.WHITE);
        cv.setCardElevation(SmApplication.resources.getDimension(R.dimen.card_elevation));
        cv.setUseCompatPadding(true);

        return cv;
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

    /**
     * CardView用LinearLayout.LayoutParamsを生成して返す
     *
     * @return LinearLayout.LayoutParams
     */
    public static LinearLayout.LayoutParams lpCard() {
        return new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , SmApplication.resources.getDimensionPixelSize(R.dimen.dimen_space_48dp));
    }
}
