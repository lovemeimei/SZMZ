package com.szmz.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bm.library.PhotoView;
import com.szmz.R;
import com.szmz.SystemConst;
import com.szmz.entity.MyNewPhoto;
import com.szmz.utils.ImageUtil;

import org.xutils.image.ImageOptions;
import org.xutils.x;

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
        PhotoView iv = null;
        ImageView ivDelete = null;
        if (convertView == null) {
            convertView = inflater.inflate(layoutID, null);

            iv = (PhotoView) convertView.findViewById(R.id.img_item);
            iv.disenable();
            ivDelete = (ImageView) convertView.findViewById(R.id.img_delete);
            ViewGroup.LayoutParams params = iv.getLayoutParams();
            params.height = params.width;
            iv.setLayoutParams(params);
            convertView.setTag(convertView);

        } else {
            convertView = (View) convertView.getTag();
            iv = (PhotoView) convertView.findViewById(R.id.img_item);
            iv.disenable();
            ivDelete = (ImageView) convertView.findViewById(R.id.img_delete);
        }

        refreshView(position, convertView, iv, ivDelete);

        return convertView;
    }


    private void refreshView(final int position, final View view, ImageView iv,
                             ImageView iv3) {
        final MyNewPhoto imgPath = (MyNewPhoto) getItem(position);

        iv3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.doDelete(position);
            }
        });

        if (!"TakePhoto".equals(imgPath.getFileUrl())) {
            if (isShowDelete) {
                iv3.setVisibility(View.VISIBLE);
            }
            x.image().bind(iv, imgPath.getFileUrl(), new ImageOptions.Builder()
                    .setSize(ImageUtil.dip2px(context, 250), ImageUtil.dip2px(context, 250))
                    .build());
        } else {
            iv.setImageResource(R.drawable.icon_add_image);
            iv3.setVisibility(View.GONE);

        }
    }

    public interface OnDeleteListener {
        void doDelete(int p);
    }
}
