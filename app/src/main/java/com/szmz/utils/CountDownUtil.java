package com.szmz.utils;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;
import android.widget.TextView;

import com.szmz.R;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/10/13 0013下午 3:26
 */

public class CountDownUtil extends CountDownTimer{

    private TextView tv;
    private Context context;
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public CountDownUtil(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    public CountDownUtil(Context context, TextView textView, long millisInFuture, long countDownInterval){
        super(millisInFuture, countDownInterval);
        this.tv = textView;
        this.context = context;
    }

    @Override
    public void onTick(long millisUntilFinished) {

        tv.setClickable(false);
        tv.setText("重新发送("+millisUntilFinished / 1000 +")" );
        tv.setBackgroundColor(ContextCompat.getColor(context,R.color.gray2));

        SpannableString spannableString = new SpannableString(tv.getText().toString());
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context,R.color.blue)), 5, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(spannableString);

    }

    @Override
    public void onFinish() {
        tv.setText("重新获取验证码");
        tv.setClickable(true);
        tv.setBackgroundColor(ContextCompat.getColor(context,R.color.blue));
    }
}
