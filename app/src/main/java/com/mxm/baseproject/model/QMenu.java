package com.mxm.baseproject.model;

/**
 * Created by Mao on 2017/6/18.
 */

public class QMenu {
    private String icon;
    private String name;
    private String detail;

    public QMenu(String icon, String name, String detail) {
        this.icon = icon;
        this.name = name;
        this.detail = detail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "QMenu{" +
                "name='" + name + '\'' +
                ", detail='" + detail + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
