package com.example.byz.studyandroid;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.byz.studyandroid.base.BaseActivity;
import com.example.byz.studyandroid.utils.HttpRequestUtilTest;
import com.example.byz.studyandroid.utils.PermissionsUtils;
import com.example.byz.studyandroid.utils.UpdateManager;

import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

import static android.Manifest.permission.ACCESS_NETWORK_STATE;

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
    }

    @Override
    @Subscribe
    public void onEventMainThread(String str) {
        if(str.equals("is_force_2")){
            Toast.makeText(getActivity(),"取消下载",Toast.LENGTH_LONG).show();
        }
        if(str.equals("is_force_1")){
            finish();
        }

    }

    String has_new,download_url,description,is_force;

    private void onCheck(){
        Map<String,String> map = new HashMap<String, String>();
        map.put("version_name","100");

        onLoddingShow(getActivity());
        HttpRequestUtilTest.getHttpRequestUtilTest().doGet(getActivity(), "http://api.jcd6.com/version", map, new HttpRequestUtilTest.OkHttpListener() {
            @Override
            public void onResponse(Call call, String response) throws JSONException {
                onLoddingHint();
                Log.e("byz",response);
                try {
                    JSONObject obj = new JSONObject(response);

                    if (obj.optString("code").equals("1")) {
                        JSONObject obj_data = obj.optJSONObject("data");
                        if (obj_data.optString("has_new").equals("1")) {
                            has_new = obj_data.optString("has_new");
                            download_url = obj_data.optString("download_url");
                            description = obj_data.optString("description");
                            is_force = obj_data.optString("is_force");

                            if(!SelectPermission(Manifest.permission.ACCESS_NETWORK_STATE)||
                                    !SelectPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                                Toast.makeText(getActivity(),"没有权限",Toast.LENGTH_SHORT).show();
                               return;
                            }

                            UpdateManager manager = new UpdateManager(getActivity());
//                                    // 检查软件更新
                                    manager.checkVersions(obj_data.optString("has_new"), obj_data.optString("download_url"),
                                            obj_data.optString("description"), obj_data.optString("is_force"));
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
                onLoddingHint();
                Log.e("byz",e.getMessage().toString()+"------");
            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();
        onSelectPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, ACCESS_NETWORK_STATE);
        onAskPermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 0){
            for(int i=0;i<grantResults.length;i++){
                if(grantResults[i]!=0){
                    Toast.makeText(getActivity(),"缺少权限："+permissions[i]+"",Toast.LENGTH_LONG).show();
                }
            }

        }
    }

    private boolean SelectPermission(String permission) {
//        PackageManager.PERMISSION_GRANTED = 0
        if (ContextCompat.checkSelfPermission(getActivity(), permission) != 0) {
            return false;
        }
        return true;
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
        if(permissions!=null&&permissions.size()!=0){
            ActivityCompat.requestPermissions(getActivity(),permissions.toArray(new String[permissions.size()]),0);
        }
    }

}
