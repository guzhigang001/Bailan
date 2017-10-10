package com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl;

import com.example.ggxiaozhi.store.the_basket.api.IGetDataDelegate;
import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BasePresenterImpl;
import com.example.ggxiaozhi.store.the_basket.bean.CategoryNewBean;
import com.example.ggxiaozhi.store.the_basket.mvp.interactor.CategoryNewInteractor;
import com.example.ggxiaozhi.store.the_basket.mvp.presenter.CategoryNewPresenter;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity_view.CategoryNewActivityView;

import javax.inject.Inject;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl
 * 作者名 ： 志先生_
 * 日期   ： 2017/10/1
 * 功能   ：
 */

public class CategoryNewPresenterImpl extends BasePresenterImpl<CategoryNewActivityView> implements CategoryNewPresenter {


    @Inject
    CategoryNewInteractor mInteractor;

    @Inject
    public CategoryNewPresenterImpl() {

    }

    @Override
    public void getCategoryNewData(BaseActivity activity) {
        mInteractor.loadCategoryNewData(activity, new IGetDataDelegate<CategoryNewBean>() {
            @Override
            public void getDataSuccess(CategoryNewBean categoryNewBean) {
                mPresenterView.getCategoryNewDataSuccess(categoryNewBean);
            }

            @Override
            public void getDataError(String msg) {
                mPresenterView.getCategoryNewDataError(msg);
            }
        });
    }
}
