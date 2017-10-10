package com.example.ggxiaozhi.store.the_basket.base;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ggxiaozhi.store.the_basket.R;
import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BaseView;
import com.example.ggxiaozhi.store.the_basket.view.LoadingPager;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.base
 * 作者名 ： 志先生_
 * 日期   ： 2017/8/22
 * 时间   ： 17:46
 * 功能   ： 所有Fragment的基类
 */

public abstract class BaseFragment extends Fragment implements BaseView {

    protected BaseActivity mBaseActivity;
    LoadingPager mLoadingPager;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mBaseActivity = (BaseActivity) getActivity();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mLoadingPager == null) {
            mLoadingPager = new LoadingPager(getContext()) {
                @Override
                protected View cretaeSuccessView() {
                    return BaseFragment.this.cretaeSuccessView();
                }

                @Override
                protected void load() {
                    BaseFragment.this.load();
                }
            };
        }
        return mLoadingPager;
    }

    /**
     * 调用父类的方法(请求网络 修改状态)
     */
    public void show() {
        if (mLoadingPager != null) {
            mLoadingPager.show();
        }
    }

    /**
     * 调用父类的方法(设置状态)
     *
     * @param result
     */
    public void setState(LoadingPager.LoadResult result) {
        if (mLoadingPager != null) {
            mLoadingPager.setState(result);
        }
    }

    /**
     * 加载成功的界面
     *
     * @return 显示子类相应的布局
     */
    protected abstract View cretaeSuccessView();

    /**
     * 请求网络 根据结果传入相应状态
     */
    protected abstract void load();

    @Override
    public void showToast(String msg) {
        mBaseActivity.showToast(msg);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);

    }
}
