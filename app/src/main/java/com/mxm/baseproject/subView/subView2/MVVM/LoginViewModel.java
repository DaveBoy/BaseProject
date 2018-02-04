package com.mxm.baseproject.subView.subView2.MVVM;


import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.orhanobut.logger.Logger;

/**
 * Created by Mao on 2017/6/25.
 */

public class LoginViewModel {
    public ILoginService loginService;//model
    public ILoginView view;//view
    public LoginInfo info;
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
            info.setPhone(s.toString());
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
            info.setPassword(s.toString());
        }
    };

    public LoginViewModel(ILoginView activity) {
        this.view = activity;
        this.loginService = new LoginService();
        this.info = new LoginInfo();
        info.setPassword("123");
        info.setPhone("123");
    }

    //登录事件
    public void onLoign(View v) {
        Logger.i("onLoign");
        if (TextUtils.isEmpty(info.getPhone()) || TextUtils.isEmpty(info.getPassword())) {
            info.setRes("用户名或密码为空");
            Logger.i("用户名或密码为空");
        } else {
            info.setProstatus(1);
            loginService.login(info.getPhone(), info.getPassword(), new LoginListener() {
                @Override
                public void loginSuccess(User user) {
                    info.setProstatus(0);
                    info.setRes("登录成功：" + user.toString());
                    Logger.i("登录成功");
                }

                @Override
                public void loginFailed(String msg) {
                    info.setProstatus(0);
                    info.setRes("登录失败：" + msg);
                    Logger.i("登录失败");
                }
            });
        }
    }

    public void onClear(View v) {
        Logger.i("onClear");
        info.setPassword("");
        info.setPhone("");
        info.setRes("");
    }

}
