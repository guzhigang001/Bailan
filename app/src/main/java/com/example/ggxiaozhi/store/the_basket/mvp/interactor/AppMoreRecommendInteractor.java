package com.example.ggxiaozhi.store.the_basket.mvp.interactor;

import com.example.ggxiaozhi.store.the_basket.api.AppMoreRecommendApi;
import com.example.ggxiaozhi.store.the_basket.api.IGetDataDelegate;
import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.bean.AppMoreRecommendBean;
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

public class AppMoreRecommendInteractor {

    private IGetDataDelegate<AppMoreRecommendBean> mDelegate;

    @Inject
    public AppMoreRecommendInteractor() {

    }

    public void loadAppMoreRecommendData(BaseActivity activity, String type, String packageName, IGetDataDelegate<AppMoreRecommendBean> mDelegate) {
        this.mDelegate = mDelegate;
        HttpManager manager = HttpManager.getInstance();
        AppMoreRecommendApi recommendApi = new AppMoreRecommendApi(listener, activity, type, packageName);
        manager.doHttpDeal(recommendApi);

    }

    private HttpOnNextListener<AppMoreRecommendBean> listener = new HttpOnNextListener<AppMoreRecommendBean>() {
        @Override
        public void onNext(AppMoreRecommendBean appMoreRecommendBean) {
            mDelegate.getDataSuccess(appMoreRecommendBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            AppMoreRecommendBean appMoreRecommendBean = JsonParseUtils.parseAppMoreRecommendBean(string);
            mDelegate.getDataSuccess(appMoreRecommendBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
