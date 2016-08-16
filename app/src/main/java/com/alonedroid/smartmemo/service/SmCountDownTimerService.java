
package com.alonedroid.smartmemo.service;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.alonedroid.smartmemo.util.SmResourceManager;

import java.util.HashMap;
import java.util.Set;

public class SmCountDownTimerService extends SmTimerService {

    private static final String EXTRA_COUNT_DOWN_TIME = "extraCountDownTime";
    private static final int MAX_COUNT_DOWN_TIMER = 5;
    private int settingTime;
    private HashMap<Integer, Integer> mTime = new HashMap<>();

    public static void startService(final Context context, int time) {
        final Intent intent = new Intent(context, SmCountDownTimerService.class);
        intent.putExtra(EXTRA_COUNT_DOWN_TIME, time);
        new Thread(() -> context.startService(intent)).start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) return START_NOT_STICKY;

        settingTime = intent.getIntExtra(EXTRA_COUNT_DOWN_TIME, 0);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected int addView() {
        int id = super.addView();
        mTime.put(id, settingTime);
        return id;
    }

    @Override
    protected boolean isUse() {
        return addedViewSize() < MAX_COUNT_DOWN_TIMER;
    }

    @Override
    protected void removeTimer(View view) {
        super.removeTimer(view);
        mTime.remove(view.getId());
    }

    @Override
    protected void passedTime() {
        Set<Integer> keys = mTime.keySet();
        for (Integer id : keys) {
            Integer value = mTime.get(id) - 1;
            if (value > 0) {
                setTime(id, value);
                mTime.put(id, value);
            } else {
                setMessage(id, SmResourceManager.getTimerCompMessage());
            }
        }
    }
}
