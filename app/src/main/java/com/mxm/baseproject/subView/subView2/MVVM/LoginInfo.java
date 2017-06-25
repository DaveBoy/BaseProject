package com.mxm.baseproject.subView.subView2.MVVM;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by Mao on 2017/6/25.
 */

public class LoginInfo extends BaseObservable {
    public String phone;
    public String password;
    public String res;

    @Bindable
    public String getRes() {
        return res;
    }

    public void setRes(String mRes) {
        this.res = mRes;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
