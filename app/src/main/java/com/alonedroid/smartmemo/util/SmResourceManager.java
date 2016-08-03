package com.alonedroid.smartmemo.util;

import android.content.res.TypedArray;

import com.alonedroid.smartmemo.R;
import com.alonedroid.smartmemo.SmApplication;

public class SmResourceManager {

    public static String[] getMainLeftMenu() {
        return SmApplication.resources.getStringArray(R.array.main_left_menu);
    }

    public static TypedArray getMainMenuColor() {
        return SmApplication.resources.obtainTypedArray(R.array.main_left_menu_color);
    }
}
