package com.example.ggxiaozhi.store.the_basket.mvp.view.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.ggxiaozhi.store.the_basket.R;
import com.example.ggxiaozhi.store.the_basket.adapter.CategoryToolMultiAdapter;
import com.example.ggxiaozhi.store.the_basket.adapter.top.RecommendTopWrapper;
import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BaseMvpActivity;
import com.example.ggxiaozhi.store.the_basket.bean.CategoryToolBean;
import com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl.CategoryToolActivityPresenterImpl;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity_view.CategoryToolActivityView;
import com.zhxu.recyclerview.pullrefresh.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class CategoryToolActivity extends BaseMvpActivity<CategoryToolActivityPresenterImpl> implements CategoryToolActivityView {

    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.iv_search)
    ImageView iv_search;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.ptr)
    PullToRefreshView ptr;

    @Inject
    public CategoryToolActivityPresenterImpl categoryToolActivityPresenter;

    private CategoryToolBean mCategoryToolBean;
    private List<CategoryToolBean.CategoryToolAppBean> mBeanList = new ArrayList<>();
    private CategoryToolMultiAdapter adapter;

    private RecommendTopWrapper topWrapper;

    @Override
    public void onCategoryToolDataSuccess(CategoryToolBean categoryToolBean) {
        this.mCategoryToolBean = categoryToolBean;
        rv.setLayoutManager(new LinearLayoutManager(this));

        //主体adapter
        adapter = new CategoryToolMultiAdapter(this);
        adapter.addDataAll(categoryToolBean.getCategoryToolAppBeanList());
        //头部轮播图
        topWrapper = new RecommendTopWrapper(this, adapter);
        topWrapper.setData(categoryToolBean.getBannerList());
        adapter.setAppItemListener(new CategoryToolMultiAdapter.AppItemListener() {
            @Override
            public void goAppDetail(String packageName) {
                Intent intent = new Intent(CategoryToolActivity.this, AppDetailActivity.class);
                intent.putExtra("packageName", packageName);
                startActivity(intent);
            }
        });
        rv.setAdapter(topWrapper);
        ptr.setPullDownEnable(false);
        ptr.setListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
                mordData();
            }
        });
    }

    @Override
    public void onCategoryToolDataMoreSuccess(CategoryToolBean categoryToolBean) {
        //添加数据
        mBeanList.addAll(categoryToolBean.getCategoryToolAppBeanList());
        adapter.clearData();
        adapter.addDataAll(mBeanList);
        topWrapper.notifyDataSetChanged();
        ptr.onFinishLoading();
    }

    @Override
    public void onCategoryToolError(String msg) {

    }

    @Override
    protected void initData() {
        categoryToolActivityPresenter.getCategoryToolData(this);
    }

    @Override
    protected CategoryToolActivityPresenterImpl initInjector() {
        build.inject(this);
        return categoryToolActivityPresenter;
    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_category_tool);
    }

    @Override
    protected void initView() {
        String name = getIntent().getStringExtra("name");
        //设置沉浸式状态栏
        setStatus();
        iv_search.setVisibility(View.VISIBLE);
        //设置沉浸式状态栏背景
        bar_layout.setBackgroundResource(R.color.black_alpha_5);
        title_text.setText(name);
    }

    public void mordData() {
        mPresenter.getgetCategoryToolMoreData(this);
    }
}
