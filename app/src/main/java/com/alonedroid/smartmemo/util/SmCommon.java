package com.alonedroid.smartmemo.util;

import java.util.Locale;

public class SmCommon {

    public static String convert00m00s(int seconds) {
        return String.format(Locale.JAPAN, "%1$02d", seconds / 60) + " : "
                + String.format(Locale.JAPAN, "%1$02d", seconds % 60);
    }
}
