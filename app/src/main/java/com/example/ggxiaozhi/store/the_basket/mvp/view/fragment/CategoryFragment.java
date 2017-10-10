package com.example.ggxiaozhi.store.the_basket.mvp.view.fragment;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.ggxiaozhi.store.the_basket.R;
import com.example.ggxiaozhi.store.the_basket.adapter.section.CategroySectionAdapter;
import com.example.ggxiaozhi.store.the_basket.adapter.top.CategoryTopWrapper;
import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BaseMvpFragment;
import com.example.ggxiaozhi.store.the_basket.bean.CategoryBean;
import com.example.ggxiaozhi.store.the_basket.mvp.presenter.CategoryFragmentPresenter;
import com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl.CategoryFragmentPresenterImpl;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity.CategoryNecessaryActivity;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity.CategoryNewActivity;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity.CategorySubjectActivity;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity.CategorySubscribeActivity;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity.CategoryToolActivity;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity.HomeActivity;
import com.example.ggxiaozhi.store.the_basket.mvp.view.fragment_view.CategoryFragmentView;
import com.example.ggxiaozhi.store.the_basket.utils.UIUtils;
import com.example.ggxiaozhi.store.the_basket.view.LoadingPager;
import com.example.ggxiaozhi.store.the_basket.view.widget.ViewUpSearch;
import com.zhxu.recyclerview.layoutmanager.PullRecyclerViewGroup;
import com.zhxu.recyclerview.section.SectionRVAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CategoryFragment extends BaseMvpFragment<CategoryFragmentPresenterImpl> implements CategoryFragmentView<CategoryBean> {

    private static final String TAG = "CategoryFragment";
    @Inject
    CategoryFragmentPresenterImpl mPresenter;

    @BindView(R.id.rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.search)
    ViewUpSearch mViewUpSearch;//悬浮搜索框
    @BindView(R.id.PullRecyclerViewGroup)
    PullRecyclerViewGroup mPullRecyclerViewGroup;
    private boolean isExpend = true;//标记悬浮框是否打开
    CategoryBean mCategoryBean;

    @Override
    protected View cretaeSuccessView() {
        View view = UIUtils.inflate(R.layout.fragment_category);
        ButterKnife.bind(this, view);
        mPullRecyclerViewGroup.setMoveViews(mViewUpSearch);//将搜索栏加入回弹效果
        SectionRVAdapter rvAdapter = new SectionRVAdapter(getContext());
        CategoryTopWrapper wrapper = new CategoryTopWrapper(rvAdapter, getContext());
        wrapper.addData(mCategoryBean.getCategoryTopBeanList());
        final CategroySectionAdapter categroySectionAdapter = new CategroySectionAdapter(mCategoryBean.getTitle(), mCategoryBean.getCategoryDataBeanList(), getContext());
        rvAdapter.addSection(categroySectionAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(wrapper);
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

        categroySectionAdapter.setOnItemClickListener(new CategroySectionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), CategoryToolActivity.class);
                intent.putExtra("name", mCategoryBean.getCategoryDataBeanList().get(position).getName());
                ((HomeActivity) getActivity()).startActivity(intent);
            }
        });
        /*分类页 预约 必备 首发 专题*/
        wrapper.setOnItemClick(new CategoryTopWrapper.OnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                if (position == 0)/*预约*/
                    mBaseActivity.startActivity(new Intent(getActivity(), CategorySubscribeActivity.class));
                if (position == 1)/*必备*/
                    mBaseActivity.startActivity(new Intent(getActivity(), CategoryNecessaryActivity.class));
                if (position == 2)/*首发*/
                    mBaseActivity.startActivity(new Intent(getActivity(), CategoryNewActivity.class));
                if (position == 3)/*专题*/
                    mBaseActivity.startActivity(new Intent(getActivity(), CategorySubjectActivity.class));
            }
        });
        return view;
    }

    @Override
    protected void load() {
        mPresenter.getCategoryFragmentData(mBaseActivity);
    }

    @Override
    public void recommendFragmentDataSuccess(CategoryBean categoryBean) {
        mCategoryBean = categoryBean;
        setState(LoadingPager.LoadResult.success);
    }

    @Override
    public void recommendFragmentDataError(String msg) {
        setState(LoadingPager.LoadResult.error);
    }

    @Override
    protected CategoryFragmentPresenterImpl initInjector() {
        //完成注入
        mFragmentComponent.inject(this);
        //返回注入的对象
        return mPresenter;
    }
}
