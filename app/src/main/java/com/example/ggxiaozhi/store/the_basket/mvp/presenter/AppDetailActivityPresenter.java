package com.example.ggxiaozhi.store.the_basket.mvp.presenter;

import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BasePresenter;
import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BasePresenterImpl;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity_view.AppDetailActivityView;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.presenter
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/25
 * 时间   ： 14:27
 * 功能   ：
 */

public interface AppDetailActivityPresenter extends BasePresenter<AppDetailActivityView> {

    void getAppDetailData(BaseActivity activity,String packageName);

}
