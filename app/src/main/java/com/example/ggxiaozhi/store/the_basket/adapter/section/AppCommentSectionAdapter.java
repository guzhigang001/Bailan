package com.example.ggxiaozhi.store.the_basket.adapter.section;

import android.content.Context;
import android.view.View;
import android.widget.RatingBar;

import com.example.ggxiaozhi.store.the_basket.R;
import com.example.ggxiaozhi.store.the_basket.bean.AppCommentBean;
import com.zhxu.recyclerview.base.ViewHolder;
import com.zhxu.recyclerview.section.StatelessSection;

import java.util.List;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.adapter.section
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/28
 * 功能   ：
 */

public class AppCommentSectionAdapter extends StatelessSection {

    private List<AppCommentBean.CommentsBean> commentsBeanList;
    private String title;
    private Context mContext;

    public AppCommentSectionAdapter(Context context, String title, List<AppCommentBean.CommentsBean> commentsBeanList) {
        super(R.layout.appdetail_comment_list_title, R.layout.appdetail_comment_list_item);
        this.commentsBeanList = commentsBeanList;
        this.mContext = context;
        this.title = title;
    }

    @Override
    public int getContentItemsTotal() {
        return commentsBeanList.size();
    }

    @Override
    public ViewHolder getItemViewHolder(View view, int viewType) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(ViewHolder holder, int position) {
        AppCommentBean.CommentsBean commentsBean = commentsBeanList.get(position);
        holder.setText(R.id.detail_comment_user_textview, commentsBean.getAccountName());
        holder.setText(R.id.detail_comment_time_textview, commentsBean.getOperTime());
        holder.setText(R.id.detail_comment_user_client_textview, commentsBean.getPhone());
        holder.setText(R.id.detail_comment_content_textview, commentsBean.getCommentInfo());
        ((ItemViewHolder) holder).detail_comment_stars_ratingbar.setRating(Float.parseFloat(commentsBean.getStars()));
        holder.setText(R.id.detail_comment_version_textview, commentsBean.getVersionName());
        holder.setImageUrl(R.id.detail_comment_user_icon_imageview, commentsBean.getPhotoUrl());
        holder.setText(R.id.detail_comment_approve_counts_textview, commentsBean.getApproveCounts());
        holder.setText(R.id.detail_comment_reply_button_textview, commentsBean.getReplyCounts());
    }

    @Override
    public ViewHolder getHeaderViewHolder(Context context, View view) {
        return new ViewHolder(context, view);
    }

    @Override
    public void onBindHeaderViewHolder(ViewHolder holder) {
        super.onBindHeaderViewHolder(holder);
        holder.setText(R.id.titleText, title);
    }

    class ItemViewHolder extends ViewHolder {

        RatingBar detail_comment_stars_ratingbar;

        public ItemViewHolder(View view) {
            super(mContext, view);
            detail_comment_stars_ratingbar = (RatingBar) view.findViewById(R.id.detail_comment_stars_ratingbar);
        }
    }
}
