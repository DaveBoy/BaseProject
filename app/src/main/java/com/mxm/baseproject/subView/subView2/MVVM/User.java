package com.mxm.baseproject.subView.subView2.MVVM;

/**
 * Created by Administrator on 2017/6/24.
 */

public class User {
    private String userName;
    private String userID;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userID='" + userID + '\'' +
                '}';
    }
}
