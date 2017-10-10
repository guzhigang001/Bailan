package com.example.ggxiaozhi.store.the_basket.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.blankj.utilcode.utils.LogUtils;

import java.util.Stack;
/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.base
 * 作者名 ： 志先生_
 * 日期   ： 2017/8/22
 * 时间   ： 12:11
 * 功能   ： 管理Activity
 */


public class AppActivityManager {
    private static Stack<Activity> mStack;
    private static AppActivityManager mAppCompatActivity;

    private AppActivityManager(){

    }

    /**
     * 单一实例
     * @return
     */
    public static AppActivityManager getInstance(){
        if (mAppCompatActivity==null){
            mAppCompatActivity=new AppActivityManager();
        }
        return mAppCompatActivity;
    }

    /**
     * 添加Activity到堆栈
     * @param activity
     */
    public void addActivity(Activity activity){
        if (mStack==null){
            mStack=new Stack<>();
        }
        mStack.add(activity);
        Log.i("------------------>", mStack.size()+"");
    }

    /**
     * 移除Activity到堆外
     */
    public void removeActivity(Activity activity){
        mStack.remove(activity);
    }

    /**
     * 获取栈顶Activity
     */
    public Activity getTopActivity(){
        return  mStack.lastElement();
    }

    /**
     * 结束栈顶Activity
     */
    public void killTopActivity() {
        Activity activity = mStack.lastElement();
        killActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    private void killActivity(Activity activity) {
        if (activity != null) {
            mStack.remove(activity);
        }
        activity.finish();
    }

    /**
     * 结束指定类名的Activity
     */
    public void killActivity(Class<?> cls) {
        for (Activity activity : mStack) {
            if (activity.getClass().equals(cls)) {
                killActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    private void killAllActivity() {
        int size = mStack.size();
        for (int i = 0; i < size; i++) {
            if (null != mStack.get(i)) {
                mStack.get(i).finish();
            }
        }
        mStack.clear();
    }

    /**
     * 退出应用程序
     */
    @SuppressWarnings("deprecation")
    public void AppExit(Context context) {
        try {
            killAllActivity();
            android.app.ActivityManager activityMgr = (android.app.ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            Log.e("AppActivityManager",""+e);
        }
    }
}
