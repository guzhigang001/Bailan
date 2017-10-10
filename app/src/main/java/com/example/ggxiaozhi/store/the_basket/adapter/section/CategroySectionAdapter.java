package com.example.ggxiaozhi.store.the_basket.adapter.section;

import android.content.Context;
import android.view.View;
import android.widget.RatingBar;

import com.example.ggxiaozhi.store.the_basket.R;
import com.example.ggxiaozhi.store.the_basket.bean.CategoryBean;
import com.zhxu.recyclerview.base.ViewHolder;
import com.zhxu.recyclerview.section.StatelessSection;

import java.util.List;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.adapter.section
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/3
 * 时间   ： 18:52
 * 功能   ：
 */

public class CategroySectionAdapter extends StatelessSection{

    private String title;//标题
    private List<CategoryBean.CategoryDataBean> mBeen;
    private Context mContext;
    public OnItemClickListener mListener ;
    public CategroySectionAdapter(String title, List<CategoryBean.CategoryDataBean> list,Context context) {
        super(R.layout.applistitem_titlecard, R.layout.applistitem_subcat);
        this.title=title;
        this.mBeen=list;
        this.mContext=context;
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener ;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    @Override
    public int getContentItemsTotal() {
        return mBeen.size();
    }

    @Override
    public ViewHolder getItemViewHolder(View view, int viewType) {
        return new ViewHolder(mContext,view);
    }

    @Override
    public void onBindItemViewHolder(ViewHolder holder, final int position) {
        holder.setImageUrl(R.id.appicon,mBeen.get(position).getIconUrl());
        holder.setText(R.id.ItemTitle,mBeen.get(position).getName());
        holder.setOnClickListener(R.id.rl_item, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    mListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public ViewHolder getHeaderViewHolder(Context context, View view) {
        return new ViewHolder(context,view);
    }

    @Override
    public void onBindHeaderViewHolder(ViewHolder holder) {
        holder.setText(R.id.ItemTitle,title);
    }

}
