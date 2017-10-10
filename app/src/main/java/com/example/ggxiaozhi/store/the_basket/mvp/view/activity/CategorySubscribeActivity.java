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
import android.widget.Toast;

import com.example.ggxiaozhi.store.the_basket.R;
import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BaseMvpActivity;
import com.example.ggxiaozhi.store.the_basket.bean.AppBean;
import com.example.ggxiaozhi.store.the_basket.bean.CategorySubscribeBean;
import com.example.ggxiaozhi.store.the_basket.di.component.ActivityComponent;
import com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl.CategorySubscribeActivityPresentImpl;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity_view.CategorySubscribeActivityView;
import com.example.ggxiaozhi.store.the_basket.utils.DownLoadController;
import com.zhxu.recyclerview.adapter.CommonAdapter;
import com.zhxu.recyclerview.adapter.MultiItemTypeAdapter;
import com.zhxu.recyclerview.base.ViewHolder;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 预约Activity
 */
public class CategorySubscribeActivity extends BaseMvpActivity<CategorySubscribeActivityPresentImpl> implements CategorySubscribeActivityView {

    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.iv_search)
    ImageView iv_search;
    @BindView(R.id.rv)
    RecyclerView rv;

    @Inject
    CategorySubscribeActivityPresentImpl mPresent;
    private CategorySubscribeBean bean;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_category_subscribe);
    }

    @Override
    protected void initView() {
        //设置沉浸式状态栏
        setStatus();
        iv_search.setVisibility(View.VISIBLE);//右侧搜索图标
        //设置沉浸式状态栏背景
        if (bar_layout != null)
            bar_layout.setBackgroundResource(R.color.black_alpha_5);
        title_text.setText(getResources().getString(R.string.card_reserve_btn));
    }

    @Override
    protected void initData() {
        mPresent.getCategorySubscribeData(this);
    }

    @Override
    protected CategorySubscribeActivityPresentImpl initInjector() {
        build.inject(this);
        return mPresent;
    }

    @Override
    public void getCategorySubscribeDataSuccess(final CategorySubscribeBean bean) {
        this.bean = bean;
        rv.setLayoutManager(new LinearLayoutManager(this));
        CategorySubscribeAdapter adapter = new CategorySubscribeAdapter(this);
        adapter.addDataAll(bean.getAppBeanList());
        rv.setAdapter(adapter);

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
                Intent intent = new Intent(CategorySubscribeActivity.this, WebViewActivity.class);
                intent.putExtra("name", bean.getAppBeanList().get(position).getName());
                intent.putExtra("url", bean.getAppBeanList().get(position).getDetailId());
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
                return false;
            }
        });
    }

    @Override
    public void getCategorySubscribeDataError(String msg) {
        showToast(msg);
    }

    public class CategorySubscribeAdapter extends CommonAdapter<AppBean> {

        public CategorySubscribeAdapter(Context context) {
            super(context, R.layout.applistitem_recommend);
        }

        @Override
        protected void convert(ViewHolder holder, AppBean bean, int position) {
            holder.setText(R.id.appTitle, bean.getName());
            holder.setText(R.id.app_size, bean.getSizeDesc());
            holder.setText(R.id.app_des, bean.getMemo());
            holder.setImageUrl(R.id.appicon, bean.getIcon());
            holder.setOnClickListener(R.id.downbtn, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "预约游戏暂不支持下载", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
