package com.szmz.utils;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页列表适配器基类
 *
 * @param <T>
 */

public abstract class MyListPageAdapter<T> extends MyBaseListAdapter<T> {


    private static final int THRESHOLD = 1000;//页面显示最大数据

    /**
     * 构造函数
     *
     * @param context
     * @param resouceId
     */
    public MyListPageAdapter(Context context, int resouceId) {
        super(context, resouceId);
    }

    @Override
    public void setListData(List<T> items) {
        if (items == null || items.size() == 0)
            return;
        this.items.addAll(items);

        int size = this.items.size();

        if (size > THRESHOLD) {
            int start = size - THRESHOLD;
            int end = size;
            this.items = this.items.subList(start, end);
        }

    }

    public List<T> getListData() {
        return this.items;
    }

    /**
     * 清空数据
     */
    public void clearListData() {
        this.items = new ArrayList<T>();
    }

}
