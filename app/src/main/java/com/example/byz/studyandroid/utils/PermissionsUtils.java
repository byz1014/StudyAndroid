package com.example.byz.studyandroid.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by byz on 2017/11/6.
 */

public class PermissionsUtils {

    /**
     * 判断是否需要检测，防止不停的弹框
     */
    public boolean isNeedCheck = true;
    private static final int PERMISSION_REQUEST_CODE = 0;
    private static PermissionsUtils permissionsUtils;
    private PermissionsUtils(){}
    public static PermissionsUtils getPermissionsUtils(){
        synchronized (PermissionsUtils.class){
            if(permissionsUtils == null){
                permissionsUtils = new PermissionsUtils();
            }
        }
        return permissionsUtils;
    }



    /**
     * 检测权限
     */
    public void checkPermissions(Activity activity, String... permissions) {
        List<String> needRequestPermissionList = findDeniedPermissions(permissions,activity);
        if (null != needRequestPermissionList && needRequestPermissionList.size() > 0) {
            ActivityCompat.requestPermissions(activity,
                    needRequestPermissionList.toArray(new String[needRequestPermissionList.size()]),PERMISSION_REQUEST_CODE);

        }
    }


    public void onResult(int requestCode,
                         String[] permissions, int[] paramArrayOfInt, final Activity activity){
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (!verifyPermissions(paramArrayOfInt)) {
                DialogUtils.getDialogUtils().onSetting(activity, new DialogUtils.DialogListener() {
                    @Override
                    public void onCancel() {
                        activity.finish();
                    }

                    @Override
                    public void onNext() {
                        startAppSettings(activity);
                    }
                });
                isNeedCheck = false;
            }
        }

    }







    /**
     *  启动应用的设置
     */
    private void startAppSettings(Activity activity) {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + activity.getPackageName()));
        activity.startActivity(intent);
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     */
    private List<String> findDeniedPermissions(String[] permissions, Activity activity) {
        List<String> needRequestPermissionList = new ArrayList<String>();
        for (String perm : permissions) {
            if (ContextCompat.checkSelfPermission(activity,
                    perm) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    activity, perm)) {
                needRequestPermissionList.add(perm);
            }
        }
        return needRequestPermissionList;
    }

    /**
     * 检测是否所有的权限都已经授权
     *
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

}
