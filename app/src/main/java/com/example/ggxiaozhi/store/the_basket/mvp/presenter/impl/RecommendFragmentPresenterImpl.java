package com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl;

import android.os.SystemClock;

import com.example.ggxiaozhi.store.the_basket.api.IGetDataDelegate;
import com.example.ggxiaozhi.store.the_basket.api.RecommendApi;
import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BasePresenterImpl;
import com.example.ggxiaozhi.store.the_basket.bean.RecommendBean;
import com.example.ggxiaozhi.store.the_basket.mvp.interactor.RecommendInteractor;
import com.example.ggxiaozhi.store.the_basket.mvp.presenter.RecommendFragmentPresenter;
import com.example.ggxiaozhi.store.the_basket.mvp.view.fragment_view.RecommendFragmentView;

import javax.inject.Inject;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl
 * 作者名 ： 志先生_
 * 日期   ： 2017/8/26
 * 时间   ： 14:45
 * 功能   ：推荐页Presenter接口
 */

public class RecommendFragmentPresenterImpl extends BasePresenterImpl<RecommendFragmentView> implements RecommendFragmentPresenter{

    @Inject
    RecommendInteractor mInteractor;

    @Inject
    public RecommendFragmentPresenterImpl(){


    }

    @Override
    public void getRecommendFragmentData(BaseActivity activity) {
        mInteractor.LoadRecommend(activity,listener);
    }

    @Override
    public void getRecommendFragmentMoreData(BaseActivity activity) {
        mInteractor.LoadRecommend(activity,listener1);
    }


    private IGetDataDelegate<RecommendBean> listener=new IGetDataDelegate<RecommendBean>() {
        @Override
        public void getDataSuccess(RecommendBean recommendBean) {
            mPresenterView.recommendFragmentDataSuccess(recommendBean);
        }

        @Override
        public void getDataError(String msg) {
            mPresenterView.recommendFragmentDataError(msg);
        }
    };
    private IGetDataDelegate<RecommendBean> listener1=new IGetDataDelegate<RecommendBean>() {
        @Override
        public void getDataSuccess(RecommendBean recommendBean) {
            mPresenterView.recommendFragmentMoreDataSuccess(recommendBean);
        }

        @Override
        public void getDataError(String msg) {
            mPresenterView.recommendFragmentDataError(msg);
        }
    };
}
