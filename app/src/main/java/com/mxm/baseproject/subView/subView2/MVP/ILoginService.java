package com.mxm.baseproject.subView.subView2.MVP;

/**
 * Created by Administrator on 2017/6/24.
 */

public interface ILoginService {
    public  void login(String phone,String password,LoginListener loginListener);
}
