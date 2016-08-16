package com.alonedroid.smartmemo.service;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.util.HashMap;

public class SmCountUpTimerService extends SmTimerService {

    private static final int MAX_COUNT_DOWN_TIMER = 5;
    private HashMap<Integer, Integer> mTime = new HashMap<>();

    public static void startService(final Context context) {
        new Thread(() -> context.startService(
                new Intent(context, SmCountUpTimerService.class))).start();
    }

    @Override
    protected boolean isUse() {
        return mTime.size() < MAX_COUNT_DOWN_TIMER;
    }

    @Override
    protected int addView() {
        int id = super.addView();
        mTime.put(id, 0);
        return id;
    }

    @Override
    protected void removeTimer(View view) {
        super.removeTimer(view);
        mTime.remove(view.getId());
    }

    @Override
    protected void passedTime() {
        for (Integer id : mTime.keySet()) {
            Integer value = mTime.get(id) + 1;
            setTime(id, value);
            mTime.put(id, value);
        }
    }
}
