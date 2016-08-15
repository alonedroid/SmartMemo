package com.alonedroid.smartmemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.alonedroid.smartmemo.feature.memo.detail.SmMemoDetailDialogFragment;
import com.alonedroid.smartmemo.feature.memo.input.SmMemoInputFragment;
import com.alonedroid.smartmemo.feature.memo.list.SmMemoListFragment;
import com.alonedroid.smartmemo.feature.menu.SmMainLeftMenuFragment;
import com.alonedroid.smartmemo.feature.option.SmCopyrightDialogFragment;
import com.alonedroid.smartmemo.feature.settings.SmSettingsFragment;
import com.alonedroid.smartmemo.feature.timer.SmTimerFragment;
import com.alonedroid.smartmemo.feature.tutorial.SmTutorialModel;
import com.alonedroid.smartmemo.util.SmKVSData;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_MAIN_ACTIVITY = 1101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_main);
        init();
    }

    private void init() {
        setLeftContents();
        setRightContents(0);
        setTutorial();
    }

    private void setLeftContents() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_left_menu_contents, getLeftMenuContents())
                .commit();
    }

    private Fragment getLeftMenuContents() {
        SmMainLeftMenuFragment fragment = SmMainLeftMenuFragment.newInstance();
        fragment.getSelectedMenu().subscribe(this::setRightContents);

        return fragment;
    }

    private void setRightContents(int index) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_right_menu_contents, getRightMenuFragment(index))
                .commit();
    }

    private Fragment getRightMenuFragment(int index) {
        switch (index) {
            case 0:
                return getListFragment();
            case 1:
                return getInputFragment();
            case 2:
                return getTimerFragment();
            case 3:
                return getSettingsFragment();
            default:
                return getListFragment();
        }
    }

    private Fragment getListFragment() {
        SmMemoListFragment fragment = SmMemoListFragment.newInstance();
        fragment.getSelectId()
                .subscribe(this::showDetailFragment);

        return fragment;
    }

    private Fragment getTimerFragment() {
        return SmTimerFragment.newInstance();
    }

    private Fragment getInputFragment() {
        return SmMemoInputFragment.newInstance();
    }

    private Fragment getSettingsFragment() {
        return SmSettingsFragment.newInstance();
    }

    private void showDetailFragment(long id) {
        SmMemoDetailDialogFragment.newInstance(id)
                .show(getSupportFragmentManager(), SmMemoDetailDialogFragment.DIALOG_ID);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings_copyright:
                showCopyrightFragment();
                break;
        }
        return true;
    }

    private void showCopyrightFragment() {
        SmCopyrightDialogFragment.newInstance()
                .show(getSupportFragmentManager(), SmCopyrightDialogFragment.DIALOG_ID);
    }

    private void setTutorial() {
        if (SmKVSData.isFirstTimeTutorial1()) SmTutorialModel.putTutorialMemo1();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
