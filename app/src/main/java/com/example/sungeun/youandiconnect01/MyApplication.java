package com.example.sungeun.youandiconnect01;

import android.app.Application;

import com.facebook.FacebookSdk;

/**
 * Created by sungeun on 2017-04-30.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}