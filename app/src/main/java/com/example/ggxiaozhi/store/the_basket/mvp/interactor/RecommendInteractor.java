package com.example.ggxiaozhi.store.the_basket.mvp.interactor;

import com.example.ggxiaozhi.store.the_basket.api.IGetDataDelegate;
import com.example.ggxiaozhi.store.the_basket.api.RecommendApi;
import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.bean.RecommendBean;
import com.example.ggxiaozhi.store.the_basket.utils.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.interactor
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/1
 * 时间   ： 16:35
 * 功能   ：真正为推荐页提供请求的类
 */

public class RecommendInteractor {
    private IGetDataDelegate<RecommendBean> mDelegate;

    @Inject
    public RecommendInteractor() {
    }

    /**
     * 为推荐页请求网络
     * @param activity Rxjava监听Activity的生命周期
     */
    public void LoadRecommend(BaseActivity activity,IGetDataDelegate mDelegate){
        this.mDelegate=mDelegate;
        RecommendApi api=new RecommendApi(listener,activity);
        HttpManager instance = HttpManager.getInstance();
        instance.doHttpDeal(api);
    }

    private HttpOnNextListener<RecommendBean> listener=new HttpOnNextListener<RecommendBean>() {
        @Override
        public void onNext(RecommendBean recommendBean) {
            mDelegate.getDataSuccess(recommendBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            mDelegate.getDataSuccess( JsonParseUtils.parseRecommendBean(string));
        }
    };
}
