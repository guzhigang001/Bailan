package com.example.ggxiaozhi.store.the_basket.mvp.view.fragment;



import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ggxiaozhi.store.the_basket.R;
import com.example.ggxiaozhi.store.the_basket.adapter.section.TopSectionAdapter;
import com.example.ggxiaozhi.store.the_basket.adapter.top.TopTopWrapper;
import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BaseMvpFragment;
import com.example.ggxiaozhi.store.the_basket.bean.AppBean;
import com.example.ggxiaozhi.store.the_basket.bean.TopBean;
import com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl.TopFragmentPresenterImpl;
import com.example.ggxiaozhi.store.the_basket.mvp.view.fragment_view.TopFragmentView;
import com.example.ggxiaozhi.store.the_basket.utils.UIUtils;
import com.example.ggxiaozhi.store.the_basket.view.LoadingPager;
import com.example.ggxiaozhi.store.the_basket.view.widget.ViewUpSearch;
import com.zhxu.recyclerview.layoutmanager.PullRecyclerViewGroup;
import com.zhxu.recyclerview.section.SectionRVAdapter;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopFragment extends BaseMvpFragment<TopFragmentPresenterImpl> implements TopFragmentView<TopBean> {

    private static final String TAG = "TopFragment";
    @Inject
    TopFragmentPresenterImpl mPresenter;

    @BindView(R.id.rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.search)
    ViewUpSearch mViewUpSearch;//悬浮搜索框
    @BindView(R.id.PullRecyclerViewGroup)
    PullRecyclerViewGroup mPullRecyclerViewGroup;

    private boolean isExpend = true;//标记悬浮框是否打开

    private TopBean mTopBean;

    private TopSectionAdapter mAdapter;


    @Override
    protected View cretaeSuccessView() {


        View view = UIUtils.inflate(R.layout.fragment_top);
        ButterKnife.bind(this, view);

        mPullRecyclerViewGroup.setMoveViews(mViewUpSearch);//将搜索栏加入回弹效果
        SectionRVAdapter sectionRVAdapter = new SectionRVAdapter(getContext());
        TopTopWrapper topWrapper = new TopTopWrapper(sectionRVAdapter, getContext());
        topWrapper.addData(mTopBean.getTopTopBeanList());
        Map<String, List<AppBean>> appBeanMap = mTopBean.getAppBeanMap();
        Set<String> strings = appBeanMap.keySet();
        for (String str : strings) {
            List<AppBean> appBeen = appBeanMap.get(str);
            mAdapter = new TopSectionAdapter(appBeen, str, getContext());
            sectionRVAdapter.addSection(mAdapter);
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(topWrapper);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            /**
             *
             * @param recyclerView
             * @param dx 滑动X轴坐标
             * @param dy 滑动Y轴坐标 上滑为正 下拉为负
             */
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //获得RecyclerView第一个可见的Item (这里头部的4个图片即为第一个Item-->0)
                int firstVisibleItemPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                if (firstVisibleItemPosition == 0 && dy > 0 && isExpend) {
                    mViewUpSearch.updateShow(!isExpend);
                    isExpend = false;
                } else if (firstVisibleItemPosition == 0 && dy < 0 && !isExpend) {
                    mViewUpSearch.updateShow(!isExpend);
                    isExpend = true;
                } else if (firstVisibleItemPosition == 1 && dy < 0 && isExpend) {
                    mViewUpSearch.updateShow(!isExpend);
                    isExpend = false;
                }
            }
        });

        return view;
    }

    @Override
    protected void load() {
        mPresenter.getTopFragmentData(mBaseActivity);
    }

    @Override
    public void topFragmentDataSuccess(TopBean topBean) {
        this.mTopBean = topBean;
        setState(LoadingPager.LoadResult.success);
    }

    @Override
    public void topFragmentDataError(String msg) {
        setState(LoadingPager.LoadResult.error);
    }

    @Override
    protected TopFragmentPresenterImpl initInjector() {
        mFragmentComponent.inject(this);
        return mPresenter;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.updataDownState();
    }

}
