package com.szmz.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.szmz.R;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/10/10 0010下午 3:21
 */

public class MyImageViewV extends LinearLayout{

    private TextView tvName;
    private ImageView ivSrc;

    public MyImageViewV(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.my_image_view_v,this);

        tvName = (TextView)findViewById(R.id.tv_name);
        ivSrc = (ImageView) findViewById(R.id.iv_src);

        initAttrs(attrs);
    }

    private void initAttrs(AttributeSet attrs){

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs,R.styleable.MyImageViewV);

        Drawable src = typedArray.getDrawable(R.styleable.MyImageViewV_imgSrcV);
        String title = typedArray.getString(R.styleable.MyImageViewV_tvContentV);

        if (src!=null)
            ivSrc.setImageDrawable(src);
        if (!TextUtils.isEmpty(title))
            tvName.setText(title);

        typedArray.recycle();
    }

    public void setTitle(String title){
        tvName.setText(title);
    }

    public void setSrc(int srcID){
        ivSrc.setImageResource(srcID);
    }
}
