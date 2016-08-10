package com.alonedroid.smartmemo.feature.option;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SmCopyrightDialogFragment extends DialogFragment {

    public static final String DIALOG_ID = "copyright";

    public static SmCopyrightDialogFragment newInstance() {
        return new SmCopyrightDialogFragment();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setMessage(readFile(getContext()))
                .create();
    }

    public String readFile(Context context) {
        final String copyrightFile = "copyright.txt";

        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(context.getResources().getAssets().open(copyrightFile)));

            String lineString;
            StringBuilder stringBuilder = new StringBuilder();
            while ((lineString = bufferedReader.readLine()) != null) {
                stringBuilder.append(lineString);
                stringBuilder.append("\n");
            }
            stringBuilder.append("\n");
            return stringBuilder.toString();
        } catch (Exception e) {
            return "";
        }
    }
}
