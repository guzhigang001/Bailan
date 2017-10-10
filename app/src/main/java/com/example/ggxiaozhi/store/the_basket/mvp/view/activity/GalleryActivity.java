package com.example.ggxiaozhi.store.the_basket.mvp.view.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.ggxiaozhi.store.the_basket.R;
import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 专门用于显示应用截图的activity
 */
public class GalleryActivity extends BaseActivity  implements ViewPager.OnPageChangeListener{
    @BindView(R.id.gallery)
    ViewPager mViewPager;
    @BindView(R.id.img_choose)
    ViewGroup imgViewGroup;

    private ImageView[] choose;
    private int curOffset = -1;

    private List<String> urlList;
    private int tag;


    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_gallery);
    }

    @Override
    protected void initView() {
        tag = getIntent().getIntExtra("tag", 0);
        curOffset = tag;
        urlList = getIntent().getStringArrayListExtra("urlList");

        showView();
    }


    private void initViewPager() {
        GalleryAdapter adapter = new GalleryAdapter(this);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(curOffset);
        mViewPager.setOnPageChangeListener(this);

    }


    private void showView() {
        choose = new ImageView[urlList.size()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < urlList.size(); i++) {
            choose[i] = new ImageView(this);
            choose[i].setImageDrawable(getResources().getDrawable(R.drawable.detail_point_normal));
            if (i == curOffset)
                choose[i].setImageDrawable(getResources().getDrawable(R.drawable.detail_point_selected));
            layoutParams.leftMargin = (int) getResources().getDimension(R.dimen.detail_screen_point_margin);
            choose[i].setLayoutParams(layoutParams);

            imgViewGroup.addView(this.choose[i]);
        }

        initViewPager();
    }

    private void recycleImage(View view) {
        if (!(view instanceof ImageView)) {
            ImageView imageView = (ImageView) view;
            Drawable drawable = imageView.getDrawable();
            if (drawable == null || !(drawable instanceof BitmapDrawable)) {
                return;
            } else {
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                if (bitmap == null || bitmap.isRecycled()) {
                    return;
                }
                bitmap.recycle();
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        choose[position].setImageDrawable(getResources().getDrawable(R.drawable.detail_point_selected));
        choose[curOffset].setImageDrawable(getResources().getDrawable(R.drawable.detail_point_normal));
        curOffset = position;

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }




    public class GalleryAdapter extends PagerAdapter {
        private Context context = null;
        private List<View> viewList = new ArrayList();

        protected GalleryAdapter(Context context) {
            this.context = context;
        }

        @Override
        public void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject) {
            View localView = (View) paramObject;
            paramViewGroup.removeView(localView);
            recycleImage(localView);
            this.viewList.remove(localView);
        }

        @Override
        public int getCount() {
            return urlList.size();
        }

        @Override
        public Object instantiateItem(ViewGroup paramViewGroup, int position) {
            ImageView imageView = new ImageView(this.context);
            this.viewList.add(imageView);
            paramViewGroup.addView(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            Glide.with(UIUtils.getContext()).load(urlList.get(position)).into(imageView);
            return imageView;
        }

        @Override
        public boolean isViewFromObject(View paramView, Object paramObject) {
            return paramView == paramObject;
        }
    }
}