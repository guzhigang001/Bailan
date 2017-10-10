package com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl;


import com.example.ggxiaozhi.store.the_basket.api.IGetDataDelegate;
import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BasePresenterImpl;
import com.example.ggxiaozhi.store.the_basket.bean.CategoryToolBean;
import com.example.ggxiaozhi.store.the_basket.mvp.interactor.CategoryToolActivityInteractor;
import com.example.ggxiaozhi.store.the_basket.mvp.presenter.CategoryToolActivityPresenter;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity_view.CategoryToolActivityView;

import javax.inject.Inject;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class CategoryToolActivityPresenterImpl extends BasePresenterImpl<CategoryToolActivityView> implements CategoryToolActivityPresenter {

    @Inject
    public CategoryToolActivityInteractor categoryToolActivityInteractor;

    @Inject
    public CategoryToolActivityPresenterImpl() {

    }

    @Override
    public void getCategoryToolData(BaseActivity activity) {
        categoryToolActivityInteractor.loadCategoryToolData(activity, new IGetDataDelegate<CategoryToolBean>() {
            @Override
            public void getDataSuccess(CategoryToolBean categoryToolBean) {
                mPresenterView.onCategoryToolDataSuccess(categoryToolBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onCategoryToolError(errmsg);
            }
        });
    }

    @Override
    public void getgetCategoryToolMoreData(BaseActivity activity) {
        categoryToolActivityInteractor.loadCategoryToolData(activity, listener);
    }

    private IGetDataDelegate<CategoryToolBean> listener = new IGetDataDelegate<CategoryToolBean>() {
        @Override
        public void getDataSuccess(CategoryToolBean categoryToolBean) {
            mPresenterView.onCategoryToolDataMoreSuccess(categoryToolBean);

        }

        @Override
        public void getDataError(String msg) {
            mPresenterView.onCategoryToolError(msg);
        }
    };

}
