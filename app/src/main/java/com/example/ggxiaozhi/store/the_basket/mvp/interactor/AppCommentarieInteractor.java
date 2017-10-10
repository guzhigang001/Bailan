package com.example.ggxiaozhi.store.the_basket.mvp.interactor;

import com.example.ggxiaozhi.store.the_basket.api.AppCommentarieApi;
import com.example.ggxiaozhi.store.the_basket.api.IGetDataDelegate;
import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.bean.AppCommentBean;
import com.example.ggxiaozhi.store.the_basket.utils.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.interactor
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/28
 * 功能   ：
 */

public class AppCommentarieInteractor {
    private IGetDataDelegate<AppCommentBean> mDelegate;

    @Inject
    public AppCommentarieInteractor() {

    }

    public void AppCommentarieLoad(BaseActivity activity, IGetDataDelegate<AppCommentBean> mDelegate, String packageName) {
        this.mDelegate = mDelegate;
        HttpManager manager = HttpManager.getInstance();
        AppCommentarieApi commentarieApi = new AppCommentarieApi(listener, activity, packageName);
        manager.doHttpDeal(commentarieApi);


    }

    HttpOnNextListener<AppCommentBean> listener = new HttpOnNextListener<AppCommentBean>() {
        @Override
        public void onNext(AppCommentBean appCommentBean) {
            mDelegate.getDataSuccess(appCommentBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            AppCommentBean bean = JsonParseUtils.parseAppCommentBean(string);
            mDelegate.getDataSuccess(bean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
