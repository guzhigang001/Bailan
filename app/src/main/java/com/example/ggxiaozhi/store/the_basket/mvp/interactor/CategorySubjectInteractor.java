package com.example.ggxiaozhi.store.the_basket.mvp.interactor;

import com.example.ggxiaozhi.store.the_basket.api.CategorySubjectApi;
import com.example.ggxiaozhi.store.the_basket.api.IGetDataDelegate;
import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.bean.CategorySubscribeBean;
import com.example.ggxiaozhi.store.the_basket.utils.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import java.util.List;

import javax.inject.Inject;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.interactor
 * 作者名 ： 志先生_
 * 日期   ： 2017/10/1
 * 功能   ：
 */

public class CategorySubjectInteractor {

    private IGetDataDelegate<List<String>> mDelegate;


    @Inject
    public CategorySubjectInteractor() {

    }

    public void loadCategorySubjectData(BaseActivity activity, IGetDataDelegate<List<String>> mDelegate) {
        this.mDelegate = mDelegate;
        HttpManager manager = HttpManager.getInstance();
        CategorySubjectApi api = new CategorySubjectApi(listener, activity);
        manager.doHttpDeal(api);

    }

    private HttpOnNextListener<List<String>> listener = new HttpOnNextListener<List<String>>() {
        @Override
        public void onNext(List<String> strings) {
            mDelegate.getDataSuccess(strings);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            List<String> strs = JsonParseUtils.parseCategorySubject(string);
            mDelegate.getDataSuccess(strs);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };

}
