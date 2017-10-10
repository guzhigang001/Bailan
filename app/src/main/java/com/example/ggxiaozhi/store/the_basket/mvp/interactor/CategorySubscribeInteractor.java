package com.example.ggxiaozhi.store.the_basket.mvp.interactor;

import com.example.ggxiaozhi.store.the_basket.api.CategorySubscribeApi;
import com.example.ggxiaozhi.store.the_basket.api.IGetDataDelegate;
import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.bean.CategorySubscribeBean;
import com.example.ggxiaozhi.store.the_basket.utils.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.interactor
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/30
 * 功能   ：
 */

public class CategorySubscribeInteractor {

    private IGetDataDelegate<CategorySubscribeBean> mDelegate;

    @Inject
    public CategorySubscribeInteractor() {

    }

    public void loadCategorySubscribe(BaseActivity activity, IGetDataDelegate<CategorySubscribeBean> mDelegate) {
        this.mDelegate = mDelegate;
        HttpManager manager = HttpManager.getInstance();
        CategorySubscribeApi subscribeApi = new CategorySubscribeApi(listener, activity);
        manager.doHttpDeal(subscribeApi);
    }

    HttpOnNextListener<CategorySubscribeBean> listener = new HttpOnNextListener<CategorySubscribeBean>() {
        @Override
        public void onNext(CategorySubscribeBean bean) {
            mDelegate.getDataSuccess(bean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            CategorySubscribeBean bean = JsonParseUtils.parseCategorySubscribeBean(string);
            mDelegate.getDataSuccess(bean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };

}
