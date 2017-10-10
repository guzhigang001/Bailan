package com.example.ggxiaozhi.store.the_basket.mvp.view.fragment_view;

import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BaseView;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.view.fragment_view
 * 作者名 ： 志先生_
 * 日期   ： 2017/8/26
 * 时间   ： 14:39
 * 功能   ：推荐Fragment请求数据回调接口 T 返回数据Bean对象
 */

public interface RecommendFragmentView<T> extends BaseView{

    /**
     * 获取数据成功回调接口
     */
    void recommendFragmentDataSuccess(T t);

    /**
     * 获取数据成功回调接口(加载更多)
     */
    void recommendFragmentMoreDataSuccess(T t);

    /**
     * 获取数据失败回调接口
     */
    void recommendFragmentDataError(String msg);
}
