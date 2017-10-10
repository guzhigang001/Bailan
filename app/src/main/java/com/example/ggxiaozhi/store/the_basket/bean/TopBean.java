package com.example.ggxiaozhi.store.the_basket.bean;

import java.util.List;
import java.util.Map;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.bean
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/2
 * 时间   ： 12:04
 * 功能   ：排行页数据
 */

public class TopBean {
    private Map<String,List<AppBean>> appBeanMap ;
    private List<TopTopBean> topTopBeanList ;

    public TopBean(Map<String, List<AppBean>> appBeanMap, List<TopTopBean> topTopBeanList) {
        this.appBeanMap = appBeanMap;
        this.topTopBeanList = topTopBeanList;
    }

    public Map<String, List<AppBean>> getAppBeanMap() {
        return appBeanMap;
    }

    public List<TopTopBean> getTopTopBeanList() {
        return topTopBeanList;
    }

    public static class TopTopBean {

        private String iconUrl;
        private String name;

        public TopTopBean(String iconUrl, String name) {
            this.iconUrl = iconUrl;
            this.name = name;
        }

        public String getIconUrl() {
            return iconUrl;
        }

        public String getName() {
            return name;
        }
    }
}
