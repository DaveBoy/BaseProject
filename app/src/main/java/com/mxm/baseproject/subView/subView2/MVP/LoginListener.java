package com.mxm.baseproject.subView.subView2.MVP;

/**
 * Created by Administrator on 2017/6/24.
 */

public interface LoginListener {
    void loginSuccess(User user);
    void loginFailed(String msg);
}
