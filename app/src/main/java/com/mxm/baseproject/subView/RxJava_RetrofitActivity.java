package com.mxm.baseproject.subView;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mxm.baseproject.R;
import com.mxm.baseproject.subView.subView2.Retrofit.AliAddrsBean;
import com.mxm.baseproject.subView.subView2.Retrofit.ApiService;
import com.mxm.baseproject.subView.subView2.Retrofit.IndexRequestBean;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function3;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/6/24.
 */

public class RxJava_RetrofitActivity extends Activity {
    public static final Retrofit retrofit = new Retrofit.Builder().baseUrl("http://gc.ditu.aliyun.com/").addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();
    @BindView(R.id.rxtxt)
    TextView rxtxt;
    @BindView(R.id.rxbt)
    Button rxbt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        ButterKnife.bind(this);

    }

    private void init() {
        ApiService api = retrofit.create(ApiService.class);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("a", "上海市");
        map.put("aa", "黄浦区");
        Observable<AliAddrsBean> indexContent = api.getIndexContent("geocoding", map);
//        IndexRequestBean index = new IndexRequestBean();
//        index.setA("四川省");
//        index.setAa("成都市");
//        Observable<AliAddrsBean> indexContent1 = api.getIndexContent("geocoding", index);
        Map<String, Object> map1= new HashMap<String, Object>();
        map1.put("a", "四川省");
        map1.put("aa", "成都市");
        Observable<AliAddrsBean> indexContent1 = api.getIndexContent( map1);

        IndexRequestBean index1 = new IndexRequestBean();
        index1.setA("重庆市");
        index1.setAa("巴南区");
        Observable<AliAddrsBean> indexContent2 = api.getIndexContent(index1);//该接口不支持post形式请求，故返回数据有误，level标记为-1
        Observable.zip(indexContent,indexContent1, indexContent2, new Function3<AliAddrsBean, AliAddrsBean, AliAddrsBean, List<AliAddrsBean>>() {

            @Override
            public List<AliAddrsBean> apply(@NonNull AliAddrsBean aliAddrsBean, @NonNull AliAddrsBean aliAddrsBean2, @NonNull AliAddrsBean aliAddrsBean3) throws Exception {
                ArrayList<AliAddrsBean> arr=new ArrayList<AliAddrsBean>();
                arr.add(aliAddrsBean);
                arr.add(aliAddrsBean2);
                arr.add(aliAddrsBean3);
                return arr;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<AliAddrsBean>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Logger.i("onSubscribe");
            }

            @Override
            public void onNext(@NonNull List<AliAddrsBean> list) {
                Logger.i("onNext");
                rxtxt.setText("");
                for (AliAddrsBean ali:list
                     ) {
                    rxtxt.setText( rxtxt.getText()+"\n"+ali.toString());
                }

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Logger.i("onError");
                rxtxt.setText(e.getMessage());
            }

            @Override
            public void onComplete() {
                Logger.i("onComplete");
            }
        });
    }

    @OnClick({R.id.rxtxt, R.id.rxbt})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rxtxt:
                break;
            case R.id.rxbt:
                init();
                break;
        }
    }
}
