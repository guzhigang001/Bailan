package com.example.ggxiaozhi.store.the_basket.base.mvpbase;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.base.mvpbase
 * 作者名 ： 志先生_
 * 日期   ： 2017/8/26
 * 时间   ： 13:42
 * 功能   ：所有的Presenter都有绑定和解绑的操作，所以抽取到基类当中
 */

public class BasePresenterImpl<T extends BaseView> implements BasePresenter<T>{

    protected T mPresenterView;

    @Override
    public void attachView(T view) {
        this.mPresenterView=view;
    }

    @Override
    public void detachView() {
        this.mPresenterView=null;
    }
}
