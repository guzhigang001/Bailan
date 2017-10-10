package com.example.ggxiaozhi.store.the_basket.adapter.top;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.ggxiaozhi.store.the_basket.adapter.AppCommentController;
import com.example.ggxiaozhi.store.the_basket.bean.AppCommentBean;
import com.zhxu.recyclerview.wrapper.HeaderAndFooterWrapper;


/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.adapter.top
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/2
 * 功能   ：评论页头部适配器
 */

public class AppCommentTopWrapper extends HeaderAndFooterWrapper {

    private Context mContext;
    private AppCommentController appCommentController;

    public AppCommentTopWrapper(Context context, RecyclerView.Adapter adapter) {
        super(adapter);
        this.mContext = context;
        appCommentController = new AppCommentController(mContext);
        addHeaderView(appCommentController.getContentView());
    }

    public void addDataAll(AppCommentBean appCommentBean) {
        if (appCommentController != null)
            appCommentController.setData(appCommentBean);
    }
}
