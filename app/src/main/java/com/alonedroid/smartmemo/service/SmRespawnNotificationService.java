package com.alonedroid.smartmemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.alonedroid.smartmemo.util.SmKVSData;
import com.alonedroid.smartmemo.util.SmNotification;

public class SmRespawnNotificationService extends Service {

    public static final int REQUEST_CODE = 2101;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createShortCut();
        return START_NOT_STICKY;
    }

    private void createShortCut() {
        if (SmKVSData.getInputMemoNotificationFlg()) {
            SmNotification.notifyInputMemoNotification(getApplicationContext());
        }
    }
}
