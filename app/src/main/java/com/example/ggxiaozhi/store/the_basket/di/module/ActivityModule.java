package com.example.ggxiaozhi.store.the_basket.di.module;

import android.app.Activity;
import android.content.Context;

import com.example.ggxiaozhi.store.the_basket.di.scope.ContextLife;
import com.example.ggxiaozhi.store.the_basket.di.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.di.module
 * 作者名 ： 志先生_
 * 日期   ： 2017/8/26
 * 时间   ： 11:42
 * 功能   ：Activity的Module
 */

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity mActivity) {
        this.mActivity=mActivity;
    }

    @Provides
    @PerActivity
    public Activity provideActivity(){
        return mActivity;
    }


    @Provides
    @PerActivity
    @ContextLife("Activity")
    public Context provideContext(){

        return mActivity;
    }

}
