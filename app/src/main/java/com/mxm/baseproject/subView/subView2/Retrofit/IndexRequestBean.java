package com.mxm.baseproject.subView.subView2.Retrofit;

/**
 * Created by Administrator on 2017/6/24.
 * 参数实体
 */

public class IndexRequestBean {
    private String a;//一级城市
    private String aa;//二级城市
    private String aaa;//三级城市

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getAa() {
        return aa;
    }

    public void setAa(String aa) {
        this.aa = aa;
    }

    public String getAaa() {
        return aaa;
    }

    public void setAaa(String aaa) {
        this.aaa = aaa;
    }

    @Override
    public String toString() {
        return "IndexRequestBean{" +
                "a='" + a + '\'' +
                ", aa='" + aa + '\'' +
                ", aaa='" + aaa + '\'' +
                '}';
    }
}
