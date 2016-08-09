package com.alonedroid.smartmemo;

import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.alonedroid.smartmemo.databinding.ActivityMainBinding;
import com.alonedroid.smartmemo.feature.memo.detail.SmMemoDetailFragment;
import com.alonedroid.smartmemo.feature.memo.list.SmMemoListFragment;
import com.alonedroid.smartmemo.util.SmNotification;
import com.alonedroid.smartmemo.util.SmResourceManager;
import com.alonedroid.smartmemo.util.SmViewPlant;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_MAIN_ACTIVITY = 1101;
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        test();
    }

    private void init() {
        assembleLeftMenu();
        hideLeftMenuShadow(0);
        setRightContents(0);
    }

    private void assembleLeftMenu() {
        LinearLayout.LayoutParams lp = SmViewPlant.lpMx0(1);
        String[] menuLabels = SmResourceManager.getMainLeftMenu();
        TypedArray menuColors = SmResourceManager.getMainMenuColor();

        for (int i = 0; i < menuLabels.length; i++) {
            final int index = i;
            View view = SmViewPlant.shipMainLeftMenu(menuLabels[i], menuColors.getColor(i, Color.CYAN));
            view.setOnClickListener(v -> clickLeftMenu(index));
            mBinding.mainLeftMenu.addView(view, lp);
        }
    }

    private void clickLeftMenu(int index) {
        hideLeftMenuShadow(index);
    }

    private void hideLeftMenuShadow(int index) {
        mBinding.mainLeftMenuShadow0.setVisibility(View.VISIBLE);
        mBinding.mainLeftMenuShadow1.setVisibility(View.VISIBLE);
        mBinding.mainLeftMenuShadow2.setVisibility(View.VISIBLE);
//        mBinding.mainLeftMenuShadow3.setVisibility(View.VISIBLE);

        switch (index) {
            case 0:
                mBinding.mainLeftMenuShadow0.setVisibility(View.INVISIBLE);
                break;
            case 1:
                mBinding.mainLeftMenuShadow1.setVisibility(View.INVISIBLE);
                break;
            case 2:
                mBinding.mainLeftMenuShadow2.setVisibility(View.INVISIBLE);
                break;
//            case 3:
//                mBinding.mainLeftMenuShadow3.setVisibility(View.INVISIBLE);
//                break;
        }
    }

    private void test() {
        SmNotification.notifyInputMemoNotification(getApplicationContext());
    }

    private void setRightContents(int index) {
        Fragment fragment;
        switch (index) {
            case 1:
            default:
                fragment = getListFragment();
                break;
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_right_menu_contents, fragment)
                .commit();
    }

    private Fragment getListFragment() {
        SmMemoListFragment fragment = SmMemoListFragment.newInstance();
        fragment.getSelectId()
                .subscribe(this::showDetailFragment);

        return fragment;
    }

    private void showDetailFragment(long id) {
        SmMemoDetailFragment.newInstance(id)
                .show(getSupportFragmentManager(), SmMemoDetailFragment.DIALOG_ID);
    }
}
