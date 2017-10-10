package com.example.ggxiaozhi.store.the_basket.mvp.view.activity_view;

import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BaseView;
import com.example.ggxiaozhi.store.the_basket.bean.CategoryNewBean;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.view.activity_view
 * 作者名 ： 志先生_
 * 日期   ： 2017/10/1
 * 功能   ：
 */

public interface CategoryNewActivityView extends BaseView {

    void getCategoryNewDataSuccess(CategoryNewBean bean);

    void getCategoryNewDataError(String msg);
}
