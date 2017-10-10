package com.example.ggxiaozhi.store.the_basket.adapter.top;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.ggxiaozhi.store.the_basket.banner.RecommendController;
import com.zhxu.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.List;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.adapter.top
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/2
 * 时间   ： 19:23
 * 功能   ：轮播图适配器 为RecycleView添加头部
 */

public class RecommendTopWrapper extends HeaderAndFooterWrapper{

    private Context mContext;
    private RecommendController mController;//自定义轮播图
    public RecommendTopWrapper(Context context,RecyclerView.Adapter adapter) {
        super(adapter);
        this.mContext=context;
        mController=new RecommendController(context);
        addHeaderView(mController.getContentView());
    }
    public void setData(List<String> urls){
        if (mController!=null){
            //为轮播图添加数据
            mController.setData(urls);
        }
    }
}
