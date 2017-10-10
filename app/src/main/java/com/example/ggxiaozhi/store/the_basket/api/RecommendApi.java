package com.example.ggxiaozhi.store.the_basket.api;

import com.example.ggxiaozhi.store.the_basket.bean.RecommendBean;
import com.example.ggxiaozhi.store.the_basket.utils.JsonParseUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhxu.library.api.BaseApi;
import com.zhxu.library.listener.HttpOnNextListener;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.api
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/1
 * 时间   ： 15:26
 * 功能   ：推荐页面网络请求的初始化设置和返回数据的处理
 */

public class RecommendApi extends BaseApi<RecommendBean>{

    public RecommendApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
        setCache(true);//设置缓存
        setMothed("appstore/recommend");//存入到数据库，传入的值要与接口参数一致
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getRecommendData();
    }

    @Override
    public RecommendBean call(ResponseBody responseBody) {
        //转换规则。HttpManager中的Rxjava初始化中的Map操作符执行的是这个Call方法
        String result="";
        try {
            result= responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonParseUtils.parseRecommendBean(result);
    }
}
