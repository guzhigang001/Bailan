package com.example.ggxiaozhi.store.the_basket.mvp.presenter;

import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BasePresenter;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity_view.CategoryNecessaryActivityView;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.presenter
 * 作者名 ： 志先生_
 * 日期   ： 2017/10/1
 * 功能   ：
 */

public interface CategoryNecessaryPresenter extends BasePresenter<CategoryNecessaryActivityView> {

    void getCategoryNecessaryData(BaseActivity activity);
}
