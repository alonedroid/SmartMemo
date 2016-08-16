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
import com.alonedroid.smartmemo.service.SmCountDownTimerService;
import com.alonedroid.smartmemo.service.SmCountUpTimerService;
import com.alonedroid.smartmemo.util.SmCommon;

import rx.Subscription;
import rx.subjects.BehaviorSubject;

public class SmTimerFragment extends Fragment {

    private Subscription mSubscription;
    private FragmentTimerBinding mBinding;
    private BehaviorSubject<Integer> mTime = BehaviorSubject.create(0);

    public static SmTimerFragment newInstance() {
        return new SmTimerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_timer, container, false);
        mBinding.timerCountDown.setOnClickListener(v -> startCountDown());
        mBinding.timerCountUp.setOnClickListener(v -> startCountUp());
        mBinding.timerAdd10m.setOnClickListener(v -> mTime.onNext(mTime.getValue() + (10 * 60)));
        mBinding.timerAdd1m.setOnClickListener(v -> mTime.onNext(mTime.getValue() + (60)));
        mBinding.timerClear.setOnClickListener(v -> mTime.onNext(0));
        mBinding.timerAdd10s.setOnClickListener(v -> mTime.onNext(mTime.getValue() + (10)));
        mBinding.timerAdd1s.setOnClickListener(v -> mTime.onNext(mTime.getValue() + (1)));
        return mBinding.getRoot();
    }

    private void startCountDown() {
        SmCountDownTimerService.startService(getContext(), mTime.getValue());
        mTime.onNext(0);
    }

    private void startCountUp() {
        SmCountUpTimerService.startService(getContext());
    }


    @Override
    public void onStart() {
        super.onStart();
        mSubscription = mTime.subscribe(this::setTime);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    private void setTime(int second) {
        mBinding.timerCountDownTime.setText(SmCommon.convert00m00s(second));
    }
}
