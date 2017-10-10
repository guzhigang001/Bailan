package com.example.ggxiaozhi.store.the_basket.mvp.interactor;

import com.example.ggxiaozhi.store.the_basket.api.CategoryApi;
import com.example.ggxiaozhi.store.the_basket.api.HttpGetService;
import com.example.ggxiaozhi.store.the_basket.api.IGetDataDelegate;
import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.bean.CategoryBean;
import com.example.ggxiaozhi.store.the_basket.utils.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.interactor
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/1
 * 时间   ： 19:36
 * 功能   ：真正为分类页提供请求的类
 */

public class CategoryInteractor {

    private IGetDataDelegate<CategoryBean> mDelegate;

    @Inject
    public CategoryInteractor() {

    }

    public void CategoryLoad(BaseActivity activity,IGetDataDelegate<CategoryBean> mDelegate) {
        this.mDelegate=mDelegate;
        CategoryApi api = new CategoryApi(listener, activity);
        HttpManager httpManager=HttpManager.getInstance();
        httpManager.doHttpDeal(api);
    }

    protected HttpOnNextListener listener = new HttpOnNextListener<CategoryBean>() {
        @Override
        public void onNext(CategoryBean categoryBean) {
            mDelegate.getDataSuccess(categoryBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            mDelegate.getDataSuccess(JsonParseUtils.parseCategoryBean(string));
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}