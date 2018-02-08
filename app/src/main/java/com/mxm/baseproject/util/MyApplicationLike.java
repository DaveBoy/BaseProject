package com.mxm.baseproject.util;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import com.mxm.baseproject.BuildConfig;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.LogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.lib.service.PatchResult;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tinkerpatch.sdk.TinkerPatch;
import com.tinkerpatch.sdk.server.callback.ConfigRequestCallback;
import com.tinkerpatch.sdk.server.callback.RollbackCallBack;
import com.tinkerpatch.sdk.tinker.callback.ResultCallBack;

import java.util.HashMap;


/**
 * tinkerplatform
 * Created by Mao on 2018/2/7.
 */
@SuppressWarnings("unused")
@DefaultLifeCycle(application = "com.mxm.baseproject.util.MyApplication",
        flags = ShareConstants.TINKER_ENABLE_ALL,
        loadVerifyFlag = false)
public class MyApplicationLike extends DefaultApplicationLike {
    private static final String TAG = "Tinker.SampleAppLike";
    private static Context mContext;

    public MyApplicationLike(Application application,
                             int tinkerFlags,
                             boolean tinkerLoadVerifyFlag,
                             long applicationStartElapsedTime,
                             long applicationStartMillisTime,
                             Intent tinkerResultIntent
    ) {
        super(
                application,
                tinkerFlags,
                tinkerLoadVerifyFlag,
                applicationStartElapsedTime,
                applicationStartMillisTime,
                tinkerResultIntent
        );
    }

    public static Context getInstance() {
        return mContext;
    }

    /**
     * install multiDex before install tinker
     * so we don't need to put the tinker lib classes in the main dex
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        //you must install multiDex whatever tinker is installed!
    }

    /**
     * 我们需要确保至少对主进程跟patch进程初始化 TinkerPatch
     */
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.TINKER_ENABLE)
            initTinker();
        mContext = getApplication().getApplicationContext();
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
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

    private void initTinker() {
        //   if (BuildConfig.TINKER_ENABLE) {
        //开始检查是否有补丁，这里配置的是每隔访问3小时服务器是否有更新。
        TinkerPatch.init(this)
                .reflectPatchLibrary()
                .setPatchRollbackOnScreenOff(true)
                .setPatchRestartOnSrceenOff(true)
                .setFetchPatchIntervalByHours(3);

        // 获取当前的补丁版本
        Logger.d("current patch version is " + TinkerPatch.with().getPatchVersion());

        //每隔3个小时去访问后台时候有更新,通过handler实现轮训的效果
        TinkerPatch.with().fetchPatchUpdateAndPollWithInterval();
        // }
    }

    /**
     * 在这里给出TinkerPatch的所有接口解释
     * 更详细的解释请参考:http://tinkerpatch.com/Docs/api
     */
    private void useSample() {
        TinkerPatch.init(this)
                //是否自动反射Library路径,无须手动加载补丁中的So文件
                //注意,调用在反射接口之后才能生效,你也可以使用Tinker的方式加载Library
                .reflectPatchLibrary()
                //向后台获取是否有补丁包更新,默认的访问间隔为3个小时
                //若参数为true,即每次调用都会真正的访问后台配置
                .fetchPatchUpdate(false)
                //设置访问后台补丁包更新配置的时间间隔,默认为3个小时
                .setFetchPatchIntervalByHours(3)
                //向后台获得动态配置,默认的访问间隔为3个小时
                //若参数为true,即每次调用都会真正的访问后台配置
                .fetchDynamicConfig(new ConfigRequestCallback() {
                    @Override
                    public void onSuccess(HashMap<String, String> hashMap) {

                    }

                    @Override
                    public void onFail(Exception e) {

                    }
                }, false)
                //设置访问后台动态配置的时间间隔,默认为3个小时
                .setFetchDynamicConfigIntervalByHours(3)
                //设置当前渠道号,对于某些渠道我们可能会想屏蔽补丁功能
                //设置渠道后,我们就可以使用后台的条件控制渠道更新
                .setAppChannel("default")
                //屏蔽部分渠道的补丁功能
                .addIgnoreAppChannel("googleplay")
                //设置tinkerpatch平台的条件下发参数
                .setPatchCondition("test", "1")
                //设置补丁合成成功后,锁屏重启程序
                //默认是等应用自然重启
                .setPatchRestartOnSrceenOff(true)
                //我们可以通过ResultCallBack设置对合成后的回调
                //例如弹框什么
                .setPatchResultCallback(new ResultCallBack() {
                    @Override
                    public void onPatchResult(PatchResult patchResult) {
                        Logger.i("onPatchResult callback here");
                    }
                })
                //设置收到后台回退要求时,锁屏清除补丁
                //默认是等主进程重启时自动清除
                .setPatchRollbackOnScreenOff(true)
                //我们可以通过RollbackCallBack设置对回退时的回调
                .setPatchRollBackCallback(new RollbackCallBack() {
                    @Override
                    public void onPatchRollback() {
                        Logger.i("onPatchRollback callback here");
                    }
                });
    }

}
/**tinker
 * Created by Mao on 2017/6/18.
 */
/*
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
                .methodCount(0)         // (Optional) How many method line to show. Default 2
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
*/
