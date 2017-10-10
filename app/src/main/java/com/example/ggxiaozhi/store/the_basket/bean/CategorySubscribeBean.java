package com.example.ggxiaozhi.store.the_basket.bean;

import java.util.List;

/**
 * 预约model
 */

public class CategorySubscribeBean {
    List<AppBean> appBeanList ;

    public CategorySubscribeBean(List<AppBean> appBeanList) {
        this.appBeanList = appBeanList;
    }

    public List<AppBean> getAppBeanList() {
        return appBeanList;
    }
}
