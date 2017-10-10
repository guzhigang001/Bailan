package com.example.ggxiaozhi.store.the_basket.di.component;

import android.content.Context;

import com.example.ggxiaozhi.store.the_basket.di.module.AppModule;
import com.example.ggxiaozhi.store.the_basket.di.scope.ContextLife;
import com.example.ggxiaozhi.store.the_basket.di.scope.PerApp;

import dagger.Component;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.di
 * 作者名 ： 志先生_
 * 日期   ： 2017/8/23
 * 时间   ： 13:35
 * 功能   ：
 */

@PerApp
@Component(modules = {AppModule.class})
public interface AppComponent {

    @ContextLife("Application")
    Context getApplicationContext();
}
