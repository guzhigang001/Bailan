package com.example.ggxiaozhi.store.the_basket.base.mvpbase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.ggxiaozhi.store.the_basket.base.BaseFragment;
import com.example.ggxiaozhi.store.the_basket.base.StoreApplication;
import com.example.ggxiaozhi.store.the_basket.di.component.DaggerFragmentComponent;
import com.example.ggxiaozhi.store.the_basket.di.component.FragmentComponent;
import com.example.ggxiaozhi.store.the_basket.di.module.FragmentModule;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.base.mvpbase
 * 作者名 ： 志先生_
 * 日期   ： 2017/8/26
 * 时间   ： 14:16
 * 功能   ：将dagger2依赖注入通过抽象方法提取出来，由具体的Fragement去实现
 */

public  abstract class BaseMvpFragment<T extends BasePresenter> extends BaseFragment {

    protected FragmentComponent mFragmentComponent;
    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragmentComponent();

        mPresenter=initInjector();
        //绑定View
        mPresenter.attachView(this);
    }

    public void initFragmentComponent(){
        mFragmentComponent = DaggerFragmentComponent.builder().fragmentModule(new FragmentModule(this))
                .appComponent(((StoreApplication) getActivity().getApplication()).getAppComponent())
                .build();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this.getContext(),msg,Toast.LENGTH_SHORT).show();
    }

    /**
     * 完成注入并返回注入的Presenter对象
     * @return
     */
    protected abstract T initInjector();

    /**
     * 解绑
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.detachView();
        }
    }
}
