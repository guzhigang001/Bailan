package com.example.ggxiaozhi.store.the_basket.mvp.interactor;

import com.example.ggxiaozhi.store.the_basket.api.AppDetailApi;
import com.example.ggxiaozhi.store.the_basket.api.IGetDataDelegate;
import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.bean.AppDetailBean;
import com.example.ggxiaozhi.store.the_basket.utils.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.interactor
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/25
 * 时间   ： 14:35
 * 功能   ：
 */

public class AppDateilActivityInteractor {

    private IGetDataDelegate<AppDetailBean> mDelegate;

    @Inject
    public AppDateilActivityInteractor() {

    }

    public void AppDetailLoad(BaseActivity activity, IGetDataDelegate<AppDetailBean> iGetDataDelegate, String packageName) {
        this.mDelegate = iGetDataDelegate;
        AppDetailApi appDetailApi = new AppDetailApi(mNextListener, activity, packageName);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(appDetailApi);
    }

    HttpOnNextListener<AppDetailBean> mNextListener = new HttpOnNextListener<AppDetailBean>() {
        @Override
        public void onNext(AppDetailBean appDetailBean) {
            mDelegate.getDataSuccess(appDetailBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            AppDetailBean appDetailBean = JsonParseUtils.parseAppDetailBean(string);
            mDelegate.getDataSuccess(appDetailBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
