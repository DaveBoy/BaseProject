package com.mxm.baseproject.subView.subView2.MVVM;


/**
 * Created by Administrator on 2017/6/24.
 */

public class LoginService implements ILoginService {
    @Override
    public void login(final String phone, final String password, final LoginListener loginListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (phone.equals("111") && password.equals("123")) {
                    User user = new User();
                    user.setUserID("234");//随意返回的数据，模拟后台返回的数据
                    user.setUserName("mxm");
                    loginListener.loginSuccess(user);
                } else
                    loginListener.loginFailed("密码错误");
            }
        }).start();
    }
}
