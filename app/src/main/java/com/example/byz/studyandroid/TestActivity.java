package com.example.byz.studyandroid;

import android.view.View;

import com.example.byz.studyandroid.base.BaseActivity;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by byz on 2017/11/7.
 */

public class TestActivity extends BaseActivity implements View.OnClickListener {

    @Override
    public void onClick(View view) {

    }

    @Override
    public int onLayout() {
        return R.layout.layout_test;
    }

    @Override
    public void init() {

    }

    @Override @Subscribe
    public void onEventMainThread(String str) {

    }



}
