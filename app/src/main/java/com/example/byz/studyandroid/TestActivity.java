package com.example.byz.studyandroid;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.baselib.base.BaseActivity;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by byz on 2017/11/7.
 */

public class TestActivity extends BaseActivity implements View.OnClickListener {
    RecyclerView rv_body;

    @Override
    public void onClick(View view) {

    }

    @Override
    public int onLayout() {
        return R.layout.layout_test;
    }

    @Override
    public void init() {
        Bundle bundle = getIntent().getBundleExtra("data");
        String title = bundle.getString("title");
        setTitleMessage(title);
        setLeftListener(new OnTitleLeftListener() {
            @Override
            public void onLeft() {
                finish();
            }
        });
        rv_body = (RecyclerView)findViewById(R.id.rv_body);
        rv_body.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    @Subscribe
    public void onEventMainThread(String str) {

    }


}
