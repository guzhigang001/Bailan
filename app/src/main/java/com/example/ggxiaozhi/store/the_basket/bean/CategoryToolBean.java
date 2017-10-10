package com.example.ggxiaozhi.store.the_basket.bean;

import java.util.List;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class CategoryToolBean {

    private List<String> bannerList ;
    private List<CategoryToolAppBean> categoryToolAppBeanList;

    public CategoryToolBean(List<String> bannerList, List<CategoryToolAppBean> recommendAppBeanList) {
        this.bannerList = bannerList;
        this.categoryToolAppBeanList = recommendAppBeanList;
    }

    public List<String> getBannerList() {
        return bannerList;
    }

    public List<CategoryToolAppBean> getCategoryToolAppBeanList() {
        return categoryToolAppBeanList;
    }

    public static class CategoryToolAppBean {
        /** 标题 */
        private String title ;
        /** 广告 */
        private List<String> iconList ;
        /** 应用集合 */
        private List<AppBean> appList ;
        private int type = 0 ;

        public CategoryToolAppBean(String title, List<String> iconList, List<AppBean> appList, int type) {
            this.title = title;
            this.iconList = iconList;
            this.appList = appList;
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public List<String> getIconList() {
            return iconList;
        }

        public List<AppBean> getAppList() {
            return appList;
        }

        public int getType() {
            return type;
        }
    }
}
