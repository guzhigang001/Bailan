package com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl;

import com.example.ggxiaozhi.store.the_basket.api.IGetDataDelegate;
import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BasePresenterImpl;
import com.example.ggxiaozhi.store.the_basket.bean.AppRecommendBean;
import com.example.ggxiaozhi.store.the_basket.mvp.interactor.AppRecommendInteractor;
import com.example.ggxiaozhi.store.the_basket.mvp.presenter.AppRecommendFragmentPresenter;
import com.example.ggxiaozhi.store.the_basket.mvp.view.fragment_view.AppRecommendFragmentView;

import javax.inject.Inject;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.presenter
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/29
 * 功能   ：
 */

public class AppRecommendFragmentPresenterImpl extends BasePresenterImpl<AppRecommendFragmentView> implements AppRecommendFragmentPresenter {

    @Inject
    AppRecommendInteractor mInteractor;

    @Inject
    public AppRecommendFragmentPresenterImpl() {

    }

    @Override
    public void getAppRecommendFragmentData(BaseActivity activity, String packageName) {
        mInteractor.AppRecommendLoad(activity, packageName, new IGetDataDelegate<AppRecommendBean>() {
            @Override
            public void getDataSuccess(AppRecommendBean appRecommendBean) {
                mPresenterView.onAppRecommendDataSuccess(appRecommendBean);
            }

            @Override
            public void getDataError(String msg) {
                mPresenterView.onAppRecommendDataError(msg);
            }
        });
    }
}
