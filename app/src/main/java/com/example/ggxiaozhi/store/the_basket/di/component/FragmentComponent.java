package com.example.ggxiaozhi.store.the_basket.di.component;

import android.app.Activity;
import android.content.Context;

import com.example.ggxiaozhi.store.the_basket.di.module.FragmentModule;
import com.example.ggxiaozhi.store.the_basket.di.scope.ContextLife;
import com.example.ggxiaozhi.store.the_basket.di.scope.PerFragment;
import com.example.ggxiaozhi.store.the_basket.mvp.view.fragment.CategoryFragment;
import com.example.ggxiaozhi.store.the_basket.mvp.view.fragment.RecommendFragment;
import com.example.ggxiaozhi.store.the_basket.mvp.view.fragment.TopFragment;
import com.example.ggxiaozhi.store.the_basket.mvp.view.fragment.appdetails.AppCommentarieFragment;
import com.example.ggxiaozhi.store.the_basket.mvp.view.fragment.appdetails.AppIntroduceFragment;
import com.example.ggxiaozhi.store.the_basket.mvp.view.fragment.appdetails.AppRecommendFragment;

import dagger.Component;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.di.component
 * 作者名 ： 志先生_
 * 日期   ： 2017/8/26
 * 时间   ： 12:30
 * 功能   ：
 */

@PerFragment
@Component(modules = {FragmentModule.class}, dependencies = AppComponent.class)
public interface FragmentComponent {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(RecommendFragment fragment);
    void inject(CategoryFragment fragment);
    void inject(TopFragment fragment);
    void inject(AppIntroduceFragment fragment);
    void inject(AppCommentarieFragment fragment);
    void inject(AppRecommendFragment fragment);


}
