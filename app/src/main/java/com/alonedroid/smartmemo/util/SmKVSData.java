package com.alonedroid.smartmemo.util;

import com.alonedroid.smartmemo.SmApplication;

public class SmKVSData {

    private static final String INPUT_MEMO_NOTIFICATION = "inputMemoNotification";

    public static void putInputMemoNotificationFlg(boolean flg) {
        SmApplication.sp.edit()
                .putBoolean(INPUT_MEMO_NOTIFICATION, flg)
                .apply();
    }

    public static boolean getInputMemoNotificationFlg() {
        return SmApplication.sp.getBoolean(INPUT_MEMO_NOTIFICATION, false);
    }
}
