package com.szmz.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseListAdapter<T, H> extends BaseAdapter {

    private List<T> items = new ArrayList<>();
    private LayoutInflater inflater = null;
    private int resourceId;

    public BaseListAdapter(Context context, int resourceId) {
        this.resourceId = resourceId;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setItems(List<T> items) {
        if (items != null && items.size() > 0) {
            this.items.addAll(items) ;
        }

    }

    /**
     * 清空数据
     */
    public void clearListData() {
        this.items = new ArrayList<T>();
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        H holer = null;
        if (convertView == null) {

            convertView = inflater.inflate(resourceId, null);
            holer = getHolder(convertView);
            convertView.setTag(holer);
        } else {
            holer = (H) convertView.getTag();
        }
        refreshView(position, items.get(position), holer);
        return convertView;
    }

    protected abstract void refreshView(int postion, T item, H holer);

    protected abstract H getHolder(View converView);
}
