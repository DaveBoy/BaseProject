package com.mxm.baseproject.subView.subView2.Retrofit;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2017/6/24.
 */

public interface ApiService {


        @GET("{method_name}?")
        Observable<AliAddrsBean>  getIndexContent(@Path("method_name") String p, @QueryMap Map<String,Object> map);
        @GET("geocoding?")
        Observable<AliAddrsBean>  getIndexContent( @QueryMap Map<String,Object> map);
        @POST("{method_name}?")
        Observable<AliAddrsBean>  getIndexContent(@Path("method_name") String p, @Body IndexRequestBean indexRequestBean);

        @POST("geocoding?")
        Observable<AliAddrsBean> getIndexContent( @Body IndexRequestBean indexRequestBean);

}
