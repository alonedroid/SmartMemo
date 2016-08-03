package com.alonedroid.smartmemo.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.WindowManager;

import com.alonedroid.smartmemo.R;

public class SmMemoInputService extends Service {

    private static final int EXTRA_NONE = 0;
    public static final String EXTRA_LAYOUT = "extraLayout";
    public static final int LAYOUT_MEMO = R.layout.view_input;

    public static void startService(final Context context, @LayoutRes int layout) {
        final Intent intent = new Intent(context, SmMemoInputService.class);
        intent.putExtra(EXTRA_LAYOUT, layout);
        new Thread(() -> context.startService(intent)).start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        showView(intent.getIntExtra(EXTRA_LAYOUT, EXTRA_NONE));
        return START_NOT_STICKY;
    }

    private void showView(@LayoutRes int layout) {
        if (EXTRA_NONE == layout) return;

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);

        // Viewを画面上に重ね合わせする
        ((WindowManager) getSystemService(Context.WINDOW_SERVICE))
                .addView(LayoutInflater.from(this).inflate(layout, null), params);
    }
}
