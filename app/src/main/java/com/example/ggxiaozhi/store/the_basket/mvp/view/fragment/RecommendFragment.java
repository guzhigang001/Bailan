package com.example.ggxiaozhi.store.the_basket.mvp.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.utils.LogUtils;
import com.example.ggxiaozhi.store.the_basket.R;
import com.example.ggxiaozhi.store.the_basket.adapter.RecommendAdapter;
import com.example.ggxiaozhi.store.the_basket.adapter.top.RecommendTopWrapper;
import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BaseMvpFragment;
import com.example.ggxiaozhi.store.the_basket.bean.RecommendBean;
import com.example.ggxiaozhi.store.the_basket.mvp.presenter.RecommendFragmentPresenter;
import com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl.RecommendFragmentPresenterImpl;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity.AppDetailActivity;
import com.example.ggxiaozhi.store.the_basket.mvp.view.fragment_view.RecommendFragmentView;
import com.example.ggxiaozhi.store.the_basket.utils.UIUtils;
import com.example.ggxiaozhi.store.the_basket.view.LoadingPager;
import com.zhxu.recyclerview.pullrefresh.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecommendFragment extends BaseMvpFragment<RecommendFragmentPresenterImpl> implements RecommendFragmentView<RecommendBean> {

    private static final String TAG = "RecommendFragment";
    @Inject
    RecommendFragmentPresenterImpl mPresenter;

    private RecommendBean mBean;

    @BindView(R.id.rv_recommend)
    RecyclerView mRecyclerView;
    @BindView(R.id.ptr)
    PullToRefreshView ptr;
    private RecommendAdapter adapter;//分类条目的适配器
    private RecommendTopWrapper topWrapper;//添加头部轮播图的视频器
    private List<RecommendBean.RecommendAppBean> mBeanList = new ArrayList<>();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    protected void load() {
        //发起网络请求
        mPresenter.getRecommendFragmentData(mBaseActivity);
    }

    @Override
    protected View cretaeSuccessView() {
        View view = UIUtils.inflate(R.layout.fragment_recommend);
        ButterKnife.bind(this, view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecommendAdapter(getContext(), mBean.getRecommendAppBeanList());
        topWrapper = new RecommendTopWrapper(getContext(), adapter);//为RecycleView添加头部(添加头部轮播图)
        topWrapper.setData(mBean.getBannerList());
        mRecyclerView.setAdapter(topWrapper);
        ptr.setPullDownEnable(false);
        ptr.setListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
                mPresenter.getRecommendFragmentMoreData(mBaseActivity);
            }
        });
        adapter.setOnItemClickListenter(new RecommendAdapter.OnItemClickListenter() {
            @Override
            public void goDetailActivity(String packageName) {
                Intent intent = new Intent(mBaseActivity, AppDetailActivity.class);
                intent.putExtra("packageName", packageName);
                mBaseActivity.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    protected RecommendFragmentPresenterImpl initInjector() {
        //完成依赖注入
        mFragmentComponent.inject(this);
        //返回注入的对象
        return mPresenter;
    }

    /**
     * 初始化数据加载
     *
     * @param recommendBean
     */
    @Override
    public void recommendFragmentDataSuccess(RecommendBean recommendBean) {
        mBean = recommendBean;
        setState(LoadingPager.LoadResult.success);
    }

    /**
     * 加载更多
     *
     * @param recommendBean
     */
    @Override
    public void recommendFragmentMoreDataSuccess(RecommendBean recommendBean) {
        //添加数据
        mBeanList.addAll(recommendBean.getRecommendAppBeanList());
        adapter.clearData();
        adapter.addDataAll(mBeanList);
        topWrapper.notifyDataSetChanged();
        ptr.onFinishLoading();
    }

    @Override
    public void recommendFragmentDataError(String msg) {
        LogUtils.w(TAG, msg);
        setState(LoadingPager.LoadResult.error);
    }
}
