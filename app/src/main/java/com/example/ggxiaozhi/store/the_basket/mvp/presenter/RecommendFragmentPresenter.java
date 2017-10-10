package com.example.ggxiaozhi.store.the_basket.mvp.presenter;

import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BasePresenter;
import com.example.ggxiaozhi.store.the_basket.mvp.view.fragment_view.RecommendFragmentView;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.presenter
 * 作者名 ： 志先生_
 * 日期   ： 2017/8/26
 * 时间   ： 14:42
 * 功能   ：推荐页Presenter接口
 */

public interface RecommendFragmentPresenter extends BasePresenter<RecommendFragmentView>{

    /**
     * 获取推荐页数据
     */
    void getRecommendFragmentData(BaseActivity activity);

    /**
     * 加载更多数据
     */
    void getRecommendFragmentMoreData(BaseActivity activity);
}
