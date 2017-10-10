package com.example.ggxiaozhi.store.the_basket.base.mvpbase;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.ggxiaozhi.store.the_basket.R;
import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.base.StoreApplication;
import com.example.ggxiaozhi.store.the_basket.di.component.ActivityComponent;
import com.example.ggxiaozhi.store.the_basket.di.component.DaggerActivityComponent;
import com.example.ggxiaozhi.store.the_basket.di.module.ActivityModule;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.base.mvpbase
 * 作者名 ： 志先生_
 * 日期   ： 2017/8/26
 * 时间   ： 13:51
 * 功能   ：Activity实现Mvp的基类
 */

public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity  {

    protected ActivityComponent build;

    //通过Dagger2注入的其实是Mvp中的 Presenter
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivityComonter();

        mPresenter = initInjector();
        //绑定View
        mPresenter.attachView(this);
        initData();
    }

    /**
     * 加载数据
     */
    protected abstract void initData();

    public void initActivityComonter() {
        build = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComponent(((StoreApplication) getApplication()).getAppComponent())
                .build();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 完成注入并返回注入的Presenter对象
     *
     * @return
     */
    protected abstract T initInjector();

    /**
     * 解绑
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }

    }

}
