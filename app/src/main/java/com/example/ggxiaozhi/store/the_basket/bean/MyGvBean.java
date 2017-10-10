package com.example.ggxiaozhi.store.the_basket.bean;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.bean
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/1
 * 时间   ： 15:28
 * 功能   ：我的页面GridView数据
 */
public class MyGvBean {

    private String name ;
    private int iconId ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconUrl(int iconId) {
        this.iconId = iconId;
    }

    public MyGvBean(String name, int iconId) {

        this.name = name;
        this.iconId = iconId;
    }
}
