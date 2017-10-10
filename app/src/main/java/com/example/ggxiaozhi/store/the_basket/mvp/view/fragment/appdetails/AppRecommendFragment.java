package com.example.ggxiaozhi.store.the_basket.mvp.view.fragment.appdetails;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ggxiaozhi.store.the_basket.R;
import com.example.ggxiaozhi.store.the_basket.adapter.section.AppRecommendHotSection;
import com.example.ggxiaozhi.store.the_basket.adapter.section.AppRecommendPopularSection;
import com.example.ggxiaozhi.store.the_basket.adapter.section.AppRecommendTasteSection;
import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BaseMvpFragment;
import com.example.ggxiaozhi.store.the_basket.bean.AppRecommendBean;
import com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl.AppRecommendFragmentPresenterImpl;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity.AppDetailActivity;
import com.example.ggxiaozhi.store.the_basket.mvp.view.fragment_view.AppRecommendFragmentView;
import com.example.ggxiaozhi.store.the_basket.utils.UIUtils;
import com.example.ggxiaozhi.store.the_basket.view.LoadingPager;
import com.zhxu.recyclerview.section.SectionRVAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.view.fragment.appdetails
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/26
 * 功能   ：推荐Fragment
 */

public class AppRecommendFragment extends BaseMvpFragment<AppRecommendFragmentPresenterImpl> implements AppRecommendFragmentView {

    @BindView(R.id.rv)
    RecyclerView rv;
    @Inject
    AppRecommendFragmentPresenterImpl mPresenter;

    private String packageName;

    private AppRecommendBean mAppRecommedBean;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        packageName = ((AppDetailActivity) getActivity()).getAppPackageName();
        show();
    }

    @Override
    protected AppRecommendFragmentPresenterImpl initInjector() {
        mFragmentComponent.inject(this);
        return mPresenter;
    }

    @Override
    protected View cretaeSuccessView() {
        View view = UIUtils.inflate(R.layout.fragment_app_recommend);
        ButterKnife.bind(this,view);

        SectionRVAdapter adapter = new SectionRVAdapter(getContext());
        adapter.addSection(new AppRecommendPopularSection(getContext(),"流行应用",mAppRecommedBean.getPopularAppBeanList(),packageName));
        adapter.addSection(new AppRecommendTasteSection(getContext(),"兴趣相近的用户也安装了",mAppRecommedBean.getTasteAppBeanList(),packageName));
        adapter.addSection(new AppRecommendHotSection(getContext(),"本周热议的社区应用",mAppRecommedBean .getHotAppBeanList(),packageName));
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    protected void load() {
        mPresenter.getAppRecommendFragmentData(mBaseActivity, packageName);
    }

    @Override
    public void onAppRecommendDataSuccess(AppRecommendBean bean) {
        this.mAppRecommedBean = bean;
        setState(LoadingPager.LoadResult.success);
    }

    @Override
    public void onAppRecommendDataError(String msg) {
        setState(LoadingPager.LoadResult.error);
    }
}
