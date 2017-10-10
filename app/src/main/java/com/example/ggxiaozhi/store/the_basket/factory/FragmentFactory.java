package com.example.ggxiaozhi.store.the_basket.factory;

import com.example.ggxiaozhi.store.the_basket.base.BaseFragment;
import com.example.ggxiaozhi.store.the_basket.mvp.view.fragment.AppManagerFragment;
import com.example.ggxiaozhi.store.the_basket.mvp.view.fragment.CategoryFragment;
import com.example.ggxiaozhi.store.the_basket.mvp.view.fragment.MyFragment;
import com.example.ggxiaozhi.store.the_basket.mvp.view.fragment.RecommendFragment;
import com.example.ggxiaozhi.store.the_basket.mvp.view.fragment.TopFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.factory
 * 作者名 ： 志先生_
 * 日期   ： 2017/8/22
 * 时间   ： 17:44
 * 功能   ： 对首页Fragment的管理
 */

public class FragmentFactory {
    /**
     * 推荐
     */
    public static final int TAB_RECOMMEND = 0;
    /**
     * 分类
     */
    public static final int TAB_CATEGORY = 1;
    /**
     * 排行
     */
    public static final int TAB_TOP = 2;
    /**
     * 管理
     */
    public static final int TAB_APPMANAGER = 3;
    /**
     * 我的
     */
    public static final int TAB_MY = 4;

    private static Map<Integer,BaseFragment> mFragmentMap=new HashMap<>();

    /**
     * 统一通过一个方法创建Fragment，避免重复创建
     * @param index
     * @return
     */
    public static BaseFragment createFragment(int index){
        BaseFragment fragment=mFragmentMap.get(index);
        if (fragment==null){
            //如果Fragment不存在就创建相应的Fragment
            switch (index){
                case TAB_RECOMMEND:
                    fragment=new RecommendFragment();
                    break;
                case TAB_CATEGORY:
                    fragment=new CategoryFragment();
                    break;
                case TAB_TOP:
                    fragment=new TopFragment();
                    break;
                case TAB_APPMANAGER:
                    fragment=new AppManagerFragment();
                    break;
                case TAB_MY:
                    fragment=new MyFragment();
                    break;
            }
            // 把创建的Fragment 存起来
            mFragmentMap.put(index,fragment);
        }
        return fragment;
    }
}
