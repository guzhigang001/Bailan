package com.example.ggxiaozhi.store.the_basket.mvp.view.fragment;


import android.content.Intent;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import com.example.ggxiaozhi.store.the_basket.R;
import com.example.ggxiaozhi.store.the_basket.base.BaseFragment;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity.ApkManagementActivity;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity.CleanCacheActivity;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity.InstallAppInfoActivity;
import com.example.ggxiaozhi.store.the_basket.utils.UIUtils;
import com.example.ggxiaozhi.store.the_basket.view.LoadingPager;
import com.example.ggxiaozhi.store.the_basket.view.widget.EnterLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppManagerFragment extends BaseFragment {

    @BindView(R.id.update_label_textview)
    TextView tvUpdateLabel;
    @BindView(R.id.update_label_subtitle)
    TextView tvUpdateLabelSubtitle;
    @BindView(R.id.install_manager_layout)
    EnterLayout installManager;
    @BindView(R.id.apk_manager_layout)
    EnterLayout apkManager;
    @BindView(R.id.system_manager_layout)
    EnterLayout systemManager;
    @BindView(R.id.connect_computer)
    EnterLayout connect;

    @Override
    protected void load() {
        setState(LoadingPager.LoadResult.success);
    }

    @Override
    protected View cretaeSuccessView() {
        View view = UIUtils.inflate(R.layout.fragment_manager);
        ButterKnife.bind(this, view);

        tvUpdateLabel.setText(UIUtils.getString(R.string.update_manager_title));
        tvUpdateLabelSubtitle.setText(UIUtils.getString(R.string.update_manager_subtitle_version_new));
        installManager.setTitle(UIUtils.getString(R.string.install_manager_title_ex));
        installManager.setMemo(UIUtils.getString(R.string.install_manager_subtitle));
        apkManager.setTitle(UIUtils.getString(R.string.apk_management));
        apkManager.setMemo(UIUtils.getString(R.string.apkmanage_tips_modify));
        systemManager.setTitle(UIUtils.getString(R.string.system_manager_title));
        systemManager.setMemo(UIUtils.getString(R.string.system_manager_memo));
        connect.setTitle(UIUtils.getString(R.string.connect_pc));
        connect.setMemo(UIUtils.getString(R.string.manager_phone_by_pc));

        installManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBaseActivity.startActivity(new Intent(getContext(),InstallAppInfoActivity.class));
            }
        });

        apkManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBaseActivity.startActivity(new Intent(getContext(), ApkManagementActivity.class));
            }
        });

        systemManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBaseActivity.startActivity(new Intent(getContext(), CleanCacheActivity.class));
            }
        });
        return view;
    }
}
