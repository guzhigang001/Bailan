package com.example.ggxiaozhi.store.the_basket.bean;

import java.util.List;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.bean
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/1
 * 时间   ： 19:10
 * 功能   ：分类页数据
 */

public class CategoryBean {

    private String title ;
    private List<CategoryTopBean> categoryTopBeanList ;
    private List<CategoryDataBean> categoryDataBeanList ;

    public CategoryBean(String title,List<CategoryTopBean> categoryTopBeanList, List<CategoryDataBean> categoryDataBeanList) {
        this.title = title ;
        this.categoryTopBeanList = categoryTopBeanList;
        this.categoryDataBeanList = categoryDataBeanList;
    }

    public String getTitle() {
        return title;
    }

    public List<CategoryTopBean> getCategoryTopBeanList() {
        return categoryTopBeanList;
    }

    public List<CategoryDataBean> getCategoryDataBeanList() {
        return categoryDataBeanList;
    }

    public static class CategoryTopBean {

        private String iconUrl ;
        private String name ;

        public CategoryTopBean(String iconUrl, String name) {
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


    public static class CategoryDataBean {
        private String iconUrl ;
        private String name ;

        public CategoryDataBean(String iconUrl, String name) {
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
