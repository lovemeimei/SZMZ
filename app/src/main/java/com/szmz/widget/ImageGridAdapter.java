package com.szmz.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.szmz.R;
import com.szmz.SystemConst;
import com.szmz.entity.MyNewPhoto;

import java.util.ArrayList;
import java.util.List;

/**
 */

public class ImageGridAdapter extends BaseAdapter {
    private List<MyNewPhoto> imgPaths = new ArrayList<MyNewPhoto>();
    private LayoutInflater inflater = null;
    private int layoutID;
    private int maxNumber;
    private boolean isShowDelete = false;
    private OnDeleteListener listener;
    private Context context;

    public List<MyNewPhoto> getImgPaths() {
        return imgPaths;
    }

    public void setImgPaths(List<MyNewPhoto> imgPaths) {
        if (imgPaths == null) {
            this.imgPaths = new ArrayList<MyNewPhoto>();
        } else {
            this.imgPaths = imgPaths;
        }
    }

    /**
     *
     */
    public ImageGridAdapter(Context context, int layoutID, int maxNumber,
                            boolean isShowDelete, OnDeleteListener listener) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.layoutID = layoutID;
        if (maxNumber < SystemConst.MaxPhotoNumber) {
            this.maxNumber = SystemConst.MaxPhotoNumber;
        } else {
            this.maxNumber = maxNumber;
        }
        this.isShowDelete = isShowDelete;
        this.listener = listener;

    }

    public ImageGridAdapter(Context context, int layoutID, int maxNumber) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.layoutID = layoutID;
        if (maxNumber < SystemConst.MaxPhotoNumber) {
            this.maxNumber = SystemConst.MaxPhotoNumber;
        } else {
            this.maxNumber = maxNumber;
        }
        this.isShowDelete = false;

    }

    @Override
    public int getCount() {

        return this.imgPaths.size() > maxNumber ? maxNumber : this.imgPaths
                .size();
    }

    @Override
    public Object getItem(int index) {

        return this.imgPaths.get(index);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        ImageView iv = null;
        ImageView iv2 = null;
        ImageView ivDelete = null;
        if (convertView == null) {
            convertView = inflater.inflate(layoutID, null);

            iv = (ImageView) convertView.findViewById(R.id.img_item);
            ivDelete = (ImageView) convertView.findViewById(R.id.img_delete);
            ViewGroup.LayoutParams params = iv.getLayoutParams();
            params.height = params.width;
            iv.setLayoutParams(params);
            convertView.setTag(convertView);

        } else {
            convertView = (View) convertView.getTag();
            iv = (ImageView) convertView.findViewById(R.id.img_item);
            ivDelete = (ImageView) convertView.findViewById(R.id.img_delete);
        }

        refreshView(position, convertView, iv, iv2, ivDelete);

        return convertView;
    }


    private void refreshView(final int position, final View view, ImageView iv,
                             ImageView iv2, ImageView iv3) {
        final MyNewPhoto imgPath = (MyNewPhoto) getItem(position);

        iv.setTag(imgPath);
        iv3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.doDelete(position);
            }
        });

        if (!"TakePhoto".equals(imgPath.getFileUrl())) {
            iv2.setVisibility(View.GONE);
            if (isShowDelete) {
                if (imgPath.getFileUrl().contains("http")) {
                    iv3.setVisibility(View.VISIBLE);
                } else {
                    iv3.setVisibility(View.GONE);
                }
            }
            Glide.with(context).load(imgPath.getFileUrl()).into(iv);
        } else {
            iv.setImageResource(R.drawable.icon_add_image);
            iv2.setVisibility(View.VISIBLE);
            iv3.setVisibility(View.GONE);

        }
    }

    interface OnDeleteListener {
        void doDelete(int p);
    }
}
