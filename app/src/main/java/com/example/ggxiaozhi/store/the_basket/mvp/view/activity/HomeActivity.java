package com.example.ggxiaozhi.store.the_basket.mvp.view.activity;

import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import com.example.ggxiaozhi.store.the_basket.R;
import com.example.ggxiaozhi.store.the_basket.adapter.FixPageAdapter;
import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.base.BaseFragment;
import com.example.ggxiaozhi.store.the_basket.factory.FragmentFactory;
import com.example.ggxiaozhi.store.the_basket.utils.DownLoadController;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.main_viewPage)
    ViewPager mViewPager;
    @BindView(R.id.status_bar)
    LinearLayout status_bar;

    private FixPageAdapter fixPagerAdapter;
    private String[] titles = {"推荐", "分类", "排行", "管理", "我的"};
    private List<Fragment> fragments;
    @Override

    protected void initLayout() {
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void initView() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            final int statusHeight = getStatusBarHeight();
            status_bar.post(new Runnable() {
                @Override
                public void run() {
                    android.widget.LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) status_bar.getLayoutParams();
                    params.height = statusHeight;
                    status_bar.setLayoutParams(params);
                }
            });
        }

        initViewPageFragment();
    }

    private void initViewPageFragment() {
        fixPagerAdapter = new FixPageAdapter(getSupportFragmentManager());

        fragments = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            fragments.add(FragmentFactory.createFragment(i));
        }
        fixPagerAdapter.setTitles(titles);
        fixPagerAdapter.setFragments(fragments);
        mViewPager.setAdapter(fixPagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);//TabLayout和ViewPage建立联系(setTitle也因此而有效)
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        //TabLayout.MODE_FIXED 禁止左右滚动，当标题多时压缩每个标题宽度
        //TabLayout.MODE_SCROLLABLE 左右滚动

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                BaseFragment fragment = FragmentFactory.createFragment(position);
                fragment.show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
