package com.alonedroid.smartmemo.feature.memo.input;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.alonedroid.smartmemo.R;
import com.alonedroid.smartmemo.ui.SmInputView;

public class SmMemoInputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SmInputView v = new SmInputView(this);
        v.setBackgroundColor(ContextCompat.getColor(this, R.color.thin_white));
        setContentView(v);
    }
}
