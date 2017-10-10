package com.example.ggxiaozhi.store.the_basket.api;

import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.bean.AppMoreRecommendBean;
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
 * 日期   ： 2017/9/30
 * 功能   ：
 */

public class AppMoreRecommendApi extends BaseApi<AppMoreRecommendBean> {

    private String type;
    private String packageName;

    public AppMoreRecommendApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String type, String packageName) {
        super(listener, rxAppCompatActivity);
        this.type = type;
        this.packageName = packageName;
        setCache(true);
        setMothed("appStore/app/" + type + "?packageName=" + packageName);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getAppMoreRecommendData(type, packageName);
    }

    @Override
    public AppMoreRecommendBean call(ResponseBody responseBody) {
        String result = "";
        try {
            result = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonParseUtils.parseAppMoreRecommendBean(result);
    }
}
