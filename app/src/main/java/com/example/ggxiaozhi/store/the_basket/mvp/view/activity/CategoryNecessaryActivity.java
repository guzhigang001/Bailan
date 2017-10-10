package com.example.ggxiaozhi.store.the_basket.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ggxiaozhi.store.the_basket.R;
import com.example.ggxiaozhi.store.the_basket.adapter.top.CategoryNecessaryTopWrapper;
import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BaseMvpActivity;
import com.example.ggxiaozhi.store.the_basket.bean.AppBean;
import com.example.ggxiaozhi.store.the_basket.bean.CategoryNecessaryBean;
import com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl.CategoryNecessaryPresenterImpl;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity_view.CategoryNecessaryActivityView;
import com.example.ggxiaozhi.store.the_basket.utils.DownLoadController;
import com.example.ggxiaozhi.store.the_basket.view.widget.DownloadProgressButton;
import com.zhxu.recyclerview.adapter.CommonAdapter;
import com.zhxu.recyclerview.adapter.MultiItemTypeAdapter;
import com.zhxu.recyclerview.base.ViewHolder;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 必备
 */
public class CategoryNecessaryActivity extends BaseMvpActivity<CategoryNecessaryPresenterImpl> implements CategoryNecessaryActivityView {

    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.iv_search)
    ImageView iv_search;
    @BindView(R.id.rv)
    RecyclerView rv;

    @Inject
    CategoryNecessaryPresenterImpl mPresenter;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_category_necessary);
    }

    @Override
    protected void initView() {
        //设置沉浸式状态栏
        setStatus();
        iv_search.setVisibility(View.VISIBLE);//右侧搜索图标
        //设置沉浸式状态栏背景
        if (bar_layout != null)
            bar_layout.setBackgroundResource(R.color.black_alpha_5);
        title_text.setText("装机必备");
    }

    @Override
    protected void initData() {
        mPresenter.getCategoryNecessaryData(this);
    }

    @Override
    protected CategoryNecessaryPresenterImpl initInjector() {
        build.inject(this);
        return mPresenter;
    }

    @Override
    public void getCategoryNecessaryDataSuccess(final CategoryNecessaryBean categoryNecessaryBean) {
        rv.setLayoutManager(new LinearLayoutManager(this));
        CategoryNecessaryAdapter adapter = new CategoryNecessaryAdapter(this);
        adapter.addDataAll(categoryNecessaryBean.getAppBeanList());
        CategoryNecessaryTopWrapper categoryNecessaryTopWrapper = new CategoryNecessaryTopWrapper(this, adapter, categoryNecessaryBean.getHead());
        rv.setAdapter(categoryNecessaryTopWrapper);

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
                AppBean appBean = categoryNecessaryBean.getAppBeanList().get(position);
                Intent intent = new Intent(CategoryNecessaryActivity.this, AppDetailActivity.class);
                intent.putExtra("packageName", appBean.getPackageName());
                startActivity(intent);

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
                return false;
            }
        });
    }

    @Override
    public void getCategoryNecessaryDataError(String msg) {
        showToast(msg);

    }

    class CategoryNecessaryAdapter extends CommonAdapter<AppBean> {
        private DownLoadController mController;
        public CategoryNecessaryAdapter(Context context) {
            super(context, R.layout.applistitem_recommend);
        }

        @Override
        protected void convert(ViewHolder holder, AppBean appBean, int position) {
            View view = holder.getConvertView();
            mController = new DownLoadController(mContext, view, appBean.getDownurl(), appBean);
            holder.setText(R.id.appTitle, appBean.getName());
            holder.setText(R.id.app_size, appBean.getSizeDesc());
            holder.setText(R.id.app_des, appBean.getMemo());
            holder.setImageUrl(R.id.appicon, appBean.getIcon());
        }
    }
}
