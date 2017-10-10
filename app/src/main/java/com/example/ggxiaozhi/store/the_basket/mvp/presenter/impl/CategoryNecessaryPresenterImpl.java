package com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl;

import com.example.ggxiaozhi.store.the_basket.api.IGetDataDelegate;
import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BasePresenterImpl;
import com.example.ggxiaozhi.store.the_basket.bean.CategoryNecessaryBean;
import com.example.ggxiaozhi.store.the_basket.mvp.interactor.CategoryNecessaryInteractor;
import com.example.ggxiaozhi.store.the_basket.mvp.presenter.CategoryNecessaryPresenter;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity_view.CategoryNecessaryActivityView;

import javax.inject.Inject;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl
 * 作者名 ： 志先生_
 * 日期   ： 2017/10/1
 * 功能   ：
 */

public class CategoryNecessaryPresenterImpl extends BasePresenterImpl<CategoryNecessaryActivityView> implements CategoryNecessaryPresenter {

    @Inject
    CategoryNecessaryInteractor mInteractor;

    @Inject
    public CategoryNecessaryPresenterImpl() {

    }

    @Override
    public void getCategoryNecessaryData(BaseActivity activity) {
        mInteractor.CategoryNecessaryInteractor(activity, new IGetDataDelegate<CategoryNecessaryBean>() {
            @Override
            public void getDataSuccess(CategoryNecessaryBean bean) {
                mPresenterView.getCategoryNecessaryDataSuccess(bean);
            }

            @Override
            public void getDataError(String msg) {
                mPresenterView.getCategoryNecessaryDataError(msg);
            }
        });
    }
}
