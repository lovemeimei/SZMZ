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

public class MyLayoutView extends LinearLayout {
    private TextView contentTv;
    private TextView titleTv;

    public MyLayoutView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.my_layout_view, this);
        contentTv = (TextView) findViewById(R.id.content);
        titleTv = (TextView) findViewById(R.id.title);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MyLayoutView);
        try {
            String titleStr = typedArray.getString(R.styleable.MyLayoutView_titleText);
            String contentStr = typedArray.getString(R.styleable.MyLayoutView_contentText);
            contentTv.setText(contentStr);
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

}
