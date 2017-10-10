package com.example.ggxiaozhi.store.the_basket.di.module;

import android.content.Context;

import com.example.ggxiaozhi.store.the_basket.base.StoreApplication;
import com.example.ggxiaozhi.store.the_basket.di.scope.ContextLife;
import com.example.ggxiaozhi.store.the_basket.di.scope.PerApp;

import dagger.Module;
import dagger.Provides;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.di.module
 * 作者名 ： 志先生_
 * 日期   ： 2017/8/26
 * 时间   ： 11:35
 * 功能   ：全局的Module
 */

@Module
public class AppModule {

    private StoreApplication mApplication;

    public AppModule(StoreApplication mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @PerApp
    @ContextLife("Application")
    public Context provideContext() {
        return mApplication.getApplicationContext();
    }
}
