package com.example.ggxiaozhi.store.the_basket.mvp.view.fragment_view;

import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BaseView;
import com.example.ggxiaozhi.store.the_basket.bean.CategoryBean;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.view.fragment_view
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/1
 * 时间   ： 19:09
 * 功能   ：分类Fragment请求数据回调接口 T 返回数据Bean对象
 */

public interface CategoryFragmentView<T> extends BaseView{

    /**
     * 获取数据成功回调接口
     */
    void recommendFragmentDataSuccess(T t);

    /**
     * 获取数据失败回调接口
     */
    void recommendFragmentDataError(String msg);
}
