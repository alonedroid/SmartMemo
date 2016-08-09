package com.alonedroid.smartmemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.alonedroid.smartmemo.feature.memo.detail.SmMemoDetailFragment;
import com.alonedroid.smartmemo.feature.memo.list.SmMemoListFragment;
import com.alonedroid.smartmemo.feature.menu.SmMainLeftMenuFragment;
import com.alonedroid.smartmemo.util.SmNotification;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_MAIN_ACTIVITY = 1101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_main);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        test();
    }

    private void init() {
        setLeftContents();
        setRightContents(0);
    }

    private void test() {
        SmNotification.notifyInputMemoNotification(getApplicationContext());
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
        Fragment fragment;
        switch (index) {
            case 1:
            default:
                fragment = getListFragment();
                break;
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_right_menu_contents, fragment)
                .commit();
    }

    private Fragment getListFragment() {
        SmMemoListFragment fragment = SmMemoListFragment.newInstance();
        fragment.getSelectId()
                .subscribe(this::showDetailFragment);

        return fragment;
    }

    private void showDetailFragment(long id) {
        SmMemoDetailFragment.newInstance(id)
                .show(getSupportFragmentManager(), SmMemoDetailFragment.DIALOG_ID);
    }
}
