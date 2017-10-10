package com.example.ggxiaozhi.store.the_basket.mvp.view.fragment_view;

import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BaseView;
import com.example.ggxiaozhi.store.the_basket.bean.AppCommentBean;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.view.fragment_view
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/28
 * 功能   ：
 */

public interface AppCommentarieFragmentView extends BaseView {

    void getAppCommentarieDataSuccess(AppCommentBean bean);

    void getAppCommentarieDataError(String msg);
}
