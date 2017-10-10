package com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl;

import com.example.ggxiaozhi.store.the_basket.api.IGetDataDelegate;
import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BasePresenterImpl;
import com.example.ggxiaozhi.store.the_basket.bean.AppIntroductionBean;
import com.example.ggxiaozhi.store.the_basket.mvp.interactor.AppIntroduceInteractor;
import com.example.ggxiaozhi.store.the_basket.mvp.presenter.AppIntroduceFragmentPresent;
import com.example.ggxiaozhi.store.the_basket.mvp.view.fragment_view.AppIntroduceFragmentView;

import javax.inject.Inject;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/26
 * 功能   ：
 */

public class AppIntroduceFragmentPresentImpl extends BasePresenterImpl<AppIntroduceFragmentView> implements AppIntroduceFragmentPresent {

    @Inject
    AppIntroduceInteractor mInteractor;

    @Inject
    public AppIntroduceFragmentPresentImpl() {

    }

    @Override
    public void getAppIntroduceFragmentData(BaseActivity activity, String packageName) {

        mInteractor.AppIntroduceLoad(activity, packageName, new IGetDataDelegate<AppIntroductionBean>() {
            @Override
            public void getDataSuccess(AppIntroductionBean appIntroductionBean) {
                mPresenterView.onAppIntroduceDataSuccess(appIntroductionBean);
            }

            @Override
            public void getDataError(String msg) {
                mPresenterView.onAppIntroduceDataError(msg);
            }
        });
    }
}
