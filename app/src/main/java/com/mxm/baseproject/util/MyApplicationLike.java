package com.mxm.baseproject.util;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.multidex.MultiDex;
import android.widget.Toast;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.LogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Created by Mao on 2017/6/18.
 */
@SuppressWarnings("unused")
@DefaultLifeCycle(application = "com.mxm.baseproject.util.MyApplication",
        flags = ShareConstants.TINKER_ENABLE_ALL,
        loadVerifyFlag = false)
public class MyApplicationLike extends DefaultApplicationLike {


    public MyApplicationLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        //you must install multiDex whatever tinker is installed!
        MultiDex.install(base);

        //installTinker after load multiDex
        //or you can put com.tencent.tinker.** to main dex
        TinkerInstaller.install(this);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callback) {
        getApplication().registerActivityLifecycleCallbacks(callback);
    }

    private static Context mContext;
    public static Context getInstance() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplication().getApplicationContext();
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
        catchHandler.init(mContext);
        catchHandler.collectDeviceInfo(mContext);


    }
}
