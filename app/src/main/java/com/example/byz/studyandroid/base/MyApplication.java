package com.example.byz.studyandroid.base;

import android.app.Application;

/**
 * Created by byz on 2017/11/7.
 */

public class MyApplication extends Application{
    private static MyApplication myApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }

    public static MyApplication getMyApplication(){
        return myApplication;
    }
}
