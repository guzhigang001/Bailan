package com.example.ggxiaozhi.store.the_basket.bean;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.bean
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/1
 * 时间   ： 16:01
 * 功能   ：接口数据共有类型
 */

public class AppBean {

    private String appId ;
    private String appVersionName ;
    private String downCountDesc ;
    private String downurl ;
    private String icon ;
    private String intro ;
    private String memo ;
    private String name ;
    private String packageName ;
    private String sizeDesc ;
    private String stars ;
    private String aliasName ;
    private String detailId;

    public AppBean(String appId, String appVersionName, String downCountDesc, String downurl, String icon, String intro, String memo, String name, String packageName, String sizeDesc, String stars, String aliasName, String detailId) {
        this.appId = appId;
        this.appVersionName = appVersionName;
        this.downCountDesc = downCountDesc;
        this.downurl = downurl;
        this.icon = icon;
        this.intro = intro;
        this.memo = memo;
        this.name = name;
        this.packageName = packageName;
        this.sizeDesc = sizeDesc;
        this.stars = stars;
        this.aliasName = aliasName ;
        this.detailId = detailId ;
    }

    public String getDetailId() {
        return detailId;
    }

    public String getAliasName() {
        return aliasName;
    }

    public String getAppId() {
        return appId;
    }

    public String getAppVersionName() {
        return appVersionName;
    }

    public String getDownCountDesc() {
        return downCountDesc;
    }

    public String getDownurl() {
        return downurl;
    }

    public String getIcon() {
        return icon;
    }

    public String getIntro() {
        return intro;
    }

    public String getMemo() {
        return memo;
    }

    public String getName() {
        return name;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getSizeDesc() {
        return sizeDesc;
    }

    public String getStars() {
        return stars;
    }
}
