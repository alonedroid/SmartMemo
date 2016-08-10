package com.alonedroid.smartmemo.util;

import com.alonedroid.smartmemo.SmApplication;

public class SmKVSData {

    private static final String INPUT_MEMO_NOTIFICATION = "inputMemoNotification";
    private static final String TUTORIAL_MEMO_1 = "tutorialMemo1";

    public static void putInputMemoNotificationFlg(boolean flg) {
        SmApplication.sp.edit()
                .putBoolean(INPUT_MEMO_NOTIFICATION, flg)
                .apply();
    }

    public static boolean getInputMemoNotificationFlg() {
        return SmApplication.sp.getBoolean(INPUT_MEMO_NOTIFICATION, false);
    }

    public static boolean isFirstTimeTutorial1() {
        return SmApplication.sp.getBoolean(TUTORIAL_MEMO_1, true);
    }

    public static void putTutorial1Done() {
        SmApplication.sp.edit()
                .putBoolean(TUTORIAL_MEMO_1, false)
                .apply();
    }
}
