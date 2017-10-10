package com.example.ggxiaozhi.store.the_basket.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.adapter
 * 作者名 ： 志先生_
 * 日期   ： 2017/8/22
 * 时间   ： 16:28
 * 功能   ：主页面Viewpage适配器
 */
//FragmentStatePagerAdapter销毁不可见Fragemnt(适合多Fragment)和FragmentPagerAdapter保留Fragment实例
public class FixPageAdapter extends FragmentStatePagerAdapter{

    private String[] titles;

    List<Fragment> mFragments=null;



    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    public void setFragments(List<Fragment> fragments) {
        mFragments = fragments;
    }

    public FixPageAdapter(FragmentManager fm) {
        super(fm);
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
        Fragment fragment=null;
        try {
            fragment= (Fragment) super.instantiateItem(container,position);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
