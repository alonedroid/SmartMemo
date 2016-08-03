package com.alonedroid.smartmemo;

import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.alonedroid.smartmemo.databinding.ActivityMainBinding;
import com.alonedroid.smartmemo.util.SmNotification;
import com.alonedroid.smartmemo.util.SmResourceManager;
import com.alonedroid.smartmemo.util.SmViewPlant;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_MAIN_ACTIVITY = 1101;
    private ActivityMainBinding mBinding;
    private TypedArray mMenuColors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SmNotification.notifyInputMemoNotification(getApplicationContext());
    }

    private void init() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        assembleLeftMenu();
    }

    private void assembleLeftMenu() {
        LinearLayout.LayoutParams lp = SmViewPlant.lpMx0(1);
        String[] menuLabels = SmResourceManager.getMainLeftMenu();
        mMenuColors = SmResourceManager.getMainMenuColor();

        for (int i = 0; i < menuLabels.length; i++) {
            mBinding.mainLeftMenu.addView(
                    SmViewPlant.shipMainLeftMenu(menuLabels[i], mMenuColors.getColor(i, Color.CYAN))
                    , lp);
        }
    }
}
