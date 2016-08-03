package com.alonedroid.smartmemo.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alonedroid.smartmemo.R;

public class SmInputView extends RelativeLayout {

    private ImageView mSmviDone;
    private EditText mSmviText;

    public SmInputView(Context context) {
        this(context, null);
    }

    public SmInputView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SmInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context);
    }

    private void inflate(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View root = inflater.inflate(R.layout.view_input, this);

        mSmviDone = (ImageView) root.findViewById(R.id.smvi_done);
        mSmviText = (EditText) root.findViewById(R.id.smvi_text);
    }
}
