package com.example.byz.studyandroid;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.byz.studyandroid.adapter.MyViewHolder;
import com.example.byz.studyandroid.adapter.TotalRecycleAdapter;
import com.example.byz.studyandroid.base.BaseActivity;
import com.example.byz.studyandroid.utils.BitmapUtil;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by byz on 2017/11/13.
 */

public class RecycleViewActivity extends BaseActivity {
    RecyclerView rv_list;
    String names[]={"name------","name------","name------","name------","name------","name------","name------"};
    List<String> list;
    @Override
    public int onLayout() {
        return R.layout.layout_recycle;
    }

    @Override
    public void init() {

        Bundle bundle = getIntent().getBundleExtra("data");
        String title = bundle.getString("title");
        setLeftListener(new OnTitleLeftListener() {
            @Override
            public void onLeft() {
                finish();
            }
        });
        setTitleMessage(title);


        rv_list = (RecyclerView)findViewById(R.id.rv_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv_list.setLayoutManager(layoutManager);
        addView();
        rv_list.setAdapter(new TotalRecycleAdapter(this,list) {
            @Override
            public int onLayout() {
                return R.layout.layout_item_recycle;
            }

            @Override
            protected void convert(MyViewHolder vHolder, Object o, int position) {

                        vHolder.setImageBitmap(R.id.iv_left, BitmapUtil.getBitmapUtil().SettingBigBitmap(R.mipmap.icon6,300,300));
                vHolder.setImageHeadUrl(R.id.iv_right,"https://ps.ssl.qhimg.com/dmfd/305_417_/t01c9e6e83151fd8f08.jpg");
                vHolder.setText(R.id.tv_message,list.get(position)+position);
            }

        });
    }

    @Override
    @Subscribe
    public void onEventMainThread(String str) {

    }

    private void addView(){
        list = new ArrayList<>();
        for(int i=0;i<names.length;i++){
            list.add(names[i]);
        }
    }
}
