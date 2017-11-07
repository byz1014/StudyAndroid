package com.example.byz.studyandroid;

import android.Manifest;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.byz.studyandroid.base.BaseActivity;
import com.example.byz.studyandroid.utils.HttpRequestUtilTest;
import com.example.byz.studyandroid.utils.PermissionsUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by byz on 2017/11/6.
 */

public class CheckVersionActivity extends BaseActivity {
    TextView tv_version;

    PermissionsUtils permissionsUtils;

    @Override
    public int onLayout() {
        return R.layout.layout_version;
    }

    @Override
    public void init() {
        tv_version = (TextView) findViewById(R.id.tv_version);

        tv_version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCheck();
            }
        });
//tv_version.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//        Map<String,String> map = new HashMap<String, String>();
//        HttpRequestUtilTest.getHttpRequestUtilTest().doGet(getActivity(), "http://api.jcd6.com/version", map, new HttpRequestUtilTest.OkHttpListener() {
//            @Override
//            public void onResponse(Call call, String response) throws JSONException {
//                Log.e("byz",response);
//            }
//
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//        });
//    }
//});

//
//        HttpRequestUtilTest.getHttpRequestUtilTest().doGet(getActivity(), "/version", map, new HttpRequestUtilTest.OkHttpListener() {
//            @Override
//            public void onResponse(Call call, String response) throws JSONException {
//                try {
//                    JSONObject obj = new JSONObject(response);
//                    if (obj.optString("code").equals("1")) {
//                        JSONObject obj_data = obj.optJSONObject("data");
//                        if (obj_data.optString("has_new").equals("1")) {
//                            if (null != mTimer) {
//                                mTimer.cancel();
//                            }
//                            has_new = obj_data.optString("has_new");
//                            download_url = obj_data.optString("download_url");
//                            description = obj_data.optString("description");
//                            is_force = obj_data.optString("is_force");
//                            if (ContextCompat.checkSelfPermission(WelcomeActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                                //申请权限is_force
//                                ActivityCompat.requestPermissions(WelcomeActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUESTPERMISSION);
//                                AlertToast("请允许权限进行下载安装");
//                            } else {
//                                UpdateManager manager = new UpdateManager(WelcomeActivity.this);
//                                // 检查软件更新
//                                manager.checkVersions(obj_data.optString("has_new"), obj_data.optString("download_url"),
//                                        obj_data.optString("description"), obj_data.optString("is_force"));
//                            }
//                        } else {
//                            onTimerStart();
//                            // Toast.makeText(mContext,"无版本更新", Toast.LENGTH_LONG).show();
//                        }
//
//                    }else{
//                        onTimerStart();
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//
//                    onTimerStart();
//                    CountDownTimer countDownTimer = new CountDownTimer(6000, 1000) {
//                        @Override
//                        public void onTick(long millisUntilFinished) {
//                            LogUtils(millisUntilFinished / 1000 + "");
//                        }
//
//                        @Override
//                        public void onFinish() {
//                            LogUtils("");
//                        }
//                    };
//                    countDownTimer.start();
//                }
//            }
//
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//        });
    }
    String has_new,download_url,description,is_force;

    private void onCheck(){
        Map<String,String> map = new HashMap<String, String>();
        map.put("version_name","100");

        HttpRequestUtilTest.getHttpRequestUtilTest().doGet(getActivity(), "http://api.jcd6.com/version", map, new HttpRequestUtilTest.OkHttpListener() {
            @Override
            public void onResponse(Call call, String response) throws JSONException {
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.optString("code").equals("1")) {
                        JSONObject obj_data = obj.optJSONObject("data");
                        if (obj_data.optString("has_new").equals("1")) {
                            has_new = obj_data.optString("has_new");
                            download_url = obj_data.optString("download_url");
                            description = obj_data.optString("description");
                            is_force = obj_data.optString("is_force");
//                            if (ContextCompat.checkSelfPermission(WelcomeActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                                //申请权限is_force
//                                ActivityCompat.requestPermissions(WelcomeActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUESTPERMISSION);
//                                AlertToast("请允许权限进行下载安装");
//                            } else {
//                                UpdateManager manager = new UpdateManager(WelcomeActivity.this);
//                                // 检查软件更新
//                                manager.checkVersions(obj_data.optString("has_new"), obj_data.optString("download_url"),
//                                        obj_data.optString("description"), obj_data.optString("is_force"));
//                            }
                        } else {
                             Toast.makeText(getActivity(),"无版本更新", Toast.LENGTH_LONG).show();
                        }

                    }else{
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {

            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();
        permissionsUtils = PermissionsUtils.getPermissionsUtils();
        if (permissionsUtils.isNeedCheck) {
            permissionsUtils.checkPermissions(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionsUtils.onResult(requestCode, permissions, grantResults, getActivity());
    }
}
