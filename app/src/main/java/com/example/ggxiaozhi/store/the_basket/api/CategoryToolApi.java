package com.example.ggxiaozhi.store.the_basket.api;


import com.example.ggxiaozhi.store.the_basket.bean.CategoryToolBean;
import com.example.ggxiaozhi.store.the_basket.utils.JsonParseUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhxu.library.api.BaseApi;
import com.zhxu.library.listener.HttpOnNextListener;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class CategoryToolApi extends BaseApi<CategoryToolBean> {
    public CategoryToolApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);

        setMothed("AppStore/categorydata/tool");

    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getCategoryToolData();
    }

    @Override
    public CategoryToolBean call(ResponseBody responseBody) {
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return JsonParseUtils.parseCategoryToolBean(string);
    }
}
