package com.example.ggxiaozhi.store.the_basket.mvp.interactor;

import com.example.ggxiaozhi.store.the_basket.api.AppRecommendApi;
import com.example.ggxiaozhi.store.the_basket.api.IGetDataDelegate;
import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.bean.AppRecommendBean;
import com.example.ggxiaozhi.store.the_basket.utils.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.interactor
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/29
 * 功能   ：
 */

public class AppRecommendInteractor {
    private IGetDataDelegate<AppRecommendBean> mDelegate;

    @Inject
    public AppRecommendInteractor() {

    }

    public void AppRecommendLoad(BaseActivity activity, String packageName, IGetDataDelegate<AppRecommendBean> mDelegate) {
        this.mDelegate = mDelegate;
        AppRecommendApi appRecommendApi = new AppRecommendApi(listener, activity, packageName);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(appRecommendApi);
    }

    HttpOnNextListener<AppRecommendBean> listener = new HttpOnNextListener<AppRecommendBean>() {
        @Override
        public void onNext(AppRecommendBean appRecommendBean) {
            mDelegate.getDataSuccess(appRecommendBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            AppRecommendBean bean = JsonParseUtils.parseAppRecommendBean(string);
            mDelegate.getDataSuccess(bean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
