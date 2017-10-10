package com.example.ggxiaozhi.store.the_basket.mvp.view.activity_view;

import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BaseView;
import com.example.ggxiaozhi.store.the_basket.bean.AppDetailBean;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.view.activity_view
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/25
 * 时间   ： 14:20
 * 功能   ：
 */

public interface AppDetailActivityView<T> extends BaseView {

    void getAppDetailDataSuccess(T bean);

    void getAppDetailDataError(String msg);
}
