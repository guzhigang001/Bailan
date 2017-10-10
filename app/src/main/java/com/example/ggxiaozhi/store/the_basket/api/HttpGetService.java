package com.example.ggxiaozhi.store.the_basket.api;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.api
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/1
 * 时间   ： 15:19
 * 功能   ：所求请求接口的服务
 */

public interface HttpGetService {

    @GET("recommend")/*推荐数据*/
    Observable<ResponseBody> getRecommendData();

    @GET("category")/*分类数据*/
    Observable<ResponseBody> getCategoryData();

    @GET("top")
    Observable<ResponseBody> getTopData();

    @GET("app/introduce")/*推荐页跳转的详情页数据*/
    Observable<ResponseBody> getAppDetailData(@Query("packageName") String packageName);

    @GET("app/comment")/*评论页面数据*/
    Observable<ResponseBody> getAppCommentData(@Query("packageName") String packageName);

    @GET("app/recommend")/*详情页中推荐页数据*/
    Observable<ResponseBody> getAppRecommendData(@Query("packageName") String packageName);

    @GET("categorydata/subscribe")/*分类Fragment中的预约页面数据*/
    Observable<ResponseBody> getCategorySubscribeData();

    @GET("categorydata/necessary")/*分类Fragment中的必备页面数据*/
    Observable<ResponseBody> getCategoryNecessaryData();

    @GET("categorydata/new")/*分类Fragment中的首发页面数据*/
    Observable<ResponseBody> getCategoryNewData();

    @GET("categorydata/subject")/*分类Fragment中的专题页面数据*/
    Observable<ResponseBody> getCategorySubjectData();

    @GET("categorydata/tool")/*分类Fragment中的各类页面数据*/
    Observable<ResponseBody> getCategoryToolData();

    @GET("app/{type}")/*详情页 推荐SubTab 更多数据*/
    Observable<ResponseBody> getAppMoreRecommendData(@Path("type") String type, @Query("packageName") String packageName);
}
