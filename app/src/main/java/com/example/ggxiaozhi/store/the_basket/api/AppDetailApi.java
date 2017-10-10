package com.example.ggxiaozhi.store.the_basket.api;

import com.example.ggxiaozhi.store.the_basket.bean.AppDetailBean;
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
 * 日期   ： 2017/9/25
 * 时间   ： 14:42
 * 功能   ：
 */

public class AppDetailApi extends BaseApi<AppDetailBean> {
    private String packageName;

    public AppDetailApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String packageName) {
        super(listener, rxAppCompatActivity);
        this.packageName = packageName;
//        setCache(true);
        setMothed("appStore/app/introduce?packageName=" + packageName);

    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        Observable<ResponseBody> appDetailData = httpGetService.getAppDetailData(packageName);
        return appDetailData;
    }

    @Override
    public AppDetailBean call(ResponseBody responseBody) {
        String result = "";
        try {
            result = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonParseUtils.parseAppDetailBean(result);
    }
}
