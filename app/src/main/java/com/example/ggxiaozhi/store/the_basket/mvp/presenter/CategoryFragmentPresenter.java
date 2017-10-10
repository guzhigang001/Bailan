package com.example.ggxiaozhi.store.the_basket.mvp.presenter;

import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BasePresenter;
import com.example.ggxiaozhi.store.the_basket.mvp.view.fragment_view.CategoryFragmentView;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.presenter
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/1
 * 时间   ： 19:08
 * 功能   ：分类页Presenter接口
 */

public interface CategoryFragmentPresenter extends BasePresenter<CategoryFragmentView>{

    /**
     * 获取分类数据
     */
    void getCategoryFragmentData(BaseActivity activity);
}
