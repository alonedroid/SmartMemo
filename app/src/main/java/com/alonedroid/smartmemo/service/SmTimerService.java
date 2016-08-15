package com.alonedroid.smartmemo.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.alonedroid.smartmemo.ui.SmTimerView;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

abstract public class SmTimerService extends Service {

    private SmTimerView mFloatingView;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mParams;
    private GestureDetector mGestureDetector;
    private Point mSize = new Point();

    abstract protected void passedTime();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        setParams();
        showView();
        startCount();
        return START_NOT_STICKY;
    }

    private void setParams() {
        setOnDoubleTouchListener();
        setParamWindowSize();
        setParamViewParam();
    }

    private void setParamWindowSize() {
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = mWindowManager.getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            display.getSize(mSize);
        } else {
            mSize.x = display.getWidth();
            mSize.y = display.getHeight();
        }
    }

    private void setParamViewParam() {
        mParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN |
                        WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);
    }

    private void showView() {
        mFloatingView = new SmTimerView(getApplicationContext());
        mFloatingView.setOnTouchListener(this::touch);
        mWindowManager.addView(mFloatingView, mParams);
    }

    private boolean touch(View view, MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        if (MotionEvent.ACTION_MOVE != event.getAction()) return false;

        int x = (int) event.getRawX();
        int y = (int) event.getRawY();

        // 中心からの移動量を計算する
        int centerX = x - (mSize.x / 2);
        int centerY = y - (mSize.y / 2);

        // オーバーレイ表示領域を移動量分だけ移動させる
        mParams.x = centerX;
        mParams.y = centerY;

        // 移動した分を更新する
        mWindowManager.updateViewLayout(mFloatingView, mParams);

        return false;
    }

    private void setOnDoubleTouchListener() {
        mGestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                mWindowManager.removeView(mFloatingView);
                stopSelf();
                return super.onDoubleTap(e);
            }
        });
    }

    private void startCount(){
        Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(t -> passedTime());
    }

    protected void setTime(int seconds){
        String time = String.format(Locale.JAPAN, "%1$02d", seconds / 60) + " : "
                + String.format(Locale.JAPAN, "%1$02d", seconds % 60);
        mFloatingView.setTimerTime(time);
    }
}