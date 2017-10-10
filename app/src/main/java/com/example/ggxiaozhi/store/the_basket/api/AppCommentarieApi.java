package com.example.ggxiaozhi.store.the_basket.api;

import com.example.ggxiaozhi.store.the_basket.bean.AppCommentBean;
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
 * 日期   ： 2017/9/28
 * 功能   ：
 */

public class AppCommentarieApi extends BaseApi<AppCommentBean> {
    private String packageName;


    public AppCommentarieApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String packageName) {
        super(listener, rxAppCompatActivity);
        this.packageName = packageName;
        setCache(true);
        setMothed("app/comment??packageName=" + packageName);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getAppCommentData(packageName);
    }

    @Override
    public AppCommentBean call(ResponseBody responseBody) {
        String result = "";
        try {
            result = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonParseUtils.parseAppCommentBean(result);
    }
}
