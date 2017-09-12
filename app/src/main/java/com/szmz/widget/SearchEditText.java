package com.szmz.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;

import com.szmz.R;

/**
 *
 */
public class SearchEditText extends AppCompatEditText implements OnFocusChangeListener{

    float searchSize = 0;
    float textSize = 0;
    int textColor = 0xFF000000;
    Drawable mDrawable;
    Paint paint;
    Context context;


    public SearchEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        InitResource(context, attrs);
        InitPaint();
    }

    private void InitResource(Context context, AttributeSet attrs) {
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.searchedit);
        float density = context.getResources().getDisplayMetrics().density;
        searchSize = mTypedArray.getDimension(R.styleable.searchedit_imagewidth, 18 * density + 0.5F);
        textColor = mTypedArray.getColor(R.styleable.searchedit_textColor, 0xFF848484);
        textSize = mTypedArray.getDimension(R.styleable.searchedit_textSize, 14 * density + 0.5F);
        mTypedArray.recycle();
    }

    private void InitPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(textColor);
        paint.setTextSize(textSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        DrawSearchIcon(canvas);
    }
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mDrawable == null) {
            try {
                mDrawable = ContextCompat.getDrawable(context,R.drawable.slt_icon_search);
                mDrawable.setBounds(0, 0, (int) searchSize, (int) searchSize);
            } catch (Exception e) {

            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        if (mDrawable != null) {
            mDrawable.setCallback(null);
            mDrawable = null;
        }
        super.onDetachedFromWindow();
    }
    private void DrawSearchIcon(Canvas canvas) {
        if (this.getText().toString().length() == 0) {
            float textWidth = paint.measureText("搜索");
            float textHeight = getFontLeading(paint);

            float dx = (getWidth() - searchSize - textWidth - 8) / 2;
            float dy = (getHeight() - searchSize) / 2;

            canvas.save();
            canvas.translate(getScrollX() + dx, getScrollY() + dy);
            if (mDrawable != null) {
                mDrawable.draw(canvas);
            }
            canvas.drawText("搜索", getScaleX()+searchSize+20, getScrollY() + (getHeight() - (getHeight() - textHeight) / 2) - paint.getFontMetrics().bottom - dy, paint);
            canvas.restore();
        }
    }
    public float getFontLeading(Paint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return fm.bottom - fm.top;
    }
    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        if (hasFocus) {
            //cursorVisible
            this.setCursorVisible(true);
        } else {
            this.setCursorVisible(false);
        }
    }

}
