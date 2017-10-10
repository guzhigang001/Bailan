package com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl;

import com.example.ggxiaozhi.store.the_basket.api.IGetDataDelegate;
import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BasePresenterImpl;
import com.example.ggxiaozhi.store.the_basket.bean.AppMoreRecommendBean;
import com.example.ggxiaozhi.store.the_basket.mvp.interactor.AppMoreRecommendInteractor;
import com.example.ggxiaozhi.store.the_basket.mvp.presenter.AppMoreRecommendPresent;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity_view.AppMoreRecommendActivityView;

import javax.inject.Inject;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/30
 * 功能   ：
 */

public class AppMoreRecommendPresentImpl extends BasePresenterImpl<AppMoreRecommendActivityView> implements AppMoreRecommendPresent {

    @Inject
    AppMoreRecommendInteractor mInteractor;

    @Inject
    public AppMoreRecommendPresentImpl() {

    }

    @Override
    public void getAppMoreRecommendDate(BaseActivity activity, String type, String packageName) {
        mInteractor.loadAppMoreRecommendData(activity, type, packageName, listener);
    }

    private IGetDataDelegate<AppMoreRecommendBean> listener = new IGetDataDelegate<AppMoreRecommendBean>() {
        @Override
        public void getDataSuccess(AppMoreRecommendBean appMoreRecommendBean) {
            mPresenterView.getAppMoreRecommendDataSuccess(appMoreRecommendBean);
        }

        @Override
        public void getDataError(String msg) {
            mPresenterView.getAppMoreRecommendDataError(msg);
        }
    };

}
