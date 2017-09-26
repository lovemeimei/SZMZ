package com.szmz.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.TextView;

import com.szmz.R;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/26 0026下午 2:49
 */

public class DialogJobDB extends Dialog{

    private MyLisener lisener;
    Context context;
    private String content;
    private TextView tvOK;
    private TextView tvCancle;
    private TextView tvContent;

    public DialogJobDB(@NonNull Context context,MyLisener lisener,String Content) {
        super(context);
        this.lisener = lisener;
        this.context  =context;
        this.content = Content;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_job_db_alert);
        tvCancle = (TextView) findViewById(R.id.tv_cancle);
        tvOK = (TextView) findViewById(R.id.tv_ok);
        tvContent = (TextView) findViewById(R.id.tv_content);
        tvContent.setText(content);
        tvOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lisener.doOK();
                DialogJobDB.this.dismiss();
            }
        });
        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lisener.doCancel();
                DialogJobDB.this.dismiss();
            }
        });
    }

    public interface MyLisener{
        void doOK();
        void doCancel();
    }
}
