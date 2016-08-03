package com.alonedroid.smartmemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alonedroid.smartmemo.service.SmMemoInputService;
import com.alonedroid.smartmemo.util.SmNotification;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_MAIN_ACTIVITY = 1101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SmNotification.notifyInputMemoNotification(getApplicationContext());
    }
}
