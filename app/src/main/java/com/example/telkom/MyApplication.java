package com.example.telkom;

import android.app.Application;
import android.content.Context;

import com.appizona.yehiahd.fastsave.FastSave;


public class MyApplication extends Application {
    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    public static Context getContext(){
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        FastSave.init(getApplicationContext());

    }
}