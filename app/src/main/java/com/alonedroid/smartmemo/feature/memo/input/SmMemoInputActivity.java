package com.alonedroid.smartmemo.feature.memo.input;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alonedroid.smartmemo.R;
import com.alonedroid.smartmemo.ui.SmInputView;

public class SmMemoInputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new SmInputView(this));
    }
}
