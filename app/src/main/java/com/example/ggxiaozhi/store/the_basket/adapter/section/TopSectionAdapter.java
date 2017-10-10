package com.example.ggxiaozhi.store.the_basket.adapter.section;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ggxiaozhi.store.the_basket.R;
import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.bean.AppBean;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity.AppDetailActivity;
import com.example.ggxiaozhi.store.the_basket.utils.DownLoadController;
import com.example.ggxiaozhi.store.the_basket.view.widget.DownloadProgressButton;
import com.zhxu.library.utils.DbDownUtil;
import com.zhxu.recyclerview.base.ViewHolder;
import com.zhxu.recyclerview.section.StatelessSection;

import java.util.List;

import butterknife.OnClick;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.adapter.section
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/24
 * 时间   ： 16:27
 * 功能   ：
 */

public class TopSectionAdapter extends StatelessSection {

    private List<AppBean> mAppBeanList;
    private String title;
    private Context mContext;
    private DownLoadController mController;

    public TopSectionAdapter(List<AppBean> mAppBeanList, String title, Context mContext) {
        super(R.layout.applistitem_titlecard, R.layout.applistitem_normal);
        this.mAppBeanList = mAppBeanList;
        this.mContext = mContext;
        this.title = title;

    }

    @Override
    public int getContentItemsTotal() {
        return mAppBeanList.size();
    }

    @Override
    public ViewHolder getItemViewHolder(View view, int viewType) {
        return new ViewHolder(mContext, view);
    }

    @Override
    public void onBindItemViewHolder(ViewHolder holder, int position) {
        View view = holder.getConvertView();
        mController = new DownLoadController(mContext, view, mAppBeanList.get(position).getDownurl(), mAppBeanList.get(position));
        final AppBean appBean = mAppBeanList.get(position);
        final DownloadProgressButton downbtn = (DownloadProgressButton) view.findViewById(R.id.downbtn);
        holder.setText(R.id.appSerial, appBean.getAliasName());
        holder.setImageUrl(R.id.appicon, appBean.getIcon());
        holder.setText(R.id.ItemTitle, appBean.getName());
        holder.setText(R.id.ItemText_star, appBean.getSizeDesc());
        holder.setText(R.id.memo, appBean.getMemo());
        holder.setOnClickListener(R.id.AppListItem, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AppDetailActivity.class);
                intent.putExtra("packageName", appBean.getPackageName());
                intent.putExtra("isMove", true);
//                downbtn.setState(DownloadProgressButton.STATUS_PROGRESS_BAR_BEGIN);
//                mController.setPasue();
//                mContext.startActivity(intent);
            }
        });

    }


    @Override
    public ViewHolder getHeaderViewHolder(Context context, View view) {
        return new ViewHolder(context, view);
    }

    @Override
    public void onBindHeaderViewHolder(ViewHolder holder) {
        holder.setText(R.id.ItemTitle, title);
    }

    public void updataDownState() {
        mController.onDestroyUpdata();
    }


}
