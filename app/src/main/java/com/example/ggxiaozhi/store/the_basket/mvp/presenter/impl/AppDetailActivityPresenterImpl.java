package com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl;

import com.example.ggxiaozhi.store.the_basket.api.IGetDataDelegate;
import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BasePresenterImpl;
import com.example.ggxiaozhi.store.the_basket.bean.AppDetailBean;
import com.example.ggxiaozhi.store.the_basket.mvp.interactor.AppDateilActivityInteractor;
import com.example.ggxiaozhi.store.the_basket.mvp.presenter.AppDetailActivityPresenter;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity_view.AppDetailActivityView;

import javax.inject.Inject;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/25
 * 时间   ： 14:31
 * 功能   ：
 */

public class AppDetailActivityPresenterImpl extends BasePresenterImpl<AppDetailActivityView> implements AppDetailActivityPresenter {

    @Inject
    AppDateilActivityInteractor mInteractor;

    @Inject
    public AppDetailActivityPresenterImpl() {

    }

    @Override
    public void getAppDetailData(BaseActivity activity, String packageName) {
        mInteractor.AppDetailLoad(activity, mIGetDataDelegate, packageName);
    }

    IGetDataDelegate<AppDetailBean> mIGetDataDelegate = new IGetDataDelegate<AppDetailBean>() {
        @Override
        public void getDataSuccess(AppDetailBean appDetailBean) {
            mPresenterView.getAppDetailDataSuccess(appDetailBean);
        }

        @Override
        public void getDataError(String msg) {
            mPresenterView.getAppDetailDataError(msg);
        }
    };
}
