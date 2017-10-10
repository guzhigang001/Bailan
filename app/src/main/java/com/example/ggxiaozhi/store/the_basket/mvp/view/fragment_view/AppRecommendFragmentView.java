package com.example.ggxiaozhi.store.the_basket.mvp.view.fragment_view;

import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BaseView;
import com.example.ggxiaozhi.store.the_basket.bean.AppRecommendBean;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.view.fragment_view
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/29
 * 功能   ：
 */

public interface AppRecommendFragmentView extends BaseView {

    void onAppRecommendDataSuccess(AppRecommendBean bean);

    void onAppRecommendDataError(String msg);
}
