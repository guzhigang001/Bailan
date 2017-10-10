package com.example.ggxiaozhi.store.the_basket.mvp.view.activity;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ggxiaozhi.store.the_basket.R;
import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BaseMvpActivity;
import com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl.CategoryNewPresenterImpl;
import com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl.CategorySubjectPresenterImpl;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity_view.CategorySubjectActivityView;
import com.zhxu.recyclerview.adapter.CommonAdapter;
import com.zhxu.recyclerview.base.ViewHolder;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class CategorySubjectActivity extends BaseMvpActivity<CategorySubjectPresenterImpl> implements CategorySubjectActivityView {
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.iv_search)
    ImageView iv_search;
    @BindView(R.id.rv)
    RecyclerView rv;
    @Inject
    CategorySubjectPresenterImpl mPresenter;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_category_subject);
    }

    @Override
    protected void initView() {
        //设置沉浸式状态栏
        setStatus();
        iv_search.setVisibility(View.VISIBLE);//右侧搜索图标
        //设置沉浸式状态栏背景
        if (bar_layout != null)
            bar_layout.setBackgroundResource(R.color.black_alpha_5);
        title_text.setText("专题列表");
    }

    @Override
    protected void initData() {
        mPresenter.getCategorySubjectData(this);
    }

    @Override
    protected CategorySubjectPresenterImpl initInjector() {
        build.inject(this);
        return mPresenter;
    }

    @Override
    public void CategorySubjectDataSuccess(List<String> mlist) {
        rv.setLayoutManager(new LinearLayoutManager(this));
        SubjectAdapter adapter = new SubjectAdapter(this);
        mlist.remove(0);
        adapter.addDataAll(mlist);
        rv.setAdapter(adapter);

    }

    @Override
    public void CategorySubjectDataError(String msg) {

    }

    class SubjectAdapter extends CommonAdapter<String> {
        public SubjectAdapter(Context context) {
            super(context, R.layout.subject_item);
        }

        @Override
        protected void convert(ViewHolder holder, String url, int position) {
            holder.setImageUrl(R.id.item_icon, url);
        }
    }
}
