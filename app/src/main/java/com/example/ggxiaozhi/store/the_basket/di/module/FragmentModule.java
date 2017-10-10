package com.example.ggxiaozhi.store.the_basket.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.example.ggxiaozhi.store.the_basket.di.scope.ContextLife;
import com.example.ggxiaozhi.store.the_basket.di.scope.PerActivity;
import com.example.ggxiaozhi.store.the_basket.di.scope.PerFragment;

import dagger.Module;
import dagger.Provides;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.di.module
 * 作者名 ： 志先生_
 * 日期   ： 2017/8/26
 * 时间   ： 11:42
 * 功能   ：Fragment的Module
 */
@Module
public class FragmentModule {

    private Fragment mFragment;

    public FragmentModule(Fragment mFragment) {
        this.mFragment=mFragment;
    }

    @Provides
    @PerFragment
    public Fragment provideFragment(){
        return mFragment;
    }

    @Provides
    @PerFragment
    public Activity proivdeFragmentActivity(){
        return mFragment.getActivity();
    }

    @Provides
    @PerFragment
    @ContextLife("Activity")
    public Context proivdeFragmentContext(){
        return mFragment.getContext();
    }
}
