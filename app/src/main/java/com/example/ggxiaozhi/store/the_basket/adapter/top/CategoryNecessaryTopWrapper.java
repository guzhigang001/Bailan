package com.example.ggxiaozhi.store.the_basket.adapter.top;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.example.ggxiaozhi.store.the_basket.R;
import com.example.ggxiaozhi.store.the_basket.bean.CategoryNecessaryBean;
import com.example.ggxiaozhi.store.the_basket.utils.UIUtils;
import com.zhxu.recyclerview.wrapper.HeaderAndFooterWrapper;


/**
 * <p>Description:
 *
 * @author xzhang
 */

public class CategoryNecessaryTopWrapper extends HeaderAndFooterWrapper {

    private Context mContext ;
    private View headerView ;
    private CategoryNecessaryBean.Head head ;

    public CategoryNecessaryTopWrapper(Context context, RecyclerView.Adapter adapter, CategoryNecessaryBean.Head head) {
        super(adapter);
        this.mContext = context ;
        this.head = head ;
        headerView = UIUtils.inflate(R.layout.head_category_necessary);
        addHeaderView(headerView);

        TextView intro = (TextView) headerView.findViewById(R.id.intro);
        ImageView iv = (ImageView) headerView.findViewById(R.id.iv);

        intro.setText(head.getIntro());
        Glide.with(UIUtils.getContext()).load(head.getIcon()).into(iv);


    }


}
