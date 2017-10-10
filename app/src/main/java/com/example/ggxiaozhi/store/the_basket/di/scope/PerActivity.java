package com.example.ggxiaozhi.store.the_basket.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;
import javax.inject.Singleton;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.di.scope
 * 作者名 ： 志先生_
 * 日期   ： 2017/8/26
 * 时间   ： 11:36
 * 功能   ：Activity的Scope
 */

@Scope
@Documented
@Retention(RUNTIME)
public @interface PerActivity {
}
