package com.example.byz.studyandroid;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.byz.studyandroid.adapter.MyAdapter;
import com.example.byz.studyandroid.base.BaseActivity;
import com.example.byz.studyandroid.utils.CommonUtil;
import com.example.byz.studyandroid.utils.HttpRequestUtilTest;
import com.example.byz.studyandroid.utils.SignUtils;

import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

public class MainActivity extends BaseActivity {
    String url = "http://api.jcd6.com/channeldata";
    RecyclerView rv_list;
    String names[] = {"Loading", "版本检查", "背景模糊", "二维码", "5555555", "66666666", "77777", "888888", "999999", "test"};
    MyAdapter myAdapter;

    @Override
    public int onLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        rv_list = (RecyclerView) findViewById(R.id.rv_list);
        TitleHint();
        addView();
        myAdapter = new MyAdapter(getActivity(), list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_list.setLayoutManager(linearLayoutManager);
        rv_list.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View v, int position) {

                switch (position){
                    case 0:
                        goActivity(LoadingActivity.class);
                        break;
                    case 1:
                        goActivity(CheckVersionActivity.class);
                        break;
                    case 2:
                        goActivity(BackgroundActivity.class);
                        break;
                    case 3:
                        goActivity(PasswordIocnActivity.class);
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
                        goActivity(TestActivity.class);
                        break;
                }
            }

        });

    }

    @Override
    @Subscribe
    public void onEventMainThread(String str) {

    }

    List<String> list;

    private void addView() {
        list = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            list.add(names[i]);
        }
    }

    private void goActivity(Class<?> cls){
        Intent intent = new Intent(getActivity(),cls);
        startActivity(intent);
    }

    private void onChannel() {
        Map<String, String> map = new HashMap<>();
        map.put("device_id", CommonUtil.getDevice_id(getApplicationContext()));
        map.put("mac", CommonUtil.getMacAddress());
        map.put("channel", "huawei");
        map.put("channel_id", "11");
        map.put("device_name", CommonUtil.getDeviceName());
        map.put("os_version", CommonUtil.getOSVersion());
        map.put("uuid", CommonUtil.getUuid(getApplicationContext()));
        map.put("ip", CommonUtil.getIP(getApplicationContext()));
        map.put("sign", SignUtils.getSignUtils().getSignChannel(CommonUtil.getUuid(getApplicationContext())));
        HttpRequestUtilTest.getHttpRequestUtilTest().doPost(getActivity(), url, map, new HttpRequestUtilTest.OkHttpListener() {
            @Override
            public void onResponse(Call call, String response) throws JSONException {
                Log.e("byz", response);
            }

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("byz", e.getMessage().toString() + "");
            }
        });

    }


}
