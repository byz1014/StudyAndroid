package com.example.byz.studyandroid;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.byz.studyandroid.adapter.LeftAdapter;
import com.example.byz.studyandroid.base.BaseActivity;
import com.example.byz.studyandroid.bean.LeftInfo;
import com.example.byz.studyandroid.view.CircleImageView;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    NavigationView navigation_view ;
    DrawerLayout dl_body;
    ImageView iv_show;
    RecyclerView rv_left_list;
    List<LeftInfo> list;
    String messages[]={"loading","版本检查","二维码扫描","背景模糊","功能5","功能6","功能7","功能8","功能9","功能10","test模块"};
    int resources[]={
            R.mipmap.icon1,R.mipmap.icon2,R.mipmap.icon3,
            R.mipmap.icon4,R.mipmap.icon5,R.mipmap.icon6,R.mipmap.icon7,
            R.mipmap.icon8,R.mipmap.icon9,R.mipmap.icona,R.mipmap.iconb };
    LeftAdapter leftAdapter;
    TextView tv_name;
    CircleImageView cv_header  ;
    View  headerLayout;
    @Override
    public void onClick(View view) {
    }

    @Override
    public int onLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        navigation_view = (NavigationView)findViewById(R.id.navigation_view);
        dl_body = (DrawerLayout)findViewById(R.id.dl_body);
        iv_show = (ImageView)findViewById(R.id.iv_show) ;
        TitleHint();
        addView();
        leftAdapter = new LeftAdapter(list,getActivity());
        headerLayout = navigation_view.getHeaderView(0);
        tv_name = (TextView)headerLayout.findViewById(R.id.tv_name);
        cv_header = (CircleImageView)headerLayout.findViewById(R.id.cv_header);
        rv_left_list = (RecyclerView) headerLayout.findViewById(R.id.rv_left_list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv_left_list.setLayoutManager(layoutManager);
        rv_left_list.setAdapter(leftAdapter);
        leftAdapter.setOnItemClickListener(new LeftAdapter.OnItemClickListener() {
            @Override
            public void OnClickListener(int index) {
                switch (index){
                    case 0:
                        Bundle bundle = new Bundle();
                        bundle.putString("title",messages[index]);
                        goActivity(LoadingActivity.class,bundle);
                        break;
                    case 1:
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("title",messages[index]);
                        goActivity(CheckVersionActivity.class,bundle1);
                        break;
                    case 2:
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("title",messages[index]);
                        goActivity(PasswordIocnActivity.class,bundle2);
                        break;
                    case 3:
                        Bundle bundle3 = new Bundle();
                        bundle3.putString("title",messages[index]);
                        goActivity(BackgroundActivity.class,bundle3);
                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 6:

                        break;
                    case 7:

                        break;
                    case 8:

                        break;
                    case 9:
                        Bundle bundle10 = new Bundle();
                        bundle10.putString("title",messages[index]);
                        goActivity(TestActivity.class,bundle10);
                        break;
                    case 10:

                        break;
                    case 11:

                        break;
                }
            }
        });

        iv_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dl_body.openDrawer(Gravity.START);
            }
        });

        dl_body.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                iv_show.setAlpha(1-slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
// 姐 妹 变态 前者
            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

    }

    @Override @Subscribe
    public void onEventMainThread(String str) {

    }

    private void addView(){
        list = new ArrayList<>();
        for(int i=0;i<messages.length;i++){
            if(i == 5){
                continue;
            }
            list.add(new LeftInfo(messages[i],resources[i]));
        }
    }

}
