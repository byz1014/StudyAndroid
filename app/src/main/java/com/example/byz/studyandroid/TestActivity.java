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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                Log.e("byz",
                requestCode + "\n" +
                permissions.length + "\n" +
                permissions[0] + "\n" +
                grantResults.length + "\n" +
                grantResults[0]);
    }


}
