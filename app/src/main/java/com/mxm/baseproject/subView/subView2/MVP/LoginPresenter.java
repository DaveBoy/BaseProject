package com.mxm.baseproject.subView.subView2.MVP;

import android.os.Handler;

import com.orhanobut.logger.Logger;


/**
 * Created by Administrator on 2017/6/24.
 */

public class LoginPresenter {
    private ILoginService loginService;
    private ILoginView loginView;
    private Handler handler=new Handler();
    public LoginPresenter(ILoginView loginView)
    {
        this.loginView = loginView;
        this.loginService = new LoginService();
    }
    public void login(){
        loginView.showDialog();
        loginService.login(loginView.getPhone(), loginView.getPassword(), new LoginListener() {
            @Override
            public void loginSuccess(final User user) {
                Logger.i("Presenter_loginSuccess:"+Thread.currentThread().getName() );
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Logger.i("Presenter_loginSuccess_post:"+Thread.currentThread().getName() );
                        loginView.hideDialog();
                        loginView.loginSuccess(user);
                    }
                });


            }

            @Override
            public void loginFailed(final String msg) {
                Logger.i("Presenter_loginFailed:"+Thread.currentThread().getName() );
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Logger.i("Presenter_loginFailed_post:"+Thread.currentThread().getName() );
                        loginView.hideDialog();
                        loginView.loginFailed(msg);
                    }
                });


            }
        });
    }

    public void clear() {
        loginView.clear();
    }
}
