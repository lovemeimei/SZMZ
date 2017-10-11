package com.szmz.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.szmz.R;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/10/11 0011下午 3:31
 */

public class MyImageLayoutView extends RelativeLayout{

    private TextView tvTitle;
    private TextView tvContent;
    private ImageView ivIcon;

    public MyImageLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.my_layout_imageview,this);

        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvContent = (TextView) findViewById(R.id.tv_content);
        ivIcon = (ImageView) findViewById(R.id.iv_icon);

        initAttrs(attrs);
    }

    private void initAttrs(AttributeSet attrs){

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs,R.styleable.MyImageLayoutView);

        String title = typedArray.getString(R.styleable.MyImageLayoutView_layout_title);
        String content = typedArray.getString(R.styleable.MyImageLayoutView_layout_content);
        Drawable drawable = typedArray.getDrawable(R.styleable.MyImageLayoutView_layout_cionSrc);

        if (drawable!=null)
            ivIcon.setImageDrawable(drawable);
        if (!TextUtils.isEmpty(title)){
            tvTitle.setText(title);
        }
        if (!TextUtils.isEmpty(content)){
            tvContent.setText(content);
        }

        typedArray.recycle();
    }
}
