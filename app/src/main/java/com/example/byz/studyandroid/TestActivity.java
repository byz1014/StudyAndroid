package com.example.byz.studyandroid;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.byz.studyandroid.base.BaseActivity;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by byz on 2017/11/7.
 */

public class TestActivity extends BaseActivity implements View.OnClickListener {
    TextView test;

    @Override
    public int onLayout() {
        return R.layout.layout_test;
    }

    @Override
    public void init() {
        test = (TextView) findViewById(R.id.test);
        test.setOnClickListener(this);
    }

    @Override
    @Subscribe
    public void onEventMainThread(String str) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.test:
                getPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                break;
        }
    }


    private void getPermission(String permission){
        if(!SelectPermission(permission)){
            askPermission(permission);
        }
    }


    private boolean SelectPermission(String permission) {
//        PackageManager.PERMISSION_GRANTED = 0
        if (ContextCompat.checkSelfPermission(getActivity(), permission) != 0) {
            return false;
        }
        return true;
    }

    private void askPermission(String permission) {
        ActivityCompat.requestPermissions(getActivity(), new String[]{
                permission}, 0);
    }


    List<String> permissions;
    private void onSelectPermissions(String ...permission){
        permissions = new ArrayList<>();
        for(int i=0;i<permission.length;i++){
            if(!SelectPermission(permission[i])){
                permissions.add(permission[i]);
            }
        }
    }
    private void onAskPermission(){
        ActivityCompat.requestPermissions(getActivity(),permissions.toArray(new String[permissions.size()]),0);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 0){
            for(int i=0;i<grantResults.length;i++){
                if(grantResults[i] == 0){
                    Log.e("byz",permissions[i]+"---权限获取成功");
                }else{
                    Log.e("byz",permissions[i]+"---权限获取失败");
                }
            }
        }
    }

    /**
     * 需要进行检测的权限数组
     */
    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };
}
