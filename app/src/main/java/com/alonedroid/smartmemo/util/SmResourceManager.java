package com.alonedroid.smartmemo.util;

import android.content.res.TypedArray;

import com.alonedroid.smartmemo.R;
import com.alonedroid.smartmemo.SmApplication;

public class SmResourceManager {

    public static String[] getMainLeftMenu() {
        return SmApplication.resources.getStringArray(R.array.main_left_menu);
    }

    public static String[] getMainRightMenuInput() {
        return SmApplication.resources.getStringArray(R.array.main_right_menu_input);
    }

    public static String[] getMainRightMenuClock() {
        return SmApplication.resources.getStringArray(R.array.main_right_menu_clock);
    }

    public static String[] getMainRightMenuSetting() {
        return SmApplication.resources.getStringArray(R.array.main_right_menu_setting);
    }

    public static TypedArray getMainMenuColor() {
        return SmApplication.resources.obtainTypedArray(R.array.main_left_menu_color);
    }

    public static TypedArray getMainMenuRightColor() {
        return SmApplication.resources.obtainTypedArray(R.array.main_right_menu_color);
    }
}
