package com.example.ggxiaozhi.store.the_basket.api;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.api
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/1
 * 时间   ： 16:31
 * 功能   ：真正网络请求结果的回调()
 */

public interface IGetDataDelegate<T> {

    void getDataSuccess(T t);//网络请求成功的回调
    void getDataError(String msg);//网络请求失败的回调
}
