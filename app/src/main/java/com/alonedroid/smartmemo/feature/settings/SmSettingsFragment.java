package com.alonedroid.smartmemo.feature.settings;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alonedroid.smartmemo.R;
import com.alonedroid.smartmemo.databinding.FragmentSettingsBinding;
import com.alonedroid.smartmemo.util.SmNotification;

public class SmSettingsFragment extends Fragment{

    FragmentSettingsBinding mBinding;

    public static SmSettingsFragment newInstance() {
        return new SmSettingsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        mBinding.settingsToggle.setOnCheckedChangeListener((v, isChecked) -> createShortCut(isChecked));
    }

    private void createShortCut(boolean isChecked){
        if(isChecked){
            SmNotification.notifyInputMemoNotification(getContext());
        }else{
            SmNotification.cancelInputMemoNotification(getContext());
        }
    }
}
