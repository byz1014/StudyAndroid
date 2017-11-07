package com.example.byz.studyandroid;

import com.example.byz.studyandroid.base.BaseActivity;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by byz on 2017/11/7.
 */

public class TestActivity extends BaseActivity {
    @Override
    public int onLayout() {
        return R.layout.layout_test;
    }

    @Override
    public void init() {

    }

    @Override
    @Subscribe
    public void onEventMainThread(String str) {

    }
}
