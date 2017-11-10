package com.example.byz.studyandroid.utils;

import android.app.Activity;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by byz on 2017/11/1.
 * 网络请求工具类
 */
public class HttpRequestUtilTest {
    private static HttpRequestUtilTest httpRequestUtilTest;
    private HttpRequestUtilTest(){}
    public static HttpRequestUtilTest getHttpRequestUtilTest(){
        if(httpRequestUtilTest == null){
            synchronized (HttpRequestUtilTest.class){
                if(httpRequestUtilTest==null){
                    httpRequestUtilTest = new HttpRequestUtilTest();
                }
            }
        }
        return httpRequestUtilTest;
    }
private static String ATG = "byz";

    /**
     * post请求方式
     * @param url 请求地址
     * @param map post请求参数
     * @param okHttpListener 回调接口
     */
    public void doPost(final Activity mActivity, final String url, Map<String, String> map, final OkHttpListener okHttpListener) {

        Log.e(ATG,"url:post--"+url);
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
                        okHttpListener.onFailure(call,e);
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
                            okHttpListener.onResponse(call,body);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
    }



    /**
     * GET 请求方式
     * @param url
     * @param map
     * @param okHttpListener
     */
    public void doGet(final Activity mActivity, final String url, Map<String, String> map, final OkHttpListener okHttpListener) {

        Log.e(ATG,"url:get--"+url);
//        final CacheControl.Builder builder1 = new CacheControl.Builder();
//        builder1.noCache();//不使用缓存，全部走网络
//        builder1.noStore();//不使用缓存，也不存储缓存
//        builder1.onlyIfCached();//只使用缓存
//        builder1.noTransform();//禁止转码
//        builder1.maxAge(10, TimeUnit.MILLISECONDS);//指示客户机可以接收生存期不大于指定时间的响应。
//        builder1.maxStale(10, TimeUnit.SECONDS);//指示客户机可以接收超出超时期间的响应消息
//        builder1.minFresh(10, TimeUnit.SECONDS);//指示客户机可以接收响应时间小于当前时间加上指定时间的响应。
//        CacheControl cache = builder1.build();//cacheControl
//        CacheControl.FORCE_CACHE; //仅仅使用缓存
//        CacheControl.FORCE_NETWORK;// 仅仅使用网络
        /**
         * 配置请求参数
         */
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        StringBuilder message = new StringBuilder();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            message.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue());
            if(iterator.hasNext()){
                message.append("&");
            }
        }
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(url).append("?").append(message.toString());

        /**
         * 配置请求头
         */
        Request.Builder builder = new Request.Builder();
//        builder.cacheControl(cache);
        Iterator<Map.Entry<String, String>> entryIterator1 = getHead().entrySet().iterator();
        while (entryIterator1.hasNext()) {
            Map.Entry<String, String> entry = entryIterator1.next();
            builder.addHeader(entry.getKey(), entry.getValue());
        }
        Request request = builder.url(urlBuilder.toString()).build();
        OkHttpClient  okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        okHttpListener.onFailure(call,e);
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
                            okHttpListener.onResponse(call,body);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
    }

    /**
     * 配置请求头信息
     * @return
     */
    private Map<String, String> getHead() {
        final Map<String, String> head_map = new HashMap<String, String>();
        head_map.put("Accept","application/json");
        head_map.put("ntype","Android");
        head_map.put("fly","jcd2089");
//        try {
            head_map.put("nversion","" );
            head_map.put("imei","" );
            head_map.put("sys_version","");
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }

        return head_map;
    }


    public interface OkHttpListener{
        void onResponse(Call call, String response) throws JSONException;
        void onFailure(Call call, IOException e);
    }




}
