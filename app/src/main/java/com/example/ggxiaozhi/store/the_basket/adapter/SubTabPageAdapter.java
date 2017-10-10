package com.example.ggxiaozhi.store.the_basket.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/26
 * 时间   ： 12:33
 * 功能   ：
 */

public class SubTabPageAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;

    public SubTabPageAdapter(FragmentManager fm) {
        super(fm);
        this.mFragments = mFragments;
    }
    public void setFragments(List<Fragment> fragments){
        this.mFragments = fragments ;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) super.instantiateItem(container, position);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fragment;
    }
}
