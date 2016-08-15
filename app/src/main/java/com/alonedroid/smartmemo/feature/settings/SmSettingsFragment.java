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
import com.alonedroid.smartmemo.util.SmKVSData;
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
        mBinding.settingsToggle.setChecked(SmKVSData.getInputMemoNotificationFlg());
        mBinding.settingsToggle.setOnCheckedChangeListener((v, isChecked) -> createShortCut(isChecked));
        return mBinding.getRoot();
    }

    private void createShortCut(boolean isChecked){
        if(isChecked){
            SmNotification.notifyInputMemoNotification(getContext());
        }else{
            SmNotification.cancelInputMemoNotification(getContext());
        }

        SmKVSData.putInputMemoNotificationFlg(isChecked);
    }
}
