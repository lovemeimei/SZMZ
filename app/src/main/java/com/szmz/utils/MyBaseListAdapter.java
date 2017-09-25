package com.szmz.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表适配器基类
 *
 * @param <T> 实体
 */

public abstract class MyBaseListAdapter<T> extends BaseAdapter {

    private int resourceId;
    protected LayoutInflater mInflater;
    protected List<T> items = new ArrayList<>();


    public MyBaseListAdapter(Context context, int resouceId) {
        this.mInflater = LayoutInflater.from(context);
        this.resourceId = resouceId;
    }

    /**
     * 获取数据
     *
     * @return
     */
    public List<T> getListData() {
        return this.items;
    }

    /**
     * 设置数据源
     *
     * @return
     */
    public void setListData(List<T> items) {
        if (items == null) {
            this.items = new ArrayList<T>();
        } else {
            this.items = items;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = mInflater.inflate(resourceId, null);
        }

        T item = (T) getItem(position);

        refreshView(position, item, convertView);

        return convertView;
    }

    /**
     * 数据绑定view
     *
     * @param position     位置
     * @param item         数据实体
     * @param listItemView 列表字View
     */
    protected abstract void refreshView(int position, T item, View listItemView);

    /**
     * 获取数据长度
     *
     * @return
     */
    @Override
    public int getCount() {
        return items.size();
    }

    /**
     * 根据位置获取列表中的子数据
     *
     * @param position 位置
     * @return 数据实体
     */
    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
