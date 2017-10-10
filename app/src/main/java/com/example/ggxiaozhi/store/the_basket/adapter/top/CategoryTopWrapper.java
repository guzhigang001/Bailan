package com.example.ggxiaozhi.store.the_basket.adapter.top;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ggxiaozhi.store.the_basket.R;
import com.example.ggxiaozhi.store.the_basket.base.StoreApplication;
import com.example.ggxiaozhi.store.the_basket.bean.CategoryBean;
import com.example.ggxiaozhi.store.the_basket.utils.UIUtils;
import com.zhxu.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.List;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.adapter.top
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/3
 * 时间   ： 20:10
 * 功能   ：分类页头部适配器
 */

public class CategoryTopWrapper extends HeaderAndFooterWrapper {
    private List<CategoryBean.CategoryTopBean> mList;
    private GridView gridView;
    private Context mContext;
    private OnItemClickListener mListener;

    public CategoryTopWrapper(RecyclerView.Adapter adapter, Context context) {
        super(adapter);
        this.mContext = context;
        View view = UIUtils.inflate(R.layout.header_top);
        gridView = (GridView) view.findViewById(R.id.gv_title_grid);
        addHeaderView(view);
    }

    public void addData(List<CategoryBean.CategoryTopBean> mList) {
        this.mList = mList;
        GridViewAdapter adapter1 = new GridViewAdapter(mContext, mList);
        gridView.setNumColumns(mList.size());
        gridView.setAdapter(adapter1);

    }

    class GridViewAdapter extends BaseAdapter {
        private Context mContext;
        private List<CategoryBean.CategoryTopBean> mBeanList;

        public GridViewAdapter(Context context, List<CategoryBean.CategoryTopBean> mList) {
            this.mContext = context;
            this.mBeanList = mList;
        }

        @Override
        public int getCount() {
            return mBeanList.size();
        }

        @Override
        public Object getItem(int position) {
            return mBeanList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = UIUtils.inflate(R.layout.appdetail_subcat_title);
                holder = new ViewHolder();
                holder.mImageView = (ImageView) convertView.findViewById(R.id.appicon);
                holder.mTextView = (TextView) convertView.findViewById(R.id.ItemTitle);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Glide.with(mContext).load(mBeanList.get(position).getIconUrl()).into(holder.mImageView);
            holder.mTextView.setText(mBeanList.get(position).getName());
            holder.mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.OnItemClickListener(position);
                }
            });
            return convertView;
        }
    }

    public void setOnItemClick(OnItemClickListener listener) {
        this.mListener = listener;
    }

    class ViewHolder {
        ImageView mImageView;
        TextView mTextView;
    }

    public interface OnItemClickListener {
        void OnItemClickListener(int position);
    }
}
