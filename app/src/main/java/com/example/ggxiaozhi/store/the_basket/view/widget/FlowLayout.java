package com.example.ggxiaozhi.store.the_basket.view.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.ggxiaozhi.store.the_basket.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.view.widget
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/2
 * 时间   ： 19:30
 * 功能   ：
 */

public class FlowLayout extends ViewGroup{

    public static final int DEFAULT_SPACING = 20;
    /** 横向间隔 */
    private int mHorizontalSpacing = DEFAULT_SPACING;
    /** 纵向间隔 */
    private int mVerticalSpacing = DEFAULT_SPACING;
    /** 当前行已用的宽度,由子View宽度加上横向的间隔 */
    private int mUsedWidth = 0;
    /** 最大的行数 */
    int mMaxLinesCount = Integer.MAX_VALUE;
    private final List<Line> mLines = new ArrayList<Line>();
    /** 行 */
    private Line mLine = null;

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setHorizontalSpacing(int spacing) {
        if (mHorizontalSpacing != spacing) {
            mHorizontalSpacing = spacing;
            requestLayoutInner();
        }
    }

    public void setVerticalSpacing(int spacing) {
        if (mVerticalSpacing != spacing) {
            mVerticalSpacing = spacing;
            requestLayoutInner();
        }
    }

    public void setMaxLines(int count) {
        if (mMaxLinesCount != count) {
            mMaxLinesCount = count;
            requestLayoutInner();
        }
    }

    private void requestLayoutInner() {
        UIUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                requestLayout();// 重新分配布局
            }
        });
    }
    /** 是否需要布局,只用于第一次 */
    private boolean mNeedLayout = true;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (mNeedLayout || changed) {
            mNeedLayout = false;
            int left = getPaddingLeft();// 最初左面的位置
            int top = getPaddingTop();// 最初上面的位置
            int linesCount = mLines.size();
            for (int i = 0; i < linesCount; i++) {
                Line oneLine = mLines.get(i);
                // TODO 每一行分配
                oneLine.layoutView(left, top);
                top += oneLine.mHeight + mVerticalSpacing;// 为下一行的top赋值
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int sizeWidth = View.MeasureSpec.getSize(widthMeasureSpec)
                - getPaddingRight() - getPaddingLeft();
        int sizeHeight = View.MeasureSpec.getSize(heightMeasureSpec)
                - getPaddingTop() - getPaddingBottom();

        int modeWidth = View.MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = View.MeasureSpec.getMode(heightMeasureSpec);
        restoreLine();// 还原所有数据
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);// 获取每个孩子
            if (child.getVisibility() == GONE) {
                continue;
            }
            int childWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(sizeWidth,
                    modeWidth == View.MeasureSpec.EXACTLY ? View.MeasureSpec.AT_MOST
                            : modeWidth);
            int childHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(
                    sizeHeight,
                    modeHeight == View.MeasureSpec.EXACTLY ? View.MeasureSpec.AT_MOST
                            : modeHeight);
            // 测量child 孩子不能为精确大小
            child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
            // 创建新行
            if (mLine == null) {
                mLine = new Line();
            }
            int childWidth = child.getMeasuredWidth();
            mUsedWidth += childWidth;// 增加使用的宽度
            if (mUsedWidth <= sizeWidth) {// 使用宽度小于总宽度,该child属于这一行
                mLine.addView(child);
                mUsedWidth += mHorizontalSpacing;// 加上间隔
                if (mUsedWidth >= sizeWidth) {// 加上间隔后大于等于总宽度,需要换行
                    // 换行
                    if (!newLine()) {
                        break;
                    }
                }
            } else { // 使用宽度大于总宽度,需要换行
                if (mLine.getViewCount() == 0) { // 最少一个孩子
                    mLine.addView(child);
                    if (!newLine()) {// 换行
                        break;
                    }
                } else { // 有数据了直接换行
                    if (!newLine()) {
                        break;
                    }
                    // 把view添加到新的一行上
                    mLine.addView(child);
                    mUsedWidth += childWidth + mHorizontalSpacing;
                }
            }
        }
        // 把最后一行记录到集合中
        if (mLine != null && mLine.getViewCount() > 0
                && !mLines.contains(mLine)) {
            mLines.add(mLine);
        }
        // 宽度就是测量出来的宽,高度应该是所有行的高
        int totalWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        int totalHeight = 0;
        int linesCount = mLines.size();
        for (int i = 0; i < linesCount; i++) {
            // 所有行的高
            totalHeight += mLines.get(i).mHeight;
        }
        totalHeight += mVerticalSpacing * (linesCount - 1);// 加上所有间距
        totalHeight += getPaddingTop() + getPaddingBottom();// 加上Padding
        // 设置布局的宽高，宽度直接采用父view传递过来的最大宽度，而不用考虑子view是否填满宽度，因为该布局的特性就是填满一行后，再换行
        // 高度根据设置的模式来决定采用所有子View的高度之和还是采用父view传递过来的高度
        setMeasuredDimension(totalWidth,
                resolveSize(totalHeight, heightMeasureSpec));
    }

    /** 还原所有数据 */
    private void restoreLine() {
        mLines.clear();
        mLine = new Line();
        mUsedWidth = 0;
    }

    /** 创建新的一行 */
    private boolean newLine() {
        // 添加之前的
        mLines.add(mLine);

        // 创建新的
        if (mLines.size() < mMaxLinesCount) {
            mLine = new Line();
            mUsedWidth = 0;
            return true;
        }
        return false;
    }

    /***
     * 代表着一行 ,封装了一行所占的高度,该行子View的集合,所有宽的总和
     *
     * @author itcast
     *
     */
    class Line {
        int mWidth = 0;// 所有宽度的总和
        int mHeight = 0; // 所有高度的总和
        List<View> views = new ArrayList<View>();// 所有的子孩子

        /**
         * 添加一个孩子
         *
         * @param view
         */
        public void addView(View view) {
            views.add(view);
            mWidth += view.getMeasuredWidth();
            int childHeight = view.getMeasuredHeight();
            // 高度等于一行中最高的View
            mHeight = mHeight < childHeight ? childHeight : mHeight;
        }

        /** 分配布局 */
        public void layoutView(int l, int t) {
            int left=l;
            int top=t;
            int count =getViewCount();
            // 总宽度
            int layoutWidth=getMeasuredWidth()-getPaddingLeft()-getPaddingRight();
            // 剩余宽度,是除了View和间隙的剩余空间
            int surplusWidth=layoutWidth-mWidth-mHorizontalSpacing*(count-1);
            if(surplusWidth>=0){
                //采用float类型数据计算后四舍五入能减少int类型计算带来的误差
                int  splitSpacing=(int) (surplusWidth/count*1.0+0.5);
                for(int i=0;i<count;i++){
                    View view=views.get(i);
                    int childWidth=view.getMeasuredWidth();
                    int childHeight=view.getMeasuredHeight();
                    // 计算出每个View的顶点 ,是由最高的View和该view高度差值除以2
                    int topOffset=(int) ((mHeight-childHeight)/2.0+0.5);
                    if(topOffset<0){
                        topOffset=0;
                    }
                    // 把剩余空间平均到每个View上
                    childWidth=childWidth+splitSpacing;
                    // 修改View的宽度
                    view.getLayoutParams().width=childWidth;
                    if(splitSpacing>0){
                        // view宽度改变需要重新测量
                        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(childWidth, View.MeasureSpec.EXACTLY);
                        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(childHeight, View.MeasureSpec.EXACTLY);
                        view.measure(widthMeasureSpec, heightMeasureSpec);
                    }
                    //System.out.println(topOffset);
                    //布局View
                    view.layout(left, top+topOffset, left+childWidth, top+childHeight+topOffset);
                    left+=childWidth+mHorizontalSpacing;//为下一个View的left赋值
                }
            }else{
                if(count==1){
                    View view=views.get(0);
                    view.layout(left, top, view.getMeasuredWidth()+left, top+view.getMeasuredHeight());
                }else{
                    // 走不到
                }
            }
        }

        public int getViewCount() {
            return views.size();
        }
    }
}
