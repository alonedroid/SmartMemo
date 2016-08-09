package com.alonedroid.smartmemo.feature.memo.input;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alonedroid.smartmemo.R;
import com.alonedroid.smartmemo.ui.SmInputView;

public class SmMemoInputFragment extends Fragment {

    public static SmMemoInputFragment newInstance() {
        return new SmMemoInputFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return new SmInputView(getContext());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.getRootView().setBackgroundColor(ContextCompat.getColor(getContext(), R.color.sp_chartreuse_pare_13));
    }
}
