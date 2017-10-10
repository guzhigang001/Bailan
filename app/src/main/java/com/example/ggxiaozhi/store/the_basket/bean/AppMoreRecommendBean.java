package com.example.ggxiaozhi.store.the_basket.bean;

import java.util.List;

/**
 * 详情页中 推荐SubTab更多Activity Model
 */

public class AppMoreRecommendBean {

    private List<AppBean> moreAppBean ;

    public AppMoreRecommendBean(List<AppBean> moreAppBean) {
        this.moreAppBean = moreAppBean;
    }

    public List<AppBean> getMoreAppBean() {
        return moreAppBean;
    }
}
