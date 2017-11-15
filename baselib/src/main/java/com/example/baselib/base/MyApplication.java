package com.example.baselib.base;

import android.app.Application;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by byz on 2017/11/7.
 */

public class MyApplication extends Application{
    private static MyApplication myApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(null)
                .showImageOnFail(null)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this).defaultDisplayImageOptions(options).build();// 添加你的配置需求
        ImageLoader.getInstance().init(configuration);
    }

    public static MyApplication getMyApplication(){
        return myApplication;
    }
}
