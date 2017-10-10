package com.example.ggxiaozhi.store.the_basket.base;


import android.os.Handler;

import com.blankj.utilcode.utils.AppUtils;
import com.blankj.utilcode.utils.LogUtils;
import com.blankj.utilcode.utils.Utils;
import com.example.ggxiaozhi.store.the_basket.BuildConfig;
import com.example.ggxiaozhi.store.the_basket.di.component.AppComponent;
import com.example.ggxiaozhi.store.the_basket.di.component.DaggerAppComponent;
import com.example.ggxiaozhi.store.the_basket.di.module.AppModule;
import com.zhxu.library.RxRetrofitApp;
import com.zhxu.recyclerview.App;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.base
 * 作者名 ： 志先生_
 * 日期   ： 2017/8/22
 * 时间   ： 20:40
 * 功能   ： 全局Appcation 进行全局统一配置
 */

public class StoreApplication extends App {

    public static int mMainThreadId;
    public static Handler mHandler;
    public AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(getApplicationContext());
        LogUtils.init(true,false,'v',"TAG");

        mHandler=new Handler();
        initApplicationComponent();

        RxRetrofitApp.init(this, BuildConfig.DEBUG);

    }

    /**
     * 初始化AppComponent
     */
    public void initApplicationComponent(){
        mAppComponent= DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    /**
     * 对外提供AppComponent
     * @return
     */
    public AppComponent getAppComponent(){
        return mAppComponent;
    }
    /**
     * 返回主线程的pid
     * @return
     */
    public static int getMainThreadId() {
        return mMainThreadId;
    }
    /**
     * 返回Handler
     * @return
     */
    public static Handler getHandler() {
        return mHandler;
    }
}
