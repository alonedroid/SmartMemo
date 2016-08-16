package com.alonedroid.smartmemo.service;

import android.content.Context;
import android.content.Intent;

import java.util.HashMap;

public class SmCountDownTimerService extends SmTimerService {

    private static final int MAX_COUNT_DOWN_TIMER = 5;
    private HashMap<Integer, Integer> mTime = new HashMap<>();

    public static void startService(final Context context) {
        new Thread(() -> context.startService(
                new Intent(context, SmCountDownTimerService.class))).start();
    }

    @Override
    protected boolean isUse() {
        return mTime.size() < MAX_COUNT_DOWN_TIMER;
    }

    @Override
    protected void passedTime() {
        setTime(0, 0);
    }
}
