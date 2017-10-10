package com.example.ggxiaozhi.store.the_basket.mvp.interactor;

import com.example.ggxiaozhi.store.the_basket.api.CategoryNecessaryApi;
import com.example.ggxiaozhi.store.the_basket.api.IGetDataDelegate;
import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.bean.CategoryNecessaryBean;
import com.example.ggxiaozhi.store.the_basket.utils.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.interactor
 * 作者名 ： 志先生_
 * 日期   ： 2017/10/1
 * 功能   ：
 */

public class CategoryNecessaryInteractor {

    private IGetDataDelegate<CategoryNecessaryBean> mDelegate;

    @Inject
    public CategoryNecessaryInteractor() {

    }

    public void CategoryNecessaryInteractor(BaseActivity activity, IGetDataDelegate<CategoryNecessaryBean> mDelegate) {
        this.mDelegate = mDelegate;
        CategoryNecessaryApi api = new CategoryNecessaryApi(listener, activity);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(api);

    }

    private HttpOnNextListener<CategoryNecessaryBean> listener = new HttpOnNextListener<CategoryNecessaryBean>() {
        @Override
        public void onNext(CategoryNecessaryBean bean) {
            mDelegate.getDataSuccess(bean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            CategoryNecessaryBean bean = JsonParseUtils.parseCategoryNecessaryBean(string);
            mDelegate.getDataSuccess(bean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };

}
