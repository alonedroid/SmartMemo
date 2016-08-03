package com.alonedroid.smartmemo;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.google.firebase.analytics.FirebaseAnalytics;

public class SmApplication extends Application {

    private static final String PREFIX = SmApplication.class.getSimpleName();
    private static FirebaseAnalytics mAnalytics;
    public static Resources resources;
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        mAnalytics = FirebaseAnalytics.getInstance(getApplicationContext());
        context = getApplicationContext();
        resources = getResources();
        sendLog("app", "start");
    }

    public static void sendLog(String key, String value) {
        if (BuildConfig.DEBUG) {
            Log.d("SmartMemo", PREFIX + " key:" + key + " value:" + value);
        } else {
//        Bundle params = new Bundle();
//        params.putString(FirebaseAnalytics.Param.ITEM_ID, "10");
//        params.putString(FirebaseAnalytics.Param.ITEM_NAME, "name");
//        mAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN);
        }
    }

    public static void sendLog() {
        sendLog("method", new Throwable().getStackTrace()[1].getMethodName());
    }
}
