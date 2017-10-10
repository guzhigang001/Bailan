package com.example.ggxiaozhi.store.the_basket.mvp.view.fragment;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ggxiaozhi.store.the_basket.R;
import com.example.ggxiaozhi.store.the_basket.base.BaseFragment;
import com.example.ggxiaozhi.store.the_basket.bean.MyGvBean;
import com.example.ggxiaozhi.store.the_basket.utils.UIUtils;
import com.example.ggxiaozhi.store.the_basket.view.LoadingPager;
import com.example.ggxiaozhi.store.the_basket.view.widget.MyEnterLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyFragment extends BaseFragment {

    @BindView(R.id.gv_title_grid)
    GridView gv_title_grid;
    @BindView(R.id.book_game_layout)
    MyEnterLayout book_game_layout;
    @BindView(R.id.buy_layout)
    MyEnterLayout buy_layout;
    @BindView(R.id.huaban_layout)
    MyEnterLayout huaban_layout;
    @BindView(R.id.setting_computer)
    MyEnterLayout setting_computer;
    @BindView(R.id.faq_layout)
    MyEnterLayout faq_layout;
    @BindView(R.id.check_update_layout)
    MyEnterLayout check_update_layout;
    @BindView(R.id.about_layout)
    MyEnterLayout about_layout;

    private List<MyGvBean> gvBeanList = new ArrayList<>();

    @Override
    protected void load() {
        setState(LoadingPager.LoadResult.success);
    }

    @Override
    protected View cretaeSuccessView() {
        View view = UIUtils.inflate(R.layout.fragment_my);
        ButterKnife.bind(this, view);

        gvBeanList.add(new MyGvBean(UIUtils.getString(R.string.market_prize), R.drawable.icon_market_lucky_draw));
        gvBeanList.add(new MyGvBean(UIUtils.getString(R.string.market_gift), R.drawable.ic_mine_package_normal));
        gvBeanList.add(new MyGvBean(UIUtils.getString(R.string.appzone_comments), R.drawable.icon_market_comment));
        gvBeanList.add(new MyGvBean(UIUtils.getString(R.string.market_mine_message), R.drawable.icon_market_message));

        MySubAdapter adapter = new MySubAdapter(getContext(), gvBeanList);
        gv_title_grid.setNumColumns(gvBeanList.size());
        gv_title_grid.setAdapter(adapter);

        book_game_layout.setTitle(UIUtils.getString(R.string.reserve_warpup_game_str));
        buy_layout.setTitle(UIUtils.getString(R.string.purchase_title));
        huaban_layout.setTitle(UIUtils.getString(R.string.mine_point_area));
        setting_computer.setTitle(UIUtils.getString(R.string.action_settings));
        faq_layout.setTitle(UIUtils.getString(R.string.menu_feedback));
        check_update_layout.setTitle(UIUtils.getString(R.string.settings_check_version_update));
        about_layout.setTitle(UIUtils.getString(R.string.about));
        return view;
    }

    private class MySubAdapter extends BaseAdapter {
        private Context mContext;
        private List<MyGvBean> mBeen;

        public MySubAdapter(Context context, List<MyGvBean> list) {
            this.mContext = context;
            this.mBeen = list;
        }

        @Override
        public int getCount() {
            return mBeen.size();
        }

        @Override
        public Object getItem(int position) {
            return mBeen.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MyGvBean bean = mBeen.get(position);
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(mContext, R.layout.appdetail_subcat_title, null);
                holder.mImageView = (ImageView) convertView.findViewById(R.id.appicon);
                holder.mTextView = (TextView) convertView.findViewById(R.id.ItemTitle);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.mTextView.setText(bean.getName());
            Glide.with(mContext).load(bean.getIconId()).into(holder.mImageView);

            return convertView;
        }
    }

    private class ViewHolder {
        private ImageView mImageView;
        private TextView mTextView;
    }
}
