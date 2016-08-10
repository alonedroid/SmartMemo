package com.alonedroid.smartmemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.alonedroid.smartmemo.util.SmKVSData;
import com.alonedroid.smartmemo.util.SmNotification;

public class SmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        if (!Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) return;

        if (SmKVSData.getInputMemoNotificationFlg()) {
            SmNotification.notifyInputMemoNotification(context);
        }
    }
}
