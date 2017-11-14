package com.example.baidumap;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;
import com.example.baselib.PermissionsUtils;


/**
 * Created by byz on 2017/11/14.
 */

public class BaiduMapActivity  extends Activity{

    MapView bmapView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        permissionsUtils = PermissionsUtils.getPermissionsUtils();
        if(permissionsUtils.isPermissionAll(this,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_PHONE_STATE)){
            SDKInitializer.initialize(getApplicationContext());
        }else{
            permissioninit();
        }

        setContentView(R.layout.layout_baidu_map);
        bmapView = (MapView)findViewById(R.id.bmapView);
        bmapView.getChildAt(1).setVisibility(View.GONE);
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
