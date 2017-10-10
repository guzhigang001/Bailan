package com.example.ggxiaozhi.store.the_basket.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ggxiaozhi.store.the_basket.R;
import com.example.ggxiaozhi.store.the_basket.adapter.top.CategoryNewTopWrapper;
import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BaseMvpActivity;
import com.example.ggxiaozhi.store.the_basket.bean.AppBean;
import com.example.ggxiaozhi.store.the_basket.bean.CategoryNewBean;
import com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl.CategoryNewPresenterImpl;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity_view.CategoryNewActivityView;
import com.example.ggxiaozhi.store.the_basket.utils.DownLoadController;
import com.zhxu.recyclerview.adapter.CommonAdapter;
import com.zhxu.recyclerview.adapter.MultiItemTypeAdapter;
import com.zhxu.recyclerview.base.ViewHolder;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 首发
 */
public class CategoryNewActivity extends BaseMvpActivity<CategoryNewPresenterImpl> implements CategoryNewActivityView {


    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.iv_search)
    ImageView iv_search;
    @BindView(R.id.rv)
    RecyclerView rv;
    @Inject
    CategoryNewPresenterImpl mPresenter;
    private DownLoadController mController;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_category_new);
    }

    @Override
    protected void initView() {
        //设置沉浸式状态栏
        setStatus();
        iv_search.setVisibility(View.VISIBLE);//右侧搜索图标
        //设置沉浸式状态栏背景
        if (bar_layout != null)
            bar_layout.setBackgroundResource(R.color.black_alpha_5);
        title_text.setText("今日首发");
    }

    @Override
    protected void initData() {
        mPresenter.getCategoryNewData(this);
    }

    @Override
    protected CategoryNewPresenterImpl initInjector() {
        build.inject(this);
        return mPresenter;
    }

    @Override
    public void getCategoryNewDataSuccess(final CategoryNewBean categoryNewBean) {
        rv.setLayoutManager(new LinearLayoutManager(this));
        CategoryNewAdapter adapter = new CategoryNewAdapter(this);
        adapter.addDataAll(categoryNewBean.getAppBeanList());
        CategoryNewTopWrapper categoryNewTopWrapper = new CategoryNewTopWrapper(this, adapter, categoryNewBean.getHead());
        rv.setAdapter(categoryNewTopWrapper);
    }

    @Override
    public void getCategoryNewDataError(String msg) {
        showToast(msg);
    }

    class CategoryNewAdapter extends CommonAdapter<AppBean> {

        public CategoryNewAdapter(Context context) {
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
