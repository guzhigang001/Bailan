package com.example.ggxiaozhi.store.the_basket.di.component;

import android.app.Activity;
import android.content.Context;

import com.example.ggxiaozhi.store.the_basket.mvp.view.activity.AppDetailActivity;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity.AppMoreRecommendActivity;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity.CategoryNecessaryActivity;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity.CategoryNewActivity;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity.CategorySubjectActivity;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity.CategorySubscribeActivity;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity.CategoryToolActivity;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity.HomeActivity;
import com.example.ggxiaozhi.store.the_basket.di.module.ActivityModule;
import com.example.ggxiaozhi.store.the_basket.di.scope.ContextLife;
import com.example.ggxiaozhi.store.the_basket.di.scope.PerActivity;

import dagger.Component;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.di.component
 * 作者名 ： 志先生_
 * 日期   ： 2017/8/26
 * 时间   ： 12:09
 * 功能   ：
 */

@PerActivity
@Component(modules = ActivityModule.class , dependencies = AppComponent.class)
public interface ActivityComponent {

    @ContextLife("Application")
    Context getApplicationContext();

    @ContextLife("Activity")
    Context getActivityContext();

    Activity getActivity();

    void inject(CategorySubscribeActivity activity);
    void inject(CategoryNecessaryActivity activity);
    void inject(CategoryNewActivity activity);
    void inject(CategorySubjectActivity activity);
    void inject(AppMoreRecommendActivity activity);
    void inject(AppDetailActivity activity);
    void inject(CategoryToolActivity activity);

}
