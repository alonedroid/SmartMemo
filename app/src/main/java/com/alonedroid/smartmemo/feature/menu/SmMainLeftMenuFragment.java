package com.alonedroid.smartmemo.feature.menu;

import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alonedroid.smartmemo.R;
import com.alonedroid.smartmemo.databinding.FragmentMainLeftMenuBinding;
import com.alonedroid.smartmemo.util.SmResourceManager;
import com.alonedroid.smartmemo.util.SmViewPlant;

import rx.subjects.BehaviorSubject;

public class SmMainLeftMenuFragment extends Fragment {

    private FragmentMainLeftMenuBinding mBinding;
    private BehaviorSubject<Integer> mSelectedMenu = BehaviorSubject.create();

    public static SmMainLeftMenuFragment newInstance() {
        return new SmMainLeftMenuFragment();
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_left_menu, container, false);
        assembleLeftMenu();
        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        clickLeftMenu(0);
    }

    private void assembleLeftMenu() {
        LinearLayout.LayoutParams lp = SmViewPlant.lpMx0(1);
        String[] menuLabels = SmResourceManager.getMainLeftMenu();
        TypedArray menuColors = SmResourceManager.getMainMenuColor();

        for (int i = 0; i < menuLabels.length; i++) {
            final int index = i;
            View view = SmViewPlant.shipMainLeftMenu(menuLabels[i], menuColors.getColor(i, Color.CYAN));
            view.setOnClickListener(v -> clickLeftMenu(index));
            mBinding.leftMenuContents.addView(view, lp);
            mBinding.leftMenuShadow.addView(SmViewPlant.shipMainLeftMenuShadow(), lp);
        }
    }

    private void clickLeftMenu(int index) {
        setBackgroundColor(index);
        hideLeftMenuShadow(index);
    }

    private void setBackgroundColor(int index) {
        TypedArray menuColors = SmResourceManager.getMainMenuRightColor();
        mBinding.leftMenuRoot.setBackgroundColor(menuColors.getColor(index, Color.CYAN));
    }

    private void hideLeftMenuShadow(int index) {
        int childCnt = mBinding.leftMenuShadow.getChildCount();
        for (int i = 0; i < childCnt; i++) {
            mBinding.leftMenuShadow.getChildAt(i)
                    .setVisibility(i == index ? View.INVISIBLE : View.VISIBLE);
        }
    }

    public BehaviorSubject<Integer> getSelectedMenu() {
        return mSelectedMenu;
    }
}
