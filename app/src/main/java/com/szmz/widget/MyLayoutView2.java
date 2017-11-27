package com.szmz.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.szmz.R;

/**
 * Created by bz on 2017/9/8.
 */

public class MyLayoutView2 extends LinearLayout {
    private TextView contentTv;
    private TextView contentTv2;
    private TextView titleTv;

    public MyLayoutView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.my_layout_view2, this);
        contentTv = (TextView) findViewById(R.id.content);
        contentTv2 = (TextView) findViewById(R.id.content2);
        titleTv = (TextView) findViewById(R.id.title);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MyLayoutView2);
        try {
            String titleStr = typedArray.getString(R.styleable.MyLayoutView2_title);
            String contentStr = typedArray.getString(R.styleable.MyLayoutView2_content);
            String contentStr2 = typedArray.getString(R.styleable.MyLayoutView2_content2);
            contentTv.setText(contentStr);
            contentTv2.setText(contentStr2);
            titleTv.setText(titleStr);
        } catch (Exception e) {

        } finally {
            typedArray.recycle();
        }

    }

    public void doSetTitle(String str) {
        titleTv.setText(str);
    }

    public void doSetContent(String str) {
        contentTv.setText(str);
    }
    public void doSetContent2(String str) {
        contentTv2.setText(str);
    }
}
