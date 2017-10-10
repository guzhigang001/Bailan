package com.example.ggxiaozhi.store.the_basket.mvp.view.fragment_view;

import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BaseView;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.view.fragment_view
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/26
 * 功能   ：详情页 介绍Fragment View
 */

public interface AppIntroduceFragmentView<T> extends BaseView {

    void onAppIntroduceDataSuccess(T bean);

    void onAppIntroduceDataError(String msg);

}
