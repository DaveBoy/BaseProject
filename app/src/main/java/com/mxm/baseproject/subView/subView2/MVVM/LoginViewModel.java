package com.mxm.baseproject.subView.subView2.MVVM;

import android.app.Activity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mxm.baseproject.databinding.ActivityMvvmBinding;
import com.orhanobut.logger.Logger;

/**
 * Created by Mao on 2017/6/25.
 */

public class LoginViewModel {
    public LoginInfo info;
    public ActivityMvvmBinding binding;
    public Activity activity;
    public ProgressBar pro;
    public TextWatcher phoneChangeListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            Logger.i("phone:" + s.toString());
            info.phone = s.toString();
        }
    };
    public TextWatcher passwordChangeListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            Logger.i("password:" + s.toString());
            info.password = s.toString();
        }
    };

    public LoginViewModel(Activity activity, ActivityMvvmBinding binding) {
        this.binding = binding;
        this.activity = activity;
        pro = binding.progress;
        info = new LoginInfo();
        binding.setLoginViewModel(LoginViewModel.this);


    }

    public void onItemClick(View pView) {
        //onClickLoginUser(pView);
        info.setRes("登录成功");
        Toast.makeText(pView.getContext(), "通知Medel层，异步请求，获取用户信息:phone:" + info.phone + " password:" + info.password, Toast.LENGTH_SHORT).show();
    }

    //登录事件
    public void onClickLoginUser(View v) {
        if (TextUtils.isEmpty(info.phone) || TextUtils.isEmpty(info.password)) {
            Toast.makeText(activity, " 用户名或密码为空", Toast.LENGTH_SHORT).show();
            ;
        } else {
            pro.setVisibility(View.VISIBLE);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //判断是否登录成功
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pro.setVisibility(View.GONE);
                            if (info.phone.equals("111") && info.password.equals("123")) {
                                info.setRes("登录成功");
                            } else
                                info.setRes("密码错误");
                        }
                    });

                }
            }).start();
        }
    }
}
