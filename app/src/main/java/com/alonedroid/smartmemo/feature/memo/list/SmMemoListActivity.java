package com.alonedroid.smartmemo.feature.memo.list;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alonedroid.smartmemo.R;
import com.alonedroid.smartmemo.databinding.ActivityMainBinding;

public class SmMemoListActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_memo_list);
        init();
    }

    private void init() {
        
    }
}
