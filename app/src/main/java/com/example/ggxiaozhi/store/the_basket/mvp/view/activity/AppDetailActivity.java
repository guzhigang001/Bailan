package com.example.ggxiaozhi.store.the_basket.mvp.view.activity;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ggxiaozhi.store.the_basket.R;
import com.example.ggxiaozhi.store.the_basket.adapter.SubTabPageAdapter;
import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BaseMvpActivity;
import com.example.ggxiaozhi.store.the_basket.bean.AppDetailBean;
import com.example.ggxiaozhi.store.the_basket.mvp.interactor.AppDateilActivityInteractor;
import com.example.ggxiaozhi.store.the_basket.mvp.presenter.AppDetailActivityPresenter;
import com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl.AppDetailActivityPresenterImpl;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity_view.AppDetailActivityView;
import com.example.ggxiaozhi.store.the_basket.mvp.view.fragment.appdetails.AppCommentarieFragment;
import com.example.ggxiaozhi.store.the_basket.mvp.view.fragment.appdetails.AppIntroduceFragment;
import com.example.ggxiaozhi.store.the_basket.mvp.view.fragment.appdetails.AppRecommendFragment;
import com.example.ggxiaozhi.store.the_basket.utils.AppInfoUtils;
import com.example.ggxiaozhi.store.the_basket.utils.UIUtils;
import com.example.ggxiaozhi.store.the_basket.view.widget.DetailShareButton;
import com.example.ggxiaozhi.store.the_basket.view.widget.DownloadProgressButton;
import com.example.ggxiaozhi.store.the_basket.view.widget.SubTabNavigator;
import com.zhxu.library.download.DownInfo;
import com.zhxu.library.download.DownState;
import com.zhxu.library.download.HttpDownManager;
import com.zhxu.library.listener.HttpDownOnNextListener;
import com.zhxu.library.utils.DbDownUtil;

import java.io.File;
import java.io.Serializable;
import java.text.Format;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 应用详情页
 */
public class AppDetailActivity extends BaseMvpActivity<AppDetailActivityPresenterImpl> implements AppDetailActivityView<AppDetailBean>, HttpDownManager.DownloadObserver {

    private static final String TAG = "AppDetailActivity";
    @Inject
    AppDetailActivityPresenterImpl mPresenter;

    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.iv_search)
    ImageView iv_search;

    @BindView(R.id.detail_head_app_icon_imageview)
    ImageView detail_app_icon;//应用图片
    @BindView(R.id.detail_head_app_name_textview)
    TextView detail_app_name;//应用名称
    @BindView(R.id.detail_head_download_count_textview)
    TextView detail_app_download_count;//下载个数
    @BindView(R.id.detail_head_app_stars_ratingbar)
    RatingBar detail_app_stars;//评星
    @BindView(R.id.detail_head_label_layout_linearlayout)
    RelativeLayout detail_head_label_layout;//包含 官方 广告 病毒 人工复检位子 和右侧向下箭头
    @BindView(R.id.detail_head_label_icon_layout_linearlayout)
    LinearLayout detail_head_label_icon_layout;//包含 官方 广告 病毒 人工复检
    @BindView(R.id.detail_head_lable_folding_textview)
    TextView detail_head_lable_folding;//右侧向下箭头
    @BindView(R.id.detail_head_safe_icon_container_linearlayout)
    LinearLayout detail_head_safe_icon_container;//检测内部布局 用于添加控件
    @BindView(R.id.detail_head_safe_icon_layout_linearlayout)
    LinearLayout detail_head_safe_icon_layout;//检测外侧布局 用于配合监听显示和展开
    @BindView(R.id.subTab)
    SubTabNavigator subTabNavigator;
    @BindView(R.id.vp)
    ViewPager mViewPager;
    @BindView(R.id.appdetail_head)
    LinearLayout appdetail_head;
    @BindView(R.id.detail_download_button)
    DownloadProgressButton detail_download_button;
    @BindView(R.id.detail_download_share_button)
    DetailShareButton detail_download_share_button;
    @BindView(R.id.detail_download_comment_button_linearlayout)
    LinearLayout detail_download_comment_button_linearlayout;

    private boolean isExpend = false;//标记检测的水平LinerLayout是否展开

    private HttpDownManager mHttpDownManager;//下载请求的类
    private DbDownUtil mDownUtil;//断点续传存入数据库的工具类(GreenDAO)
    private DownInfo downInfo;//表示当前任务是否下载过

    private File outFile;//下载文件存储的位置
    private String packageName;

    private boolean isMove = false;

    private boolean isPasue = false;

    private boolean isEmpty = false;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        packageName = intent.getStringExtra("packageName");
        isMove = intent.getBooleanExtra("isMove", false);
        mHttpDownManager = HttpDownManager.getInstance();
        mHttpDownManager.registerObserver(this);
        mDownUtil = DbDownUtil.getInstance();

        downInfo = mDownUtil.queryDownBy((long) packageName.hashCode());//表示当前任务是否下载过
        if (downInfo == null)
            outFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), packageName + ".apk");
        mPresenter.getAppDetailData(this, packageName);
    }

    @Override
    protected AppDetailActivityPresenterImpl initInjector() {
        //完成依赖注入
        build.inject(this);
        //返回注入的对象
        return mPresenter;
    }

    @Override
    protected void initLayout() {
        if (!isEmpty)
            setContentView(R.layout.activity_app_detail);
        else
            setContentView(R.layout.loading_page_empty);
    }

    @Override
    protected void initView() {
        //设置沉浸式状态栏
        setStatus();
        iv_search.setVisibility(View.VISIBLE);//右侧搜索图标
        //设置沉浸式状态栏背景
        if (bar_layout != null)
            bar_layout.setBackgroundResource(R.color.black_alpha_5);
        title_text.setText(getResources().getString(R.string.title_activity_app_detail));
    }

    @Override
    public void getAppDetailDataSuccess(AppDetailBean bean) {
        /*设置顶部信息*/
        setDetailHead(bean);
        /*设置应用描述信息(官方 广告 病毒 人工复检)*/
        setLable(bean);
        setSafeLabel(bean);

        /*设置检测布局展开 关闭*/
        detail_head_label_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpend) {
                    isExpend = false;
                    detail_head_safe_icon_layout.setVisibility(View.GONE);
                    detail_head_lable_folding.setBackgroundResource(R.drawable.ic_public_arrow_down);
                } else {
                    isExpend = true;
                    detail_head_safe_icon_layout.setVisibility(View.VISIBLE);
                    detail_head_lable_folding.setBackgroundResource(R.drawable.ic_public_arrow_up);
                }
            }
        });

        /*设置SubTabNavigator布局数据*/
        setSubTab(bean);
        /*下载请求*/
        setDownLoad(bean);
    }

    private void setDownLoad(final AppDetailBean bean) {

        if (downInfo == null) {//从未下载设置Text初始值
            //将文件长度传化成文本大小
            detail_download_button.setStartText("安装" + android.text.format.Formatter.formatFileSize(UIUtils.getContext(), Long.parseLong(bean.getSize())));
        } else {
            if (downInfo.getState() == DownState.DOWN) {
                detail_download_button.setState(DownloadProgressButton.STATUS_PROGRESS_BAR_DOWNLOADING);
                downInfo.setListener(downLoadListener);//目的是调用updateProgress中的setProgress(progress);更新进度
                mHttpDownManager.startDown(downInfo);
            } else if (downInfo.getState() == DownState.PAUSE) {
                if (isMove) {
                    if (isPasue) {
                        detail_download_button.setState(DownloadProgressButton.STATUS_PROGRESS_BAR_PAUSE);
                    } else {
                        downInfo.setListener(downLoadListener);//目的是调用updateProgress中的setProgress(progress);更新进度
                        detail_download_button.setState(DownloadProgressButton.STATUS_PROGRESS_BAR_DOWNLOADING);
                        mHttpDownManager.startDown(downInfo);
                    }
                } else {
                    detail_download_button.setState(DownloadProgressButton.STATUS_PROGRESS_BAR_PAUSE);
                }
            } else if (downInfo.getState() == DownState.FINISH) {
                detail_download_button.setState(DownloadProgressButton.STATUS_PROGRESS_BAR_BEGIN);
            }
            detail_download_button.setProgress((int) (100 * downInfo.getReadLength() / downInfo.getCountLength()));
        }

        detail_download_button.setStateChangeListener(new DownloadProgressButton.StateChangeListener() {
            @Override
            public void onPauseTask() {
                isPasue = true;
                mHttpDownManager.pause(downInfo);
            }

            @Override
            public void onFinishTask() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(3000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                AppInfoUtils.install(downInfo.getSavePath(), outFile);
                                if (mDownUtil != null && downInfo != null)
                                    mDownUtil.update(downInfo);
                            }
                        });
                    }
                }).start();
            }

            @Override
            public void onLoadingTask() {
                detail_download_button.setMax(100);
                if (downInfo == null) {//表示从未下过该应用
                    downInfo = new DownInfo(bean.getDownloadUrl());

                    downInfo.setListener(downLoadListener);//数据进度的监听
                    downInfo.setId((long) packageName.hashCode());
                    downInfo.setSavePath(outFile.getAbsolutePath());
                    downInfo.setState(DownState.START);
                    mDownUtil.save(downInfo);
                }
                if (downInfo.getState() != DownState.FINISH) {
                    mHttpDownManager.startDown(downInfo);
                }
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

    private void setSubTab(AppDetailBean bean) {
        List<String> tabInfoList = bean.getTabInfoList();
        subTabNavigator.setLeftText(tabInfoList.get(0).toString());
        subTabNavigator.setNoneText(tabInfoList.get(1).toString());
        subTabNavigator.setRightText(tabInfoList.get(2).toString());

        List<Fragment> fragments = new ArrayList<>();

        AppIntroduceFragment introduceFragment = new AppIntroduceFragment();
        AppCommentarieFragment commentarieFragment = new AppCommentarieFragment();
        AppRecommendFragment recommendFragment = new AppRecommendFragment();
        fragments.add(introduceFragment);
        fragments.add(commentarieFragment);
        fragments.add(recommendFragment);

        SubTabPageAdapter adapter = new SubTabPageAdapter(getSupportFragmentManager());
        adapter.setFragments(fragments);
        mViewPager.setAdapter(adapter);
        subTabNavigator.setViewPager(mViewPager);
        mViewPager.setOnPageChangeListener(subTabNavigator);

    }

    private void setSafeLabel(AppDetailBean bean) {
        List<AppDetailBean.SafeLabel> safeLabelList = bean.getSafeLabelList();
        for (AppDetailBean.SafeLabel safeLabel : safeLabelList) {
            View view = UIUtils.inflate(R.layout.appdetail_item_head_safe_item);
            TextView safe_checker = (TextView) view.findViewById(R.id.safe_checker_textview);//检测专员
            TextView safe_checker_label = (TextView) view.findViewById(R.id.safe_checker_label);//专员人名
            ImageView detail_head_app_icon = (ImageView) view.findViewById(R.id.detail_head_app_icon_imageview);//最左侧图片

            TextView detail_safe_desc_textview = (TextView) view.findViewById(R.id.detail_safe_desc_textview);//检测机构
            safe_checker.setText(safeLabel.getDetectorName());
            safe_checker_label.setText(safeLabel.getDetectorDesc());
            Glide.with(UIUtils.getContext()).load(safeLabel.getUrl()).into(detail_head_app_icon);
            detail_safe_desc_textview.setText(safeLabel.getName());

            detail_head_safe_icon_container.addView(view);
        }

    }

    private void setLable(AppDetailBean bean) {
        List<AppDetailBean.LabelName> labelNameList = bean.getLabelNameList();
        for (AppDetailBean.LabelName labelName : labelNameList) {
            View view = UIUtils.inflate(R.layout.appdetail_item_head_label_item);//TextView控件布局 圆角背景
            TextView label = (TextView) view.findViewById(R.id.appdetail_head_label_textview);
            label.setText(labelName.getName());
            if (labelName.getType() == 1) //0表示无广告字体颜色为默认黑色 1表示有广告字体颜色为橘黄色
                label.setTextColor(getResources().getColor(R.color.app_not_safe_textcolor));
            detail_head_label_icon_layout.addView(view);//将控件加入布局
        }
    }

    private void setDetailHead(AppDetailBean bean) {
        Glide.with(UIUtils.getContext()).load(bean.getIcoUrl()).into(detail_app_icon);
        detail_app_name.setText(bean.getName());
        detail_app_download_count.setText(bean.getIntro());
        /*原生Api RatingBar 用于设置星星评级 setRating设置星星的个数 参数为float*/
        detail_app_stars.setRating(Float.parseFloat(bean.getStars()));
    }

    @Override
    public void getAppDetailDataError(String msg) {
        isEmpty = true;
        setContentView(R.layout.loading_page_empty);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        /**
         * 此种情况为-->当下载暂停时 将下载的进度更新的数据库 下次进入时可从断电续传
         */
        if (mHttpDownManager != null) {
            mHttpDownManager.unRegisterObserver(this);
            if (downInfo != null)
                mDownUtil.update(downInfo);
        }
    }


    /**
     * 为本Activity中的Fragment提供
     *
     * @return
     */
    public String getAppPackageName() {
        return packageName;
    }

    @Override
    public void onDownloadStateChanged(DownInfo info) {
        if (info.getState() == DownState.FINISH) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(3000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AppInfoUtils.install(downInfo.getSavePath(), outFile);
                            if (mDownUtil != null && downInfo != null)
                                mDownUtil.update(downInfo);
                        }
                    });
                }
            }).start();
        }
    }

    @Override
    public void onDownloadProgressed(DownInfo info) {
        if (downInfo != null && info.getId() == downInfo.getId()) {
            detail_download_button.setProgress((int) (100 * info.getReadLength() / info.getCountLength()));
        }
    }
}
