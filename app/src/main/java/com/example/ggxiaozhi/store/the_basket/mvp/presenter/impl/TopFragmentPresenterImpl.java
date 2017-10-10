package com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl;

import com.example.ggxiaozhi.store.the_basket.api.IGetDataDelegate;
import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BasePresenterImpl;
import com.example.ggxiaozhi.store.the_basket.bean.TopBean;
import com.example.ggxiaozhi.store.the_basket.mvp.interactor.TopInteractor;
import com.example.ggxiaozhi.store.the_basket.mvp.presenter.TopFragmentPresenter;
import com.example.ggxiaozhi.store.the_basket.mvp.view.fragment_view.TopFragmentView;

import javax.inject.Inject;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/2
 * 时间   ： 12:00
 * 功能   ：
 */

public class TopFragmentPresenterImpl extends BasePresenterImpl<TopFragmentView> implements TopFragmentPresenter {

    @Inject
    TopInteractor mInteractor;

    @Inject
    public TopFragmentPresenterImpl() {

    }

    @Override
    public void getTopFragmentData(BaseActivity activity) {

        mInteractor.loadTop(activity,listener);
    }
    private IGetDataDelegate<TopBean> listener=new IGetDataDelegate<TopBean>() {
        @Override
        public void getDataSuccess(TopBean topBean) {
            mPresenterView.topFragmentDataSuccess(topBean);
        }

        @Override
        public void getDataError(String msg) {
            mPresenterView.topFragmentDataError(msg);
        }
    };
}
