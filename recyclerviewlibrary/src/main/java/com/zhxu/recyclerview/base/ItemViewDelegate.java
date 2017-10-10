package com.zhxu.recyclerview.base;

/**
 * Created by xzhang on 2017/5/21.
 */

public interface ItemViewDelegate<T> {

    int getItemViewLayoutId() ;

    boolean isForViewType(T item, int position) ;

    void convert(ViewHolder holder, T t, int position) ;
}
