package com.example.ggxiaozhi.store.the_basket.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ggxiaozhi.store.the_basket.R;


/**
 * 介绍 评论 推荐 模块自定义布局
 */

public class SubTabNavigator extends LinearLayout implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private final static int TAG_LEFT_VIEW = 0;
    private final static int TAG_NONE_VIEW = 1;
    private final static int TAG_RIGHT_VIEW = 2;

    private float mTextSize = -1.0F;
    private int tabUnSelectTextColor = 0;
    private int tabSelectTextColor = 0;

    private Drawable mLeftUnSelectDrawable;
    private Drawable mRightUnSelectDrawable;
    private Drawable mSimpleUnSelectDrawable;
    private Drawable mLeftSelectDrawable;
    private Drawable mRightSelectDrawable;
    private Drawable mSimpleSelectDrawable;
    private String mLeftText;
    private String mSimpleText;
    private String mRightText;

    private TextView mLeftTextView;
    private TextView mSimpleTextView;
    private TextView mRightTextView;
    private ViewPager mViewPager;

    private ViewPager.OnPageChangeListener mPageChangeListener;


    public SubTabNavigator(Context context) {
        this(context, null);
    }

    public SubTabNavigator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SubTabNavigator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
        initView(context);
    }

    /*public SubTabNavigator(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs);
        initView(context);
    }*/

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = null;
        if (attrs != null)
            typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.sub_tab);

        mTextSize = typedArray.getDimension(R.styleable.sub_tab_textSize, -1.0F);
        tabSelectTextColor = typedArray.getColor(R.styleable.sub_tab_textSelectColor, context.getResources().getColor(R.color.sub_tab_unselected));
        tabUnSelectTextColor = typedArray.getColor(R.styleable.sub_tab_textUnSelectColor, context.getResources().getColor(R.color.sub_tab_unselected));
        mLeftUnSelectDrawable = typedArray.getDrawable(R.styleable.sub_tab_round_left_unselected);
        mRightUnSelectDrawable = typedArray.getDrawable(R.styleable.sub_tab_round_right_unselected);
        mSimpleUnSelectDrawable = typedArray.getDrawable(R.styleable.sub_tab_round_none_unselected);
        mLeftSelectDrawable = typedArray.getDrawable(R.styleable.sub_tab_round_left_selected);
        mRightSelectDrawable = typedArray.getDrawable(R.styleable.sub_tab_round_right_selected);
        mSimpleSelectDrawable = typedArray.getDrawable(R.styleable.sub_tab_round_none_selected);
        mLeftText = typedArray.getString(R.styleable.sub_tab_round_left_text);
        mSimpleText = typedArray.getString(R.styleable.sub_tab_round_none_text);
        mRightText = typedArray.getString(R.styleable.sub_tab_round_right_text);

        typedArray.recycle();
    }


    private void initView(Context context) {

        setOrientation(LinearLayout.HORIZONTAL);
        addTextView(context, TAG_LEFT_VIEW);
        addTextView(context, TAG_NONE_VIEW);
        addTextView(context, TAG_RIGHT_VIEW);
    }

    private void addTextView(Context context, int viewTag) {
        TextView tv = (TextView) View.inflate(context, R.layout.custom_textview, null);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        layoutParams.weight = 1.0F;

        tv.setLayoutParams(layoutParams);
        tv.setOnClickListener(this);
        tv.setGravity(Gravity.CENTER);
        if (mTextSize > 0f)
            tv.setTextSize(0, mTextSize);

        tv.setOnClickListener(this);
        tv.setTag(viewTag);
        if (viewTag == TAG_LEFT_VIEW) {
            tv.setText(mLeftText);
            mLeftTextView = tv;
        } else if (viewTag == TAG_NONE_VIEW) {
            tv.setText(mSimpleText);
            mSimpleTextView = tv;
        } else if (viewTag == TAG_RIGHT_VIEW) {
            tv.setText(mRightText);
            mRightTextView = tv;
        }
        addView(tv);
    }

    /**
     * 初始化布局
     */
    private void drawBackground() {

        getChildAt(0).setBackgroundDrawable(mLeftUnSelectDrawable);
        getChildAt(1).setBackgroundDrawable(mSimpleUnSelectDrawable);
        getChildAt(2).setBackgroundDrawable(mRightUnSelectDrawable);

        for (int i = 0; i < getChildCount(); i++) {
            TextView childView = (TextView) getChildAt(i);
            childView.setTextColor(tabUnSelectTextColor);
        }
    }


    @Override
    public void onClick(View view) {
        setCurrentItemSelect(view);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground();
        setCurrentItemSelect(getChildAt(0));

    }

    public void setCurrentItemSelect(View view) {

        drawBackground();
        TextView tv = (TextView) view;
        tv.setTextColor(tabSelectTextColor);
        int tag = (int) tv.getTag();
        switch (tag) {
            case TAG_LEFT_VIEW:
                tv.setBackgroundDrawable(mLeftSelectDrawable);
                break;
            case TAG_NONE_VIEW:
                tv.setBackgroundDrawable(mSimpleSelectDrawable);
                break;
            case TAG_RIGHT_VIEW:
                tv.setBackgroundDrawable(mRightSelectDrawable);
                break;
        }

        switchViewPager(tag);

    }

    private void setCurrentIntemSelect(int position) {
        drawBackground();
        TextView tv = (TextView) getChildAt(position);
        tv.setTextColor(tabSelectTextColor);
        int tag = (int) tv.getTag();
        switch (tag) {
            case TAG_LEFT_VIEW:
                tv.setBackgroundDrawable(mLeftSelectDrawable);
                break;
            case TAG_NONE_VIEW:
                tv.setBackgroundDrawable(mSimpleSelectDrawable);
                break;
            case TAG_RIGHT_VIEW:
                tv.setBackgroundDrawable(mRightSelectDrawable);
                break;
        }
    }

    private void switchViewPager(int tag) {
        if (mViewPager != null)
            mViewPager.setCurrentItem(tag);
    }


    public void setLeftText(String leftText) {
        this.mLeftText = leftText;
        mLeftTextView.setText(leftText);
    }

    public void setNoneText(String noneText) {
        this.mSimpleText = noneText;
        mSimpleTextView.setText(noneText);
    }

    public void setRightText(String rightText) {
        this.mRightText = rightText;
        mRightTextView.setText(rightText);
    }

    public void setViewPager(ViewPager viewPager) {
        this.mViewPager = viewPager;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setCurrentIntemSelect(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
