package com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl;

import com.example.ggxiaozhi.store.the_basket.api.CategoryApi;
import com.example.ggxiaozhi.store.the_basket.api.IGetDataDelegate;
import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BasePresenterImpl;
import com.example.ggxiaozhi.store.the_basket.bean.CategoryBean;
import com.example.ggxiaozhi.store.the_basket.mvp.interactor.CategoryInteractor;
import com.example.ggxiaozhi.store.the_basket.mvp.presenter.CategoryFragmentPresenter;
import com.example.ggxiaozhi.store.the_basket.mvp.view.fragment_view.CategoryFragmentView;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/1
 * 时间   ： 19:13
 * 功能   ：分类页Presenter接口实现类
 */

public class CategoryFragmentPresenterImpl extends BasePresenterImpl<CategoryFragmentView> implements CategoryFragmentPresenter{

    @Inject
    CategoryInteractor interactor;
    @Inject
    public CategoryFragmentPresenterImpl(){

    }
    @Override
    public void getCategoryFragmentData(BaseActivity activity) {
        interactor.CategoryLoad(activity,listener);
    }

    private IGetDataDelegate<CategoryBean> listener=new IGetDataDelegate<CategoryBean>() {
        @Override
        public void getDataSuccess(CategoryBean categoryBean) {
            mPresenterView.recommendFragmentDataSuccess(categoryBean);
        }

        @Override
        public void getDataError(String msg) {
            mPresenterView.recommendFragmentDataError(msg);
        }
    };

}
