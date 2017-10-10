package com.example.ggxiaozhi.store.the_basket.mvp.interactor;

import com.example.ggxiaozhi.store.the_basket.api.IGetDataDelegate;
import com.example.ggxiaozhi.store.the_basket.api.TopApi;
import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.bean.TopBean;
import com.example.ggxiaozhi.store.the_basket.utils.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.interactor
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/2
 * 时间   ： 12:26
 * 功能   ：真正为排行业页提供请求的类
 */

public class TopInteractor {

    private IGetDataDelegate<TopBean> mDelegate;

    @Inject
    public TopInteractor() {

    }

    public void loadTop(BaseActivity activity, IGetDataDelegate<TopBean> mDelegate) {
        this.mDelegate = mDelegate;
        TopApi api = new TopApi(listener, activity);
        HttpManager manager=HttpManager.getInstance();
        manager.doHttpDeal(api);
    }

    private HttpOnNextListener listener = new HttpOnNextListener<TopBean>() {

        @Override
        public void onNext(TopBean topBean) {
            mDelegate.getDataSuccess(topBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            mDelegate.getDataSuccess(JsonParseUtils.parseTopBean(string));
        }
    };
}