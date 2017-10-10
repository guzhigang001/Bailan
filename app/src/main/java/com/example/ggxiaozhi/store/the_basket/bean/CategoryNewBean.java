package com.example.ggxiaozhi.store.the_basket.bean;

import java.util.List;

/**
 * 分类页 首发数据
 */

public class CategoryNewBean {
    private Head head;
    private List<AppBean> appBeanList;

    public CategoryNewBean(Head head, List<AppBean> appBeanList) {
        this.head = head;
        this.appBeanList = appBeanList;
    }

    public Head getHead() {
        return head;
    }

    public List<AppBean> getAppBeanList() {
        return appBeanList;
    }

    public static class Head {
        private String icon;
        private String intro;

        public String getIcon() {
            return icon;
        }

        public String getIntro() {
            return intro;
        }

        public Head(String icon, String intro) {

            this.icon = icon;
            this.intro = intro;
        }
    }
}
