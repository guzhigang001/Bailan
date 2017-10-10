package com.example.ggxiaozhi.store.the_basket.view.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.ggxiaozhi.store.the_basket.R;

/**
 * 带动画的悬浮搜索框
 */

public class ViewUpSearch extends LinearLayout {


    private View search_icon;
    private View autoText;
    private float scale;
    private View search_circle;

    public ViewUpSearch(Context context) {
        super(context);
        initView(context);
    }

    public ViewUpSearch(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.search, this, true);
        search_icon = findViewById(R.id.search_icon);
        autoText = findViewById(R.id.autoText);
        search_circle = findViewById(R.id.search_circle);
    }


    public void updateShow(boolean isExpand) {
        double serchWid = autoText.getWidth() / 1.0;
        double wid = search_icon.getWidth() / 1.0;
        double fenshu = wid / serchWid;
        scale = (float) fenshu;

        if (isExpand) {
            expandSearch();
        } else {
            closeSearch();
        }
    }

    private void expandSearch() {

        search_circle.setVisibility(View.VISIBLE);
        search_icon.setVisibility(View.VISIBLE);
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(search_circle, "alpha", 1f, 0f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(autoText, "alpha", 0f, 1f);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(autoText, "scaleX", scale, 1f);
        //setPivotX设置缩放的起始X轴位置
        autoText.setPivotX(autoText.getWidth());
        AnimatorSet animSet2 = new AnimatorSet();
        animSet2.play(anim1).with(anim2).with(anim3);
        animSet2.setDuration(300);
        animSet2.start();

    }

    private void closeSearch() {
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(autoText, "scaleX", 1f, scale);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(search_circle, "alpha", 0f, 1f);
        search_circle.setVisibility(View.VISIBLE);
        search_icon.setVisibility(View.INVISIBLE);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(autoText, "alpha", 1f, 0f);
        //setPivotX设置缩放的起始X轴位置为右侧开始Y轴为中间开始
        autoText.setPivotX(autoText.getWidth());
        autoText.setPivotY(autoText.getHeight() / 2);
        AnimatorSet animSet1 = new AnimatorSet();
        animSet1.play(anim2).with(anim3).with(anim4);
        animSet1.setDuration(300);
        animSet1.start();
    }

}