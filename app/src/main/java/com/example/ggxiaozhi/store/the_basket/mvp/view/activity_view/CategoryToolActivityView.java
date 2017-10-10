package com.example.ggxiaozhi.store.the_basket.mvp.view.activity_view;


import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BaseView;
import com.example.ggxiaozhi.store.the_basket.bean.CategoryToolBean;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public interface CategoryToolActivityView extends BaseView {
    void onCategoryToolDataSuccess(CategoryToolBean categoryToolBean);

    void onCategoryToolDataMoreSuccess(CategoryToolBean categoryToolBean);

    void onCategoryToolError(String msg);
}
