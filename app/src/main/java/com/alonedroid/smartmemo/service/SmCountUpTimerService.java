package com.alonedroid.smartmemo.service;

import android.content.Context;
import android.content.Intent;

import com.alonedroid.smartmemo.SmApplication;

public class SmCountUpTimerService extends SmTimerService {

    private int mTime = 0;

    public static void startService(final Context context) {
        new Thread(() -> context.startService(
                new Intent(context, SmCountUpTimerService.class))).start();
    }

    @Override
    protected void passedTime() {
        setTime(++mTime);
    }
}
