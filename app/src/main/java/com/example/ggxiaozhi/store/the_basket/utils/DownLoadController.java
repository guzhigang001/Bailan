package com.example.ggxiaozhi.store.the_basket.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Environment;
import android.os.SystemClock;
import android.view.View;

import com.example.ggxiaozhi.store.the_basket.R;
import com.example.ggxiaozhi.store.the_basket.bean.AppBean;
import com.example.ggxiaozhi.store.the_basket.view.widget.CommomDialog;
import com.example.ggxiaozhi.store.the_basket.view.widget.DownloadProgressButton;
import com.zhxu.library.download.DownInfo;
import com.zhxu.library.download.DownState;
import com.zhxu.library.download.HttpDownManager;
import com.zhxu.library.listener.HttpDownOnNextListener;
import com.zhxu.library.utils.DbDownUtil;

import java.io.File;

import static com.example.ggxiaozhi.store.the_basket.utils.UIUtils.runOnUiThread;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.utils
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/29
 * 功能   ：
 */

public class DownLoadController {

    private DownInfo downInfo;//表示当前任务是否下载过
    private HttpDownManager mHttpDownManager;//下载请求的类
    private DbDownUtil mDownUtil;//断点续传存入数据库的工具类(GreenDAO)
    private Context mContext;
    private String downloadUrl;
    private DownloadProgressButton detail_download_button;

    private String packageName;
    private File outFile;//下载文件存储的位置

    private boolean isDestroy = false;
    private AppBean mAppBean;

    public DownLoadController(Context context, View view, String downloadUrl, AppBean appBean) {
        detail_download_button = (DownloadProgressButton) view.findViewById(R.id.downbtn);
        this.downloadUrl = downloadUrl;
        this.packageName = appBean.getPackageName();
        this.mContext = context;
        this.mAppBean = appBean;
        mHttpDownManager = HttpDownManager.getInstance();
        mDownUtil = DbDownUtil.getInstance();
        downInfo = mDownUtil.queryDownBy((long) packageName.hashCode());//表示当前任务是否下载过
        outFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), packageName + ".apk");
        downLoad();
    }

    public DownLoadController() {

    }

    public void downLoad() {

        if (downInfo == null) {//从未下载设置Text初始值
            //将文件长度传化成文本大小
//            detail_download_button.setStartText("安装" + android.text.format.Formatter.formatFileSize(UIUtils.getContext(), Long.parseLong(bean.getSize())));
//            detail_download_button.setState(DownloadProgressButton.STATUS_PROGRESS_BAR_BEGIN);
        } else {
            if (downInfo.getState() == DownState.DOWN) {
                detail_download_button.setState(DownloadProgressButton.STATUS_PROGRESS_BAR_DOWNLOADING);
                downInfo.setListener(downLoadListener);//目的是调用updateProgress中的setProgress(progress);更新进度
                mHttpDownManager.startDown(downInfo);
            }
            if (downInfo.getState() == DownState.PAUSE) {
                detail_download_button.setState(DownloadProgressButton.STATUS_PROGRESS_BAR_PAUSE);
                downInfo.setListener(downLoadListener);
            }
            if (downInfo.getState() == DownState.FINISH) {
                detail_download_button.setState(DownloadProgressButton.STATUS_PROGRESS_BAR_FINISH);
            }
        }

        detail_download_button.setStateChangeListener(new DownloadProgressButton.StateChangeListener() {
            @Override
            public void onPauseTask() {
                mHttpDownManager.pause(downInfo);
            }

            @Override
            public void onFinishTask() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(2000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                new CommomDialog(mContext, R.style.dialog, "下载成功" + "  " + "是否安装?", new CommomDialog.OnCloseListener() {
                                    @Override
                                    public void onClick(Dialog dialog, boolean confirm) {
                                        if (confirm) {
                                            AppInfoUtils.install(downInfo.getSavePath(), outFile);
                                            if (mDownUtil != null && downInfo != null) {
                                                mDownUtil.update(downInfo);
                                            }
                                        } else {
                                            mDownUtil.deleteDowninfo(downInfo);
                                            detail_download_button.setState(DownloadProgressButton.STATUS_PROGRESS_BAR_BEGIN);
                                        }
                                        dialog.dismiss();
                                    }
                                }).setPositiveButton("确定").setNegativeButton("取消").setTitle(mAppBean.getName()).show();

                            }
                        });
                    }
                }).start();
            }

            @Override
            public void onLoadingTask() {
                detail_download_button.setMax(100);
                if (downInfo == null) {//表示从未下过该应用
                    downInfo = new DownInfo(downloadUrl);

                    downInfo.setListener(downLoadListener);//数据进度的监听
                    downInfo.setId((long) packageName.hashCode());
                    downInfo.setSavePath(outFile.getAbsolutePath());
                    downInfo.setState(DownState.START);
                    mDownUtil.save(downInfo);
                }
                if (downInfo.getState() != DownState.FINISH)
                    mHttpDownManager.startDown(downInfo);
            }
        });
    }

    private HttpDownOnNextListener downLoadListener = new HttpDownOnNextListener() {
        @Override
        public void onNext(Object o) {
        }

        @Override
        public void onStart() {
        }

        @Override
        public void onComplete() {
        }

        @Override
        public void updateProgress(long readLength, long countLength) {
            int progress = (int) ((readLength * 100) / countLength);
            detail_download_button.setProgress(progress);
        }
    };

    public void onDestroyUpdata() {
        /**
         * 此种情况为-->当下载暂停时 将下载的进度更新的数据库 下次进入时可从断电续传
         */
        if (mDownUtil != null && downInfo != null) {
            isDestroy = true;
            mDownUtil.update(downInfo);
        }
    }


    public void setPasue() {
        mHttpDownManager.pause(downInfo);
        if (mDownUtil != null && downInfo != null) {
            isDestroy = true;
            mDownUtil.update(downInfo);
        }
    }
}
