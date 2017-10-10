package com.example.ggxiaozhi.store.the_basket.mvp.interactor;

import com.example.ggxiaozhi.store.the_basket.api.AppIntroduceApi;
import com.example.ggxiaozhi.store.the_basket.api.IGetDataDelegate;
import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.bean.AppIntroductionBean;
import com.example.ggxiaozhi.store.the_basket.utils.JsonParseUtils;
import com.zhxu.library.download.HttpDownManager;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.interactor
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/26
 * 功能   ：
 */

public class AppIntroduceInteractor {

    private IGetDataDelegate<AppIntroductionBean> mDelegate;

    @Inject
    public AppIntroduceInteractor() {

    }

    public void AppIntroduceLoad(BaseActivity activity, String packageName, IGetDataDelegate<AppIntroductionBean> mDelegate) {
        this.mDelegate = mDelegate;
        AppIntroduceApi introduceApi = new AppIntroduceApi(listener, activity, packageName);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(introduceApi);
    }

    private HttpOnNextListener<AppIntroductionBean> listener = new HttpOnNextListener<AppIntroductionBean>() {
        @Override
        public void onNext(AppIntroductionBean appIntroductionBean) {
            mDelegate.getDataSuccess(appIntroductionBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            AppIntroductionBean bean = JsonParseUtils.parseAppIntroductionBean(string);
            mDelegate.getDataSuccess(bean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };

}
