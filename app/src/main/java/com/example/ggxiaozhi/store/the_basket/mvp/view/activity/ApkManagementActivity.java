package com.example.ggxiaozhi.store.the_basket.mvp.view.activity;


import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ggxiaozhi.store.the_basket.R;
import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ApkManagementActivity extends BaseActivity {
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.iv_search)
    ImageView iv_search;
    @BindView(R.id.progressImg)
    ImageView progressImg;
    @BindView(R.id.nodata_localpkg_refresh)
    TextView nodata_localpkg_refresh;
    @BindView(R.id.rv)
    RecyclerView rv;
    List<String> strs = new ArrayList<>();

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {

        //设置沉浸式状态栏
        setStatus();

        iv_search.setVisibility(View.GONE);
        //设置沉浸式状态栏背景
        bar_layout.setBackgroundResource(R.color.black_alpha_5);
        title_text.setText("安装包管理");

        nodata_localpkg_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressImg.setVisibility(View.VISIBLE);
                AnimationDrawable drawable = (AnimationDrawable) progressImg.getDrawable();
                drawable.start();
            }
        });
    }

}