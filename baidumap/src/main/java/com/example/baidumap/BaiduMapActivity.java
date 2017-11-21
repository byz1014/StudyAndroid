package com.example.baidumap;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.SupportMapFragment;
import com.example.baselib.PermissionsUtils;
import com.example.baselib.base.BaseActivity;

import org.greenrobot.eventbus.Subscribe;


/**
 * Created by byz on 2017/11/14.
 */

public class BaiduMapActivity  extends BaseActivity{

    MapView bmapView;
    SupportMapFragment map;
    ImageView iv_bage,iv_icon_1,iv_icon_2,iv_icon_3,iv_icon_4,iv_icon_5;
    RelativeLayout ll_right,right_bottmo;
    boolean state = true;
    @Override
    public int onLayout() {
        return R.layout.layout_baidu_map;
    }

    @Override
    public void init() {

        setLeftListener(new OnTitleLeftListener() {
            @Override
            public void onLeft() {
                finish();
            }
        });

        iv_bage = (ImageView)findViewById(R.id.iv_bage);
        iv_icon_1 = (ImageView)findViewById(R.id.iv_icon_1);
        iv_icon_2 = (ImageView)findViewById(R.id.iv_icon_2);
        iv_icon_3 = (ImageView)findViewById(R.id.iv_icon_3);
        iv_icon_4 = (ImageView)findViewById(R.id.iv_icon_4);
        iv_icon_5 = (ImageView)findViewById(R.id.iv_icon_5);
        ll_right = (RelativeLayout)findViewById(R.id.ll_right) ;
        right_bottmo = (RelativeLayout)findViewById(R.id.right_bottmo);
        Bundle bundle = getIntent().getBundleExtra("data");
        String title = bundle.getString("title");
        setTitleMessage(title);
        permissionsUtils = PermissionsUtils.getPermissionsUtils();
        if(permissionsUtils.isPermissionAll(this,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_PHONE_STATE)){
            SDKInitializer.initialize(getApplicationContext());
        }else{
            permissioninit();
        }
        BaiduMapOptions baiduMapOptions = new BaiduMapOptions();
        map = SupportMapFragment.newInstance(baiduMapOptions);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.ll_map, map, "map_fragment").commit();
        right_bottmo.setAlpha(0);
        iv_bage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(state){
                    onItemAnimator_start(4,0,iv_icon_1);
                    onItemAnimator_start(4,1,iv_icon_2);
                    onItemAnimator_start(4,2,iv_icon_3);
                    onItemAnimator_start(4,3,iv_icon_4);
                    onItemAnimator_start(4,4,iv_icon_5);
                    state = false;
                }else{
                    onItemAnimator_stop(4,0,iv_icon_1);
                    onItemAnimator_stop(4,1,iv_icon_2);
                    onItemAnimator_stop(4,2,iv_icon_3);
                    onItemAnimator_stop(4,3,iv_icon_4);
                    onItemAnimator_stop(4,4,iv_icon_5);
                    state = true;
                }

            }
        });

    }



    private void onItemAnimator_start(float max,float index,View view){
        float bili = 90*(index / max);
        float l = ll_right.getHeight();
        float x = (float) (Math.sin(bili*Math.PI/180)*l);
        float y = (float) (Math.cos(bili*Math.PI/180)*l);

        ObjectAnimator.ofFloat(view,"X", ll_right.getWidth(),ll_right.getWidth()-x).setDuration((long) (150*index)).start();
        ObjectAnimator.ofFloat(view,"Y", ll_right.getHeight(), ll_right.getHeight()-y).setDuration((long) (150*index)).start();
        ObjectAnimator.ofFloat(right_bottmo,"alpha",0,1).setDuration(500).start();
    }

    private void onItemAnimator_stop(float max,float index,View view){
        float bili = 90*(index / max);
        float l = ll_right.getHeight();
        float x = (float) (Math.sin(bili*Math.PI/180)*l);
        float y = (float) (Math.cos(bili*Math.PI/180)*l);

        ObjectAnimator.ofFloat(view,"X", ll_right.getWidth()-x,ll_right.getWidth()*0.9f).setDuration((long) (150*(max-index))).start();
        ObjectAnimator.ofFloat(view,"Y", ll_right.getHeight()-y, ll_right.getHeight()).setDuration((long) (150*(max-index))).start();
        ObjectAnimator.ofFloat(right_bottmo,"alpha",1,0).setDuration(500).start();
    }




    @Override
    @Subscribe
    public void onEventMainThread(String str) {

    }


    PermissionsUtils permissionsUtils;
    private void permissioninit(){
        permissionsUtils.checkPermissions(this, Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_PHONE_STATE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionsUtils.onResult(requestCode,permissions,grantResults,this);
    }




}
