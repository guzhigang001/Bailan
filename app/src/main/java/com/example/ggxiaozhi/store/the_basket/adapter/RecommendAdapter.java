package com.example.ggxiaozhi.store.the_basket.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ggxiaozhi.store.the_basket.R;
import com.example.ggxiaozhi.store.the_basket.bean.AppBean;
import com.example.ggxiaozhi.store.the_basket.bean.RecommendBean;
import com.zhxu.recyclerview.adapter.CommonAdapter;
import com.zhxu.recyclerview.adapter.MultiItemTypeAdapter;
import com.zhxu.recyclerview.base.ItemViewDelegate;
import com.zhxu.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.adapter
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/2
 * 时间   ： 15:46
 * 功能   ：推荐页布局适配器
 */

public class RecommendAdapter extends MultiItemTypeAdapter<RecommendBean.RecommendAppBean> {

    private Context mContext;
    private OnItemClickListenter mlistener;

    /**
     * 给Recommend调用的接口回调 具体处理交给调用者
     */
    public interface OnItemClickListenter {
        void goDetailActivity(String packageName);
    }

    public void setOnItemClickListenter(OnItemClickListenter listener) {
        this.mlistener = listener;
    }

    public RecommendAdapter(Context context, List<RecommendBean.RecommendAppBean> datas) {
        super(context, datas);
        this.mContext = context;

        addItemViewDelegate(new AdItemDelegate());
        addItemViewDelegate(new AppItemDelegate());
    }


    /**
     * 广告的适配器
     */
    public class AdItemDelegate implements ItemViewDelegate<RecommendBean.RecommendAppBean> {

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_ad;
        }

        @Override
        public boolean isForViewType(RecommendBean.RecommendAppBean item, int position) {
            return item.getType() == 1;
        }

        @Override
        public void convert(ViewHolder holder, RecommendBean.RecommendAppBean recommendAppBean, int position) {
            holder.setImageUrl(R.id.iv_ad1, recommendAppBean.getIconList().get(0));
            holder.setImageUrl(R.id.iv_ad2, recommendAppBean.getIconList().get(1));
            holder.setOnClickListener(R.id.iv_ad1, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "暂无数据(0)", Toast.LENGTH_SHORT).show();
                }
            });
            holder.setOnClickListener(R.id.iv_ad2, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "暂无数据(1)", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * app条目的适配器
     */
    public class AppItemDelegate implements ItemViewDelegate<RecommendBean.RecommendAppBean> {
        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_applist_horizontal;
        }

        @Override
        public boolean isForViewType(RecommendBean.RecommendAppBean item, int position) {
            return item.getType() == 0;
        }

        @Override
        public void convert(ViewHolder holder, final RecommendBean.RecommendAppBean recommendAppBean, int position) {
            holder.setText(R.id.tv_item_title, recommendAppBean.getTitle());
            RecyclerView recyclerView = holder.getView(R.id.rv_applist_item);
            LinearLayoutManager manager = new LinearLayoutManager(mContext);
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(manager);
            AppItemAdapter adapter = new AppItemAdapter(mContext);
            adapter.addDataAll(recommendAppBean.getAppList());
            recyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
                    String packageName = recommendAppBean.getAppList().get(position).getPackageName();
                    mlistener.goDetailActivity(packageName);
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
                    return false;
                }
            });

        }
    }

    private class AppItemAdapter extends CommonAdapter<AppBean> {
        public AppItemAdapter(Context context) {
            super(context, R.layout.item_app);
        }

        @Override
        protected void convert(ViewHolder holder, AppBean appBean, int position) {
            holder.setImageUrl(R.id.iv_app_icon, appBean.getIcon());
            holder.setText(R.id.tv_app_name, appBean.getName());
            holder.setText(R.id.tv_app_size, appBean.getIntro());
        }
    }

}
