package com.zhxu.library.subscribers;


import com.zhxu.library.download.DownInfo;
import com.zhxu.library.download.DownLoadListener.DownloadProgressListener;
import com.zhxu.library.download.DownState;
import com.zhxu.library.download.HttpDownManager;
import com.zhxu.library.listener.HttpDownOnNextListener;
import com.zhxu.library.utils.DbDownUtil;

import java.lang.ref.SoftReference;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * 断点下载处理类Subscriber
 * 用于在Http请求开始时，自动显示一个ProgressDialog
 * 在Http请求结束是，关闭ProgressDialog
 * 调用者自己对请求数据进行处理
 */
public class ProgressDownSubscriber<T> extends Subscriber<T> implements DownloadProgressListener {
    //弱引用结果回调
    private SoftReference<HttpDownOnNextListener> mSubscriberOnNextListener;
    /*下载数据*/
    private DownInfo downInfo;

    private HttpDownManager manager ;


    public ProgressDownSubscriber(DownInfo downInfo) {
        this.mSubscriberOnNextListener = new SoftReference<>(downInfo.getListener());
        this.downInfo=downInfo;
    }

    public void setHttpDownManager(HttpDownManager manager){
        this.manager = manager ;
    }


    public void setDownInfo(DownInfo downInfo) {
        this.mSubscriberOnNextListener = new SoftReference<>(downInfo.getListener());
        this.downInfo=downInfo;
    }


    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        if(mSubscriberOnNextListener.get()!=null){
            mSubscriberOnNextListener.get().onStart();
        }
        downInfo.setState(DownState.START);
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {
        if(mSubscriberOnNextListener.get()!=null){
            mSubscriberOnNextListener.get().onComplete();
        }
        HttpDownManager.getInstance().remove(downInfo);
        downInfo.setState(DownState.FINISH);
        DbDownUtil.getInstance().update(downInfo);
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        if(mSubscriberOnNextListener.get()!=null){
            mSubscriberOnNextListener.get().onError(e);
        }
        HttpDownManager.getInstance().remove(downInfo);
        downInfo.setState(DownState.ERROR);
        DbDownUtil.getInstance().update(downInfo);
    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener.get() != null) {
            mSubscriberOnNextListener.get().onNext(t);
        }
    }

    @Override
    public void update(long read, long count, boolean done) {
        if(downInfo.getCountLength()>count){
            read=downInfo.getCountLength()-count+read;
        }else{
            downInfo.setCountLength(count);
        }
        downInfo.setReadLength(read);

        /*接受进度消息，造成UI阻塞，如果不需要显示进度可去掉实现逻辑，减少压力*/
        rx.Observable.just(read).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                  /*如果暂停或者停止状态延迟，不需要继续发送回调，影响显示*/
                        if(downInfo.getState()==DownState.PAUSE||downInfo.getState()==DownState.STOP)return;
                        downInfo.setState(DownState.DOWN);
                        if(manager != null){
                            manager.notifyDownloadStateChanged(downInfo);
                            manager.notifyDownloadProgressed(downInfo);
                        }
                        if (mSubscriberOnNextListener.get() != null) {
                            mSubscriberOnNextListener.get().updateProgress(aLong, downInfo.getCountLength());
                        }
                    }
                });
    }


}