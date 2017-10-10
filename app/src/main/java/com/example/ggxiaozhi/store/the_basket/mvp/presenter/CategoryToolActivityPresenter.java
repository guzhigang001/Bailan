package com.example.ggxiaozhi.store.the_basket.mvp.presenter;


import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BasePresenter;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity_view.CategoryToolActivityView;
import com.zhxu.recyclerview.pullrefresh.PullToRefreshView;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public interface CategoryToolActivityPresenter extends BasePresenter<CategoryToolActivityView> {

    void getCategoryToolData(BaseActivity activity);

    void getgetCategoryToolMoreData(BaseActivity activity);


}
