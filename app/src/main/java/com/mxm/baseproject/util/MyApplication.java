package com.mxm.baseproject.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by Mao on 2017/6/18.
 */

public class MyApplication extends Application {
    private static Context mContext;

    public static Context getInstance() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        // 捕捉异常
        ExceptionHandler catchHandler = ExceptionHandler.getInstance();
        catchHandler.init(getApplicationContext());
        catchHandler.collectDeviceInfo(getApplicationContext());
    }
}
