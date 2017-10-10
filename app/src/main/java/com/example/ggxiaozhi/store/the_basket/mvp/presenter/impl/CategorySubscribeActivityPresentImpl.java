package com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl;

import com.example.ggxiaozhi.store.the_basket.api.IGetDataDelegate;
import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BasePresenterImpl;
import com.example.ggxiaozhi.store.the_basket.bean.CategorySubscribeBean;
import com.example.ggxiaozhi.store.the_basket.mvp.interactor.CategorySubscribeInteractor;
import com.example.ggxiaozhi.store.the_basket.mvp.presenter.CategorySubscribeActivityPresent;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity_view.CategorySubscribeActivityView;

import javax.inject.Inject;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/30
 * 功能   ：
 */

public class CategorySubscribeActivityPresentImpl extends BasePresenterImpl<CategorySubscribeActivityView> implements CategorySubscribeActivityPresent {

    @Inject
    CategorySubscribeInteractor mInteractor;

    @Inject
    public CategorySubscribeActivityPresentImpl(){

    }

    @Override
    public void getCategorySubscribeData(BaseActivity activity) {
        mInteractor.loadCategorySubscribe(activity, new IGetDataDelegate<CategorySubscribeBean>() {
            @Override
            public void getDataSuccess(CategorySubscribeBean bean) {
                mPresenterView.getCategorySubscribeDataSuccess(bean);
            }

            @Override
            public void getDataError(String msg) {
                mPresenterView.getCategorySubscribeDataError(msg);
            }
        });
    }
}
