package com.example.byz.studyandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
    TextView tv_name;
    CircleImageView cv_header  ;
    View  headerLayout;
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_show:
                dl_body.openDrawer(Gravity.START);
                break;
        }
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
        iv_show.setOnClickListener(this);
        dl_body.addDrawerListener(drawerListener);
        navigation_view.setNavigationItemSelectedListener(onNavigationItemSelectedListener);
        headerLayout = navigation_view.getHeaderView(0);
        tv_name = (TextView)headerLayout.findViewById(R.id.tv_name);
        cv_header = (CircleImageView)headerLayout.findViewById(R.id.cv_header);

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

    NavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.mu_loading:
                    Bundle bundle = new Bundle();
                    bundle.putString("title","loading");
                    goActivity(LoadingActivity.class,bundle);
                    break;
                case R.id.check_version:
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("title","版本检查");
                    goActivity(CheckVersionActivity.class,bundle1);
                    break;
                case R.id.see_icon:
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("title","二维码扫描");
                    goActivity(PasswordIocnActivity.class,bundle2);
                    break;
                case R.id.background_alph:
                    Bundle bundle3 = new Bundle();
                    bundle3.putString("title","背景模糊");
                    goActivity(BackgroundActivity.class,bundle3);
                    break;
                case R.id.recycle_test:
                    Bundle bundle4 = new Bundle();
                    bundle4.putString("title","Recycle自定义适配器");
                    goActivity(RecycleViewActivity.class,bundle4);
                    break;
                case R.id.mu_test:
                    Bundle bundles = new Bundle();
                    bundles.putString("title","测试模块");
                    goActivity(TestActivity.class,bundles);
                    break;

            }
            return false;
        }
    };

    DrawerLayout.DrawerListener drawerListener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
            iv_show.setAlpha(1-slideOffset);
        }

        @Override
        public void onDrawerOpened(View drawerView) {

        }

        @Override
        public void onDrawerClosed(View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };
}
