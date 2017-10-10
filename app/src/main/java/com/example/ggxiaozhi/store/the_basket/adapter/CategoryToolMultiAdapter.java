package com.example.ggxiaozhi.store.the_basket.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.example.ggxiaozhi.store.the_basket.R;
import com.example.ggxiaozhi.store.the_basket.bean.AppBean;
import com.example.ggxiaozhi.store.the_basket.bean.CategoryToolBean;
import com.zhxu.recyclerview.adapter.CommonAdapter;
import com.zhxu.recyclerview.adapter.MultiItemTypeAdapter;
import com.zhxu.recyclerview.base.ItemViewDelegate;
import com.zhxu.recyclerview.base.ViewHolder;




public class CategoryToolMultiAdapter extends MultiItemTypeAdapter<CategoryToolBean.CategoryToolAppBean> {

    private AppItemListener mListener ;
    private Context mContext ;
    public CategoryToolMultiAdapter(Context context) {
        super(context);
        this.mContext = context ;
        addItemViewDelegate(new AppDelegate()) ;
        addItemViewDelegate(new AdDelegate()) ;

    }

    public void setAppItemListener(AppItemListener listener){
        this.mListener = listener ;
    }

    public interface AppItemListener{
        void goAppDetail(String packageName) ;
    }

    /** App横向列表 */
    public class AppDelegate implements ItemViewDelegate<CategoryToolBean.CategoryToolAppBean> {

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_applist_horizontal;
        }

        @Override
        public boolean isForViewType(CategoryToolBean.CategoryToolAppBean item, int position) {
            return item.getType() == 0;
        }

        @Override
        public void convert(ViewHolder holder, final CategoryToolBean.CategoryToolAppBean categoryToolAppBean, int position) {
            holder.setText(R.id.tv_item_title,categoryToolAppBean.getTitle());
            RecyclerView rv = holder.getView(R.id.rv_applist_item);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rv.setLayoutManager(linearLayoutManager);

            AppItemAdapter adapter = new AppItemAdapter(mContext) ;
            adapter.addDataAll(categoryToolAppBean.getAppList());
            rv.setAdapter(adapter);

            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
                    if(mListener != null){
                        mListener.goAppDetail(categoryToolAppBean.getAppList().get(position).getPackageName());
                    }
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
                    return false;
                }
            });

            //更多按钮的点击事件
            holder.setOnClickListener(R.id.more_btn, new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }) ;

        }
    }

    /** 广告列表 */
    public class AdDelegate implements ItemViewDelegate<CategoryToolBean.CategoryToolAppBean> {

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_ad;
        }

        @Override
        public boolean isForViewType(CategoryToolBean.CategoryToolAppBean item, int position) {
            return item.getType() == 1;
        }

        @Override
        public void convert(ViewHolder holder, CategoryToolBean.CategoryToolAppBean recommendBean, int position) {
            holder.setImageUrl(R.id.iv_ad1,recommendBean.getIconList().get(0));
            holder.setImageUrl(R.id.iv_ad2,recommendBean.getIconList().get(1));
        }
    }

    /** 水平列表adapter */
    public class AppItemAdapter extends CommonAdapter<AppBean> {

        public AppItemAdapter(Context context) {
            super(context, R.layout.item_app);
        }

        @Override
        protected void convert(ViewHolder holder, AppBean appBean, int position) {
            holder.setImageUrl(R.id.iv_app_icon,appBean.getIcon()) ;
            holder.setText(R.id.tv_app_name,appBean.getName()) ;
            holder.setText(R.id.tv_app_size,appBean.getSizeDesc()) ;
        }
    }
}
