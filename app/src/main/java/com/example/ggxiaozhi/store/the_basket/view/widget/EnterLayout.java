package com.example.ggxiaozhi.store.the_basket.view.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ggxiaozhi.store.the_basket.R;

/**
 * 管理页面条目布局
 */
public class EnterLayout extends LinearLayout {
    private TextView setItemTitle;
    private TextView setItemContent;
    private ImageView devider_line;
    private RelativeLayout item_layout;

    public EnterLayout(Context paramContext) {
        super(paramContext);
        a(paramContext);
    }

    public EnterLayout(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        a(paramContext);
    }

    private void a(Context context) {
        setOrientation(LinearLayout.VERTICAL);
        View view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.settings_enter_normal_item, this);
        item_layout = ((RelativeLayout) view.findViewById(R.id.item_layout));
        setPadding(DensityUtil.getDensity(context, 16), DensityUtil.getDensity(context, 0));
        setItemTitle = ((TextView) item_layout.findViewById(R.id.setItemTitle));
        setItemContent = ((TextView) item_layout.findViewById(R.id.setItemContent));
        devider_line = ((ImageView) view.findViewById(R.id.devider_line));
    }

    public void a() {
        devider_line.setVisibility(View.VISIBLE);
    }

    public void setPadding(int paramInt1, int paramInt2) {
        item_layout.setPadding(paramInt1, 0, paramInt2, 0);
    }

    public void setMemo(String memo) {
        if (!TextUtils.isEmpty(memo)) ;
            setItemContent.setText(memo);

    }

    public void setTitle(String title) {
        if(!TextUtils.isEmpty(title))
            setItemTitle.setText(title);
    }
}