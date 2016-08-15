package com.alonedroid.smartmemo.feature.timer;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alonedroid.smartmemo.R;
import com.alonedroid.smartmemo.databinding.FragmentTimerBinding;
import com.alonedroid.smartmemo.service.SmCountUpTimerService;

public class SmTimerFragment extends Fragment {

    FragmentTimerBinding mBinding;

    public static SmTimerFragment newInstance() {
        return new SmTimerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_timer, container, false);
        mBinding.timerCountUp.setOnClickListener(v -> startCountUp());
        return mBinding.getRoot();
    }

    private void startCountUp() {
        SmCountUpTimerService.startService(getContext());
    }
}
