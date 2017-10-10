package com.example.ggxiaozhi.store.the_basket.mvp.view.fragment.appdetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ggxiaozhi.store.the_basket.R;
import com.example.ggxiaozhi.store.the_basket.adapter.section.AppCommentSectionAdapter;
import com.example.ggxiaozhi.store.the_basket.adapter.top.AppCommentTopWrapper;
import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BaseMvpFragment;
import com.example.ggxiaozhi.store.the_basket.bean.AppCommentBean;
import com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl.AppCommentarieFragmentPresenterImpl;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity.AppDetailActivity;
import com.example.ggxiaozhi.store.the_basket.mvp.view.fragment_view.AppCommentarieFragmentView;
import com.example.ggxiaozhi.store.the_basket.utils.UIUtils;
import com.example.ggxiaozhi.store.the_basket.view.LoadingPager;
import com.zhxu.recyclerview.section.SectionRVAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.view.fragment.appdetails
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/26
 * 功能   ：评论Fragment
 */

public class AppCommentarieFragment extends BaseMvpFragment<AppCommentarieFragmentPresenterImpl> implements AppCommentarieFragmentView {
    private static final String TAG = "AppCommentarieFragment";
    private String packageName;
    private AppCommentBean mAppCommentBean;
    @BindView(R.id.rv)
    RecyclerView rv;
    @Inject
    AppCommentarieFragmentPresenterImpl mPresenter;
    private List<AppCommentBean.CommentsBean> hotList = new ArrayList<>();//热门评论
    private List<AppCommentBean.CommentsBean> list = new ArrayList<>();//全部评论

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.packageName = ((AppDetailActivity) getActivity()).getAppPackageName();
        show();
    }

    @Override
    protected AppCommentarieFragmentPresenterImpl initInjector() {
        mFragmentComponent.inject(this);
        return mPresenter;
    }

    @Override
    protected View cretaeSuccessView() {
        View view = UIUtils.inflate(R.layout.fragment_app_comment);
        ButterKnife.bind(this, view);
        List<AppCommentBean.CommentsBean> comments = mAppCommentBean.getComments();
        for (AppCommentBean.CommentsBean commentsBean : comments) {
            //type为1是精彩评论
            if (commentsBean.getCommentType().equals("1")) {
                hotList.add(commentsBean);
            } else {
                list.add(commentsBean);
            }
        }
        SectionRVAdapter sectionRVAdapter = new SectionRVAdapter(getContext());
        if (hotList.size() > 0)
            sectionRVAdapter.addSection(new AppCommentSectionAdapter(getContext(), "热门评论", hotList));
        if (list.size() > 0)
            sectionRVAdapter.addSection(new AppCommentSectionAdapter(getContext(), "全部评论", list));
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        AppCommentTopWrapper wrapper = new AppCommentTopWrapper(getContext(), sectionRVAdapter);
        wrapper.addDataAll(mAppCommentBean);
        rv.setAdapter(wrapper);
        return view;
    }

    @Override
    protected void load() {
        mPresenter.getAppCommentarieFragmentData(mBaseActivity, packageName);
    }

    @Override
    public void getAppCommentarieDataSuccess(AppCommentBean bean) {
        setState(LoadingPager.LoadResult.success);
        this.mAppCommentBean = bean;
        Log.d(TAG, "getAppCommentarieDataSuccess: " + bean.getRatingDstList().size());
    }

    @Override
    public void getAppCommentarieDataError(String msg) {
        setState(LoadingPager.LoadResult.error);

    }
}
