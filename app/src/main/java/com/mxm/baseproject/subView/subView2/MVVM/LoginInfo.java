package com.mxm.baseproject.subView.subView2.MVVM;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by Mao on 2017/6/25.
 */

public class LoginInfo extends BaseObservable {
    private String phone;
    private String password;
    private String res;
    private int prostatus;
    @Bindable
    public String getRes() {
        return res;
    }

    public void setRes(String mRes) {
        this.res = mRes;
        notifyPropertyChanged(BR.res);
    }

    @Bindable
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        notifyPropertyChanged(BR.phone);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public int getProstatus() {
        return prostatus;
    }

    public void setProstatus(int prostatus) {
        this.prostatus = prostatus;
        notifyPropertyChanged(BR.prostatus);
    }
}
