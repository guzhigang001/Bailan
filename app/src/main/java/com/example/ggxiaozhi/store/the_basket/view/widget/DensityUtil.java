package com.example.ggxiaozhi.store.the_basket.view.widget;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.view.widget
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/2
 * 时间   ： 19:31
 * 功能   ：获取分辨率
 */
public final class DensityUtil {

    private static DisplayMetrics displayMetrics;

    /**
     * getWindowManager().getDefaultDisplay().getMetrics;
     注：构造函数DisplayMetrics 不需要传递任何参数；调用getWindowManager() 之后，
     会取得现有Activity 的Handle ，此时，getDefaultDisplay() 方法将取得的宽高维度存放于DisplayMetrics
     对象中，而取得的宽高维度是以像素为单位(Pixel) ，“像素”所指的是“绝对像素”而非“相对像素”。
     * @param context
     * @param density 传入的像素值
     * @return
     */
    public static int getDensity(Context context, int density) {
        if (displayMetrics == null) {
            displayMetrics = new DisplayMetrics();
            if (context != null)
                ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
        }
        return (int) (density * displayMetrics.density);
    }


}
