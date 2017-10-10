package com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl;

import com.example.ggxiaozhi.store.the_basket.api.IGetDataDelegate;
import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BasePresenterImpl;
import com.example.ggxiaozhi.store.the_basket.bean.AppCommentBean;
import com.example.ggxiaozhi.store.the_basket.mvp.interactor.AppCommentarieInteractor;
import com.example.ggxiaozhi.store.the_basket.mvp.presenter.AppCommentarieFragmentPresenter;
import com.example.ggxiaozhi.store.the_basket.mvp.view.fragment_view.AppCommentarieFragmentView;

import javax.inject.Inject;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/28
 * 功能   ：
 */

public class AppCommentarieFragmentPresenterImpl extends BasePresenterImpl<AppCommentarieFragmentView> implements AppCommentarieFragmentPresenter {

    @Inject
    AppCommentarieInteractor mInteractor;

    @Inject
    public AppCommentarieFragmentPresenterImpl() {

    }

    @Override
    public void getAppCommentarieFragmentData(BaseActivity activity, String packageName) {
        mInteractor.AppCommentarieLoad(activity, listener, packageName);
    }

    IGetDataDelegate<AppCommentBean> listener = new IGetDataDelegate<AppCommentBean>() {
        @Override
        public void getDataSuccess(AppCommentBean appCommentBean) {
            mPresenterView.getAppCommentarieDataSuccess(appCommentBean);
        }

        @Override
        public void getDataError(String msg) {
            mPresenterView.getAppCommentarieDataError(msg);
        }
    };

}
