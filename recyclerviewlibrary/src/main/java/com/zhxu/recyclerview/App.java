package com.zhxu.recyclerview;

import android.app.Application;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class App extends Application {

    private static App context ;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this ;
    }

    public static App getContext(){
        return context;
    }
}
