package com.example.ggxiaozhi.store.the_basket.base.mvpbase;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.base.mvpbase
 * 作者名 ： 志先生_
 * 日期   ： 2017/8/26
 * 时间   ： 13:38
 * 功能   ：所有Presenter接口的基类
 */

public interface BasePresenter<T extends BaseView> {

    /**
     * 绑定View
     */
    void attachView(T view);

    /**
     * 解绑View
     */
    void detachView();
}
