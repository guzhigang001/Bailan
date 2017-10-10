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
import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BaseMvpActivity;
import com.example.ggxiaozhi.store.the_basket.bean.AppBean;
import com.example.ggxiaozhi.store.the_basket.bean.AppMoreRecommendBean;
import com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl.AppMoreRecommendPresentImpl;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity_view.AppMoreRecommendActivityView;
import com.example.ggxiaozhi.store.the_basket.utils.DownLoadController;
import com.zhxu.recyclerview.adapter.CommonAdapter;
import com.zhxu.recyclerview.adapter.MultiItemTypeAdapter;
import com.zhxu.recyclerview.base.ViewHolder;

import javax.inject.Inject;

import butterknife.BindView;

public class AppMoreRecommendActivity extends BaseMvpActivity<AppMoreRecommendPresentImpl> implements AppMoreRecommendActivityView {


    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.iv_search)
    ImageView iv_search;
    @BindView(R.id.rv)
    RecyclerView rv;

    @Inject
    AppMoreRecommendPresentImpl mPresent;

    private AppMoreRecommendBean mRecommendBean;
    private String type;
    private String packageName;

    private DownLoadController mController;

    @Override
    protected void initData() {
        mPresent.getAppMoreRecommendDate(this, type, packageName);

    }

    @Override
    protected AppMoreRecommendPresentImpl initInjector() {
        build.inject(this);
        return mPresent;
    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_app_more_recommend);
    }

    @Override
    protected void initView() {
        //设置沉浸式状态栏
        setStatus();
        iv_search.setVisibility(View.VISIBLE);//右侧搜索图标
        //设置沉浸式状态栏背景
        if (bar_layout != null)
            bar_layout.setBackgroundResource(R.color.black_alpha_5);

        type = getIntent().getStringExtra("type");
        packageName = getIntent().getStringExtra("packageName");

        if (type.equals("popular"))
            title_text.setText("流行应用");
        if (type.equals("taste"))
            title_text.setText("兴趣相近的用户也安装了");
        if (type.equals("hot"))
            title_text.setText("本周热议的应用");

    }

    @Override
    public void getAppMoreRecommendDataSuccess(AppMoreRecommendBean bean) {
        this.mRecommendBean = bean;
        rv.setLayoutManager(new LinearLayoutManager(this));
        AppMoreRecommendAdapter adapter = new AppMoreRecommendAdapter(this);
        adapter.addDataAll(bean.getMoreAppBean());
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener<AppBean>() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, AppBean o, int position) {
                Intent intent = new Intent(AppMoreRecommendActivity.this, AppDetailActivity.class);
                intent.putExtra("packageName", o.getPackageName());
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, AppBean o, int position) {
                return false;
            }
        });
        rv.setAdapter(adapter);

    }

    @Override
    public void getAppMoreRecommendDataError(String msg) {
        showToast(msg);
    }

    private class AppMoreRecommendAdapter extends CommonAdapter<AppBean> {

        public AppMoreRecommendAdapter(Context context) {
            super(context, R.layout.applistitem_recommend);
        }

        @Override
        protected void convert(ViewHolder holder, AppBean moreRecommendBean, int position) {
            View view = holder.getConvertView();
            holder.setText(R.id.appTitle, moreRecommendBean.getName());
            holder.setText(R.id.app_size, moreRecommendBean.getSizeDesc());
            holder.setText(R.id.app_des, moreRecommendBean.getMemo());
            holder.setImageUrl(R.id.appicon, moreRecommendBean.getIcon());
        }
    }
}
