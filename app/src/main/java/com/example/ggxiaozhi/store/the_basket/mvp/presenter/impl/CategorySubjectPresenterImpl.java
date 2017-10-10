package com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl;

import com.example.ggxiaozhi.store.the_basket.api.IGetDataDelegate;
import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BasePresenterImpl;
import com.example.ggxiaozhi.store.the_basket.mvp.interactor.CategorySubjectInteractor;
import com.example.ggxiaozhi.store.the_basket.mvp.presenter.CategorySubjectPresenter;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity_view.CategorySubjectActivityView;

import java.util.List;

import javax.inject.Inject;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl
 * 作者名 ： 志先生_
 * 日期   ： 2017/10/1
 * 功能   ：
 */

public class CategorySubjectPresenterImpl extends BasePresenterImpl<CategorySubjectActivityView> implements CategorySubjectPresenter {

    @Inject
    CategorySubjectInteractor mInteractor;

    @Inject
    public CategorySubjectPresenterImpl() {

    }

    @Override
    public void getCategorySubjectData(BaseActivity activity) {
        mInteractor.loadCategorySubjectData(activity, new IGetDataDelegate<List<String>>() {
            @Override
            public void getDataSuccess(List<String> strings) {
                mPresenterView.CategorySubjectDataSuccess(strings);
            }

            @Override
            public void getDataError(String msg) {
                mPresenterView.CategorySubjectDataError(msg);
            }
        });
    }
}
