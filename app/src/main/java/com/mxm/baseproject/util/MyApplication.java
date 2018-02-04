package com.mxm.baseproject.util;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.LogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

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
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(2)         // (Optional) How many method line to show. Default 2
                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
                .tag("BaseProject")   // (Optional) Global tag for every log. Default PRETTY_LOGGER

                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        Logger.addLogAdapter(new LogAdapter() {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return true;
            }

            @Override
            public void log(int priority, String tag, String message) {
                Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
            }
        });
        // 捕捉异常
        ExceptionHandler catchHandler = ExceptionHandler.getInstance();
        catchHandler.init(getApplicationContext());
        catchHandler.collectDeviceInfo(getApplicationContext());
    }
}
