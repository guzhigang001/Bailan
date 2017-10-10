package com.example.ggxiaozhi.store.the_basket.bean;

import android.graphics.drawable.Drawable;

/**
 * 跳转安装应用 用到的实体类
 *
 * @author xzhang
 */

public class AppInfo {

    private String name ;
    private String packageName ;
    private Drawable icon ;
    private long firstInstallTime ;
    private String versionName ;

    public String getName() {
        return name;
    }

    public String getPackageName() {
        return packageName;
    }

    public Drawable getIcon() {
        return icon;
    }

    public long getFirstInstallTime() {
        return firstInstallTime;
    }

    public String getVersionName() {
        return versionName;
    }

    public AppInfo(String name, String packageName, Drawable icon, long firstInstallTime, String versionName) {

        this.name = name;
        this.packageName = packageName;
        this.icon = icon;
        this.firstInstallTime = firstInstallTime;
        this.versionName = versionName;
    }
}
