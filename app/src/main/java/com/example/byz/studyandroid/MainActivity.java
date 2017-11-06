package com.example.byz.studyandroid;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.byz.studyandroid.adapter.MyAdapter;
import com.example.byz.studyandroid.base.BaseActivity;
import com.example.byz.studyandroid.utils.CommonUtil;
import com.example.byz.studyandroid.utils.SignUtils;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends BaseActivity {
    String url = "http://api.jcd6.com/channeldata";
    RecyclerView rv_list;
    String names[] = {"1111", "22222", "33333333", "44444444", "5555555", "66666666", "77777", "888888", "999999", "0000000"};
    MyAdapter myAdapter;
//华为8  c2f07887d6159a02
//红米   da577dd60c7a7682
//r9s    54060c337e4d9881
//r11


    @Override
    public int onLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        rv_list = (RecyclerView) findViewById(R.id.rv_list);
        addView();
        myAdapter = new MyAdapter(getActivity(), list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_list.setLayoutManager(linearLayoutManager);
        rv_list.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                onToast(list.get(position));
            }

        });

    }


    /**
     * post请求方式
     *
     * @param url            请求地址
     * @param map            post请求参数
     * @param okHttpListener 回调接口
     */
    public void doPost(final Activity mActivity, final String url, Map<String, String> map, final OkHttpListener okHttpListener) {

        /**
         * 配置请求参数
         */
        FormBody.Builder formBody = new FormBody.Builder();
        Iterator<Map.Entry<String, String>> entryIterator = map.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<String, String> entry = entryIterator.next();
            formBody.add(entry.getKey(), entry.getValue());
        }
        RequestBody requestBody = formBody.build();

        /**
         * 配置请求头
         */
        Request.Builder builder = new Request.Builder();
        Iterator<Map.Entry<String, String>> entryIterator1 = getHead().entrySet().iterator();
        while (entryIterator1.hasNext()) {
            Map.Entry<String, String> entry = entryIterator1.next();
            builder.addHeader(entry.getKey(), entry.getValue());
        }
        Request request = builder.url(url).post(requestBody).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        okHttpListener.onFailure(call, e);
                    }
                });

            }

            @Override
            public void onResponse(final Call call, final Response response) throws IOException {
                final String body = response.body().string();
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            okHttpListener.onResponse(call, body);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
    }

    public interface OkHttpListener {
        void onResponse(Call call, String response) throws JSONException;

        void onFailure(Call call, IOException e);
    }


    /**
     * 配置请求头信息
     *
     * @return
     */
    private Map<String, String> getHead() {
        final Map<String, String> head_map = new HashMap<String, String>();
        head_map.put("Accept", "application/json");
        head_map.put("ntype", "Android");
        head_map.put("fly", "jcd2089");
        head_map.put("nversion", "11111111111");
        head_map.put("imei", "2222222222222");
        head_map.put("sys_version", "33333333333");
        return head_map;
    }

    List<String> list;

    private void addView() {
        list = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            list.add(names[i]);
        }
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
        doPost(getActivity(), url, map, new OkHttpListener() {
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
