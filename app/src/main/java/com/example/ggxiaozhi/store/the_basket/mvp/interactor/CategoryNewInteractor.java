package com.example.ggxiaozhi.store.the_basket.mvp.interactor;

import com.example.ggxiaozhi.store.the_basket.api.CategoryNewApi;
import com.example.ggxiaozhi.store.the_basket.api.IGetDataDelegate;
import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.bean.CategoryNewBean;
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

public class CategoryNewInteractor {

    private IGetDataDelegate<CategoryNewBean> mDelegate;

    @Inject
    public CategoryNewInteractor() {

    }

    public void loadCategoryNewData(BaseActivity activity, IGetDataDelegate<CategoryNewBean> mDelegate) {
        this.mDelegate = mDelegate;
        HttpManager manager = HttpManager.getInstance();
        CategoryNewApi api = new CategoryNewApi(listener, activity);
        manager.doHttpDeal(api);
    }

    private HttpOnNextListener<CategoryNewBean> listener = new HttpOnNextListener<CategoryNewBean>() {
        @Override
        public void onNext(CategoryNewBean categoryNewBean) {
            mDelegate.getDataSuccess(categoryNewBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            CategoryNewBean bean = JsonParseUtils.parseCategoryNewBean(string);
            mDelegate.getDataSuccess(bean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };

}
