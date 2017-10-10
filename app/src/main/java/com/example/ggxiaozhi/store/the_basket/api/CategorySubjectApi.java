package com.example.ggxiaozhi.store.the_basket.api;

import com.example.ggxiaozhi.store.the_basket.utils.JsonParseUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhxu.library.api.BaseApi;
import com.zhxu.library.listener.HttpOnNextListener;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.api
 * 作者名 ： 志先生_
 * 日期   ： 2017/10/1
 * 功能   ：
 */

public class CategorySubjectApi extends BaseApi<List<String>> {

    public CategorySubjectApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
        setCache(true);
        setMothed("categorydata/subject");
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getCategorySubjectData();
    }

    @Override
    public List<String> call(ResponseBody responseBody) {
        String result = "";
        try {
            result = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonParseUtils.parseCategorySubject(result);
    }
}
