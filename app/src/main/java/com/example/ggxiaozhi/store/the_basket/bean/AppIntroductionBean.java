package com.example.ggxiaozhi.store.the_basket.bean;

import java.util.List;

/**
 * 详情页推荐栏数据
 */

public class AppIntroductionBean {

    private List<String> imageCompressList;//应用截图数据
    private List<String> imagesList;//应用截图数据 与上面是一样的
    private AppInfoBean appInfoBean;
    private List<String> tagList;
    private List<AppDetailInfoBean> appDetailInfoBeanList;//伸缩TextView数据

    public AppIntroductionBean(List<String> imageCompressList, List<String> imagesList, AppInfoBean appInfoBean, List<String> tagList, List<AppDetailInfoBean> appDetailInfoBeanList) {
        this.imageCompressList = imageCompressList;
        this.imagesList = imagesList;
        this.appInfoBean = appInfoBean;
        this.tagList = tagList;
        this.appDetailInfoBeanList = appDetailInfoBeanList;
    }

    public List<String> getImageCompressList() {
        return imageCompressList;
    }

    public List<String> getImagesList() {
        return imagesList;
    }

    public AppInfoBean getAppInfoBean() {
        return appInfoBean;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public List<AppDetailInfoBean> getAppDetailInfoBeanList() {
        return appDetailInfoBeanList;
    }

    public static class AppDetailInfoBean {
        private String body;
        private String title;

        public String getBody() {
            return body;
        }

        public String getTitle() {
            return title;
        }

        public AppDetailInfoBean(String body, String title) {
            this.body = body;
            this.title = title;
        }
    }


    public static class AppInfoBean {
        private String developer;
        private String releaseDate;
        private String size;
        private String tariffDesc;
        private String version;

        public AppInfoBean(String developer, String releaseDate, String size, String tariffDesc, String version) {
            this.developer = developer;
            this.releaseDate = releaseDate;
            this.size = size;
            this.tariffDesc = tariffDesc;
            this.version = version;
        }

        public String getDeveloper() {
            return developer;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public String getSize() {
            return size;
        }

        public String getTariffDesc() {
            return tariffDesc;
        }

        public String getVersion() {
            return version;
        }
    }
}
