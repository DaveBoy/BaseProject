package com.mxm.baseproject.subView.subView2.Retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/6/24.
 */

public class RetrofitManage {
    private RetrofitManage() {
    }

    public static RetrofitManage getInstance() {
        return RetrofitManager.retrofitManage;
    }

    private static class RetrofitManager {
        private static final RetrofitManage retrofitManage = new RetrofitManage();
    }
    public void sendRequest(String url) {
        //每一个Call实例可以同步(call.excute())或者异步(call.enquene(CallBack<?> callBack))的被执行，
        //每一个实例仅仅能够被使用一次，但是可以通过clone()函数创建一个新的可用的实例。
        //默认情况下，Retrofit只能够反序列化Http体为OkHttp的ResponseBody类型
        //并且只能够接受ResponseBody类型的参数作为@body
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                // 使用RxJava作为回调适配器
                .addConverterFactory(GsonConverterFactory.create()) // 使用Gson作为数据转换器
                .build();
    }
}
