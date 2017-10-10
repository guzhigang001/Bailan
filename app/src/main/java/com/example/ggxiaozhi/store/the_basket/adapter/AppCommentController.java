package com.example.ggxiaozhi.store.the_basket.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;


import com.example.ggxiaozhi.store.the_basket.R;
import com.example.ggxiaozhi.store.the_basket.bean.AppCommentBean;
import com.example.ggxiaozhi.store.the_basket.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.adapter.top
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/2
 * 功能   ：评论页头部适配器具体布局及赋值的类
 */

public class AppCommentController {

    private Context mContext;
    private View contentView;

    private TextView commentScore;
    private RatingBar commentStars;
    private TextView commentCount;
    private ProgressBar fiveStarsProgressBar;
    private ProgressBar fourStarsProgressBar;
    private ProgressBar threeStarsProgressBar;
    private ProgressBar twoStarsProgressBar;
    private ProgressBar oneStarsProgressBar;


    public AppCommentController(Context context) {
        this.mContext = context;
        init();
    }

    private void init() {
        contentView = UIUtils.inflate(R.layout.appdetail_comment_score);
        commentScore = (TextView) contentView.findViewById(R.id.app_comment_score_textview);
        commentStars = (RatingBar) contentView.findViewById(R.id.detail_comment_colligation_stars_ratingbar);
        commentCount = (TextView) contentView.findViewById(R.id.detail_comments_count_textview);
        fiveStarsProgressBar = (ProgressBar) contentView.findViewById(R.id.detail_comment_five_stars_proportion_progressbar);
        fourStarsProgressBar = (ProgressBar) contentView.findViewById(R.id.detail_comment_four_stars_proportion_progressbar);
        threeStarsProgressBar = (ProgressBar) contentView.findViewById(R.id.detail_comment_three_stars_proportion_progressbar);
        twoStarsProgressBar = (ProgressBar) contentView.findViewById(R.id.detail_comment_two_stars_proportion_progressbar);
        oneStarsProgressBar = (ProgressBar) contentView.findViewById(R.id.detail_comment_one_stars_proportion_progressbar);
    }

    public void setData(AppCommentBean appCommentBean) {
        commentScore.setText(appCommentBean.getScore());
        commentStars.setRating(Float.parseFloat(appCommentBean.getStarts()));
        int count = 0;
        for (AppCommentBean.RatingDstListBean ratingDstListBean : appCommentBean.getRatingDstList()) {
            count = count + ratingDstListBean.getRatingCounts();
        }
        commentCount.setText(count + " 人评分");

        fiveStarsProgressBar.setMax(count);
        fourStarsProgressBar.setMax(count);
        threeStarsProgressBar.setMax(count);
        twoStarsProgressBar.setMax(count);
        oneStarsProgressBar.setMax(count);


        if (appCommentBean.getRatingDstList().size() < 5) {
            List<Integer> list = new ArrayList<>();
            for (AppCommentBean.RatingDstListBean bean : appCommentBean.getRatingDstList()) {
                list.add(bean.getRating());
            }

            if (!list.contains(1)) {
                appCommentBean.getRatingDstList().add(0, new AppCommentBean.RatingDstListBean(1, 0));
            }
            if (!list.contains(2)) {
                appCommentBean.getRatingDstList().add(1, new AppCommentBean.RatingDstListBean(2, 0));
            }
            if (!list.contains(3)) {
                appCommentBean.getRatingDstList().add(2, new AppCommentBean.RatingDstListBean(3, 0));
            }
            if (!list.contains(4)) {
                appCommentBean.getRatingDstList().add(3, new AppCommentBean.RatingDstListBean(4, 0));
            }
            if (!list.contains(5)) {
                appCommentBean.getRatingDstList().add(4, new AppCommentBean.RatingDstListBean(5, 0));
            }

        }

        fiveStarsProgressBar.setProgress(appCommentBean.getRatingDstList().get(4).getRatingCounts());
        fourStarsProgressBar.setProgress(appCommentBean.getRatingDstList().get(3).getRatingCounts());
        threeStarsProgressBar.setProgress(appCommentBean.getRatingDstList().get(2).getRatingCounts());
        twoStarsProgressBar.setProgress(appCommentBean.getRatingDstList().get(1).getRatingCounts());
        oneStarsProgressBar.setProgress(appCommentBean.getRatingDstList().get(0).getRatingCounts());


    }


    public View getContentView() {
        return contentView;
    }

}
