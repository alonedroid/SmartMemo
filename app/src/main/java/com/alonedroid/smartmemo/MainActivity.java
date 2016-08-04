package com.alonedroid.smartmemo;

import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alonedroid.smartmemo.dao.info.SmMemoInfo;
import com.alonedroid.smartmemo.databinding.ActivityMainBinding;
import com.alonedroid.smartmemo.feature.memo.list.SmMemoListFragment;
import com.alonedroid.smartmemo.util.SmNotification;
import com.alonedroid.smartmemo.util.SmResourceManager;
import com.alonedroid.smartmemo.util.SmViewPlant;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_MAIN_ACTIVITY = 1101;
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        test();
    }

    private void init() {
        assembleLeftMenu();
        hideLeftMenuShadow(0);
        setRightContents(0);
    }

    private void assembleLeftMenu() {
        LinearLayout.LayoutParams lp = SmViewPlant.lpMx0(1);
        String[] menuLabels = SmResourceManager.getMainLeftMenu();
        TypedArray menuColors = SmResourceManager.getMainMenuColor();

        for (int i = 0; i < menuLabels.length; i++) {
            final int index = i;
            View view = SmViewPlant.shipMainLeftMenu(menuLabels[i], menuColors.getColor(i, Color.CYAN));
            view.setOnClickListener(v -> clickLeftMenu(index));
            mBinding.mainLeftMenu.addView(view, lp);
        }
    }

    private void clickLeftMenu(int index) {
        hideLeftMenuShadow(index);
        select();
    }

    private void hideLeftMenuShadow(int index) {
        mBinding.mainLeftMenuShadow0.setVisibility(View.VISIBLE);
        mBinding.mainLeftMenuShadow1.setVisibility(View.VISIBLE);
        mBinding.mainLeftMenuShadow2.setVisibility(View.VISIBLE);
        mBinding.mainLeftMenuShadow3.setVisibility(View.VISIBLE);

        switch (index) {
            case 0:
                mBinding.mainLeftMenuShadow0.setVisibility(View.INVISIBLE);
                break;
            case 1:
                mBinding.mainLeftMenuShadow1.setVisibility(View.INVISIBLE);
                break;
            case 2:
                mBinding.mainLeftMenuShadow2.setVisibility(View.INVISIBLE);
                break;
            case 3:
                mBinding.mainLeftMenuShadow3.setVisibility(View.INVISIBLE);
                break;
        }
    }

    private void test() {
        SmNotification.notifyInputMemoNotification(getApplicationContext());
        insert();
    }

    private void insert() {
        final Realm realm = Realm.getDefaultInstance();
        try {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    SmMemoInfo memo = new SmMemoInfo();
                    memo.setId(1);
                    memo.setMemo("test");
                    memo.setTag("");
                    memo.setCreateDate(new Date());
                    memo.setUpdateDate(new Date());
                    realm.copyToRealmOrUpdate(memo);

                    memo = new SmMemoInfo();
                    memo.setId(2);
                    memo.setMemo("test2");
                    memo.setTag("");
                    memo.setCreateDate(new Date());
                    memo.setUpdateDate(new Date());
                    realm.copyToRealmOrUpdate(memo);

                    memo = new SmMemoInfo();
                    memo.setId(3);
                    memo.setMemo("test3");
                    memo.setTag("");
                    memo.setCreateDate(new Date());
                    memo.setUpdateDate(new Date());
                    realm.copyToRealmOrUpdate(memo);
                }
            });
        } finally {
            // getしたらcloseする
            realm.close();
        }
    }

    private void select() {
        Realm realm = Realm.getDefaultInstance();

        // Build the query looking at all users:
        RealmQuery<SmMemoInfo> query = realm.where(SmMemoInfo.class);

        // Execute the query:
        RealmResults<SmMemoInfo> result1 = query.findAll();

        Toast.makeText(this, result1.get(0).getMemo() + " だよ", Toast.LENGTH_SHORT).show();

        realm.close();
    }

    private void setRightContents(int index){
        Fragment fragment;
        switch (index) {
            case 1:
            default:
                fragment = SmMemoListFragment.newInstance();
                break;
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_right_menu_contents, fragment)
                .commit();
    }
}
