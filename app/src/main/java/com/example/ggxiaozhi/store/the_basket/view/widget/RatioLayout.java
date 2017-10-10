package com.example.ggxiaozhi.store.the_basket.view.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.example.ggxiaozhi.store.the_basket.R;

/**
 * 固定比例压塑图片布局
 */

public class RatioLayout extends FrameLayout {
    // 宽高比例
    private float ratio = 0.0f;

    public RatioLayout(Context context) {
        super(context);
    }

    public RatioLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RatioLayout);
        ratio = a.getFloat(R.styleable.RatioLayout_ratio, 0.0f);
        a.recycle();
    }

    public RatioLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    // 父类会测量每个子类
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft()
                - getPaddingRight();
        int height = MeasureSpec.getSize(heightMeasureSpec) - getPaddingTop()
                - getPaddingBottom();
        if (widthMode == MeasureSpec.EXACTLY
                && heightMode != MeasureSpec.EXACTLY && ratio != 0.0f) {
            height = (int) (width / ratio + 0.5f);// +0.5f方便四舍五入
            heightMeasureSpec = MeasureSpec
                    .makeMeasureSpec(height + getPaddingBottom()
                            + getPaddingTop(), MeasureSpec.EXACTLY);

        } else if (widthMode != MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY && ratio != 0.0f) {
            width = (int) (height * ratio + 0.5f);
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(width + getPaddingLeft() + getPaddingRight(),
                    MeasureSpec.EXACTLY);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
