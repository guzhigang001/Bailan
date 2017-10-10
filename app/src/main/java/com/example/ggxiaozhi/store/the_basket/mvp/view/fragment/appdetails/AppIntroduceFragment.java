package com.example.ggxiaozhi.store.the_basket.mvp.view.fragment.appdetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.Formatter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ggxiaozhi.store.the_basket.R;
import com.example.ggxiaozhi.store.the_basket.adapter.RecommendAdapter;
import com.example.ggxiaozhi.store.the_basket.base.mvpbase.BaseMvpFragment;
import com.example.ggxiaozhi.store.the_basket.bean.AppIntroductionBean;
import com.example.ggxiaozhi.store.the_basket.mvp.presenter.AppIntroduceFragmentPresent;
import com.example.ggxiaozhi.store.the_basket.mvp.presenter.impl.AppIntroduceFragmentPresentImpl;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity.AppDetailActivity;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity.GalleryActivity;
import com.example.ggxiaozhi.store.the_basket.mvp.view.fragment_view.AppIntroduceFragmentView;
import com.example.ggxiaozhi.store.the_basket.utils.UIUtils;
import com.example.ggxiaozhi.store.the_basket.view.LoadingPager;
import com.example.ggxiaozhi.store.the_basket.view.widget.FlowLayout;
import com.example.ggxiaozhi.store.the_basket.view.widget.FoldingTextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.mvp.view.fragment.appdetails
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/26
 * 时间   ：
 * 功能   ：介绍Fragment
 */

public class AppIntroduceFragment extends BaseMvpFragment<AppIntroduceFragmentPresentImpl> implements AppIntroduceFragmentView<AppIntroductionBean>, View.OnClickListener {
    private static final String TAG = "AppIntroduceFragment";

    @BindView(R.id.app_detail_gallery_container_linearlayout)
    LinearLayout app_detail_gallery_container;//HorizontalScrollView包裹的水平布局 用于填充水平滑动的图片
    @BindView(R.id.detail_appinfo_tariff_textview)
    TextView appInfoTariff;
    @BindView(R.id.detail_appinfo_size_textview)
    TextView appInfoSize;
    @BindView(R.id.detail_appinfo_version_textview)
    TextView appInfoVersion;
    @BindView(R.id.detail_appinfo_release_date_textview)
    TextView appInfoDate;
    @BindView(R.id.appdetail_appinfo_developer_textview)
    TextView appInfoDeveloper;
    @BindView(R.id.ll)
    LinearLayout appInfoDes;//FoldingTextView竖直容器
    @BindView(R.id.flowLayout)
    FlowLayout flowLayout;//自定义根据控件设置宽高的ViewGroup

    private AppIntroductionBean mIntroductionBean;

    private String packageName;

    @Inject
    AppIntroduceFragmentPresentImpl mPresent;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        packageName = ((AppDetailActivity) getActivity()).getAppPackageName();
        show();
    }


    @Override
    protected AppIntroduceFragmentPresentImpl initInjector() {
        mFragmentComponent.inject(this);
        return mPresent;
    }

    @Override
    protected View cretaeSuccessView() {
        View view = UIUtils.inflate(R.layout.fragment_app_introduction);
        ButterKnife.bind(this, view);

        /*应用截图数据*/
        for (int i = 0; i < mIntroductionBean.getImageCompressList().size(); i++) {
            String url = mIntroductionBean.getImageCompressList().get(i);
            View screenView = View.inflate(getContext(), R.layout.appdetail_item_screen_image, null);
            ImageView screenImageView = (ImageView) screenView.findViewById(R.id.appdetail_screen_img_imageview);
            //设置图片描述(一般用户是看不到的)
            screenImageView.setContentDescription(screenImageView.getResources().getString(R.string.appdetail_screenshot));
            //设置图片的放大模式
            screenImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            screenView.setOnClickListener(this);
            screenView.setTag(i);
            Glide.with(UIUtils.getContext()).load(url).into(screenImageView);
            app_detail_gallery_container.addView(screenView);
        }

        /*应用信息描述*/
        appInfoTariff.setText(mIntroductionBean.getAppInfoBean().getTariffDesc());
        appInfoSize.setText(Formatter.formatFileSize(getContext(), Long.parseLong(mIntroductionBean.getAppInfoBean().getSize())));
        appInfoDate.setText(mIntroductionBean.getAppInfoBean().getReleaseDate());
        appInfoVersion.setText(mIntroductionBean.getAppInfoBean().getVersion());
        appInfoDeveloper.setText(mIntroductionBean.getAppInfoBean().getDeveloper());

        for (int i = 0; i < mIntroductionBean.getAppDetailInfoBeanList().size(); i++) {
            FoldingTextView foldingTextView = new FoldingTextView(getContext());
            foldingTextView.setTitle(mIntroductionBean.getAppDetailInfoBeanList().get(i).getTitle());
            foldingTextView.setContent(mIntroductionBean.getAppDetailInfoBeanList().get(i).getBody());
            appInfoDes.addView(foldingTextView);
        }

        //应用标签数据
        List<String> tagList = mIntroductionBean.getTagList();
        for (int i = 0; i < tagList.size(); i++) {
            View labView = UIUtils.inflate(R.layout.appdetail_item_label_item);
            TextView tv = (TextView) labView.findViewById(R.id.appdetail_label_content_textview);
            tv.setText(tagList.get(i));
            flowLayout.addView(labView);
        }

        return view;
    }

    @Override
    protected void load() {
        mPresent.getAppIntroduceFragmentData(mBaseActivity, packageName);
    }

    @Override
    public void onAppIntroduceDataSuccess(AppIntroductionBean bean) {
        setState(LoadingPager.LoadResult.success);
        this.mIntroductionBean = bean;


    }

    @Override
    public void onAppIntroduceDataError(String msg) {
        setState(LoadingPager.LoadResult.error);
    }

    @Override
    public void onClick(View v) {
        int tag = (int) v.getTag();
        List<String> images = mIntroductionBean.getImagesList();
        Intent intent = new Intent(mBaseActivity, GalleryActivity.class);
        intent.putExtra("tag", tag);
        intent.putStringArrayListExtra("urlList", (ArrayList<String>) images);
        mBaseActivity.startActivity(intent);
    }
}
