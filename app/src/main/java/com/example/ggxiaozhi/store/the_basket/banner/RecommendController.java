package com.example.ggxiaozhi.store.the_basket.banner;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.ggxiaozhi.store.the_basket.R;
import com.example.ggxiaozhi.store.the_basket.utils.UIUtils;
import com.example.ggxiaozhi.store.the_basket.view.widget.AutoGallery;
import com.example.ggxiaozhi.store.the_basket.view.widget.FlowIndicator;


import java.util.List;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.banner
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/2
 * 时间   ： 19:27
 * 功能   ：自定义轮播图控制器
 */

public class RecommendController {
    private Context mContext ;
    private View contentView ;
    private AutoGallery headline_image_gallery ;
    private FlowIndicator headline_circle_indicator ;

//    private List<String> urls = new ArrayList<>() ;

    private int gallerySelectedPositin = 0 ;//Gallery索引
    private int circleSelectedPosition = 0 ;//默认指示器

    public RecommendController(Context context){
        this.mContext = context ;
        init();
    }

    private void init(){
        contentView = UIUtils.inflate(R.layout.gallery_indicator_layout);
        /*Gallery Android原生画廊 主要用于左右滑动图片 在Api 16以后被弃用*/
        headline_image_gallery = (AutoGallery) contentView.findViewById(R.id.headline_image_gallery);//自定义Gallery轮播图布局
        headline_circle_indicator = (FlowIndicator) contentView.findViewById(R.id.headline_circle_indicator);//自定义指示器

    }

    public void setData(List<String> datas){
        setIndicator(datas);
    }

    public View getContentView() {
        return contentView;
    }

    private void setIndicator(final List<String> urls){
        //指示器
        headline_circle_indicator.setCount(urls.size());//指示器小圆点的个数
        headline_circle_indicator.setSeletion(circleSelectedPosition);//设置默认位置

        headline_image_gallery.setLength(urls.size());
        gallerySelectedPositin = urls.size() * 50 + circleSelectedPosition ;//自动滑动50次
        headline_image_gallery.setSelection(gallerySelectedPositin);//初始化选中位置
        headline_image_gallery.setDelayMillis(4000);//设置轮播时间间隔4s
        headline_image_gallery.start();//轮播开始
        headline_image_gallery.setAdapter(new GalleryAdapter(urls));
        headline_image_gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                circleSelectedPosition = position ;
                gallerySelectedPositin = circleSelectedPosition % urls.size();
                headline_circle_indicator.setSeletion(gallerySelectedPositin);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    class GalleryAdapter extends BaseAdapter {

        private List<String> urls ;
        public GalleryAdapter(List<String> urls){
            this.urls = urls ;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public Object getItem(int position) {
            return urls.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder = null ;
            if(convertView == null){
                holder = new Holder() ;
                convertView = UIUtils.inflate(R.layout.item_gallery_layout);
                holder.item_head_gallery_img = (ImageView) convertView.findViewById(R.id.item_head_gallery_img);
                convertView.setTag(holder);
            }else {
                holder = (Holder) convertView.getTag();
            }
            Glide.with(UIUtils.getContext()).load(urls.get(position % urls.size())).into(holder.item_head_gallery_img);

            return convertView;
        }
    }
    private class Holder {
        ImageView item_head_gallery_img ;
    }
}
