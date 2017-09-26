package com.szmz.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.szmz.R;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/26 0026下午 2:49
 */

public class DialogJobDBEdit extends Dialog{

    private MyLisener lisener;
    Context context;
    private String content;
    private TextView tvOK;
    private TextView tvCancle;
    private TextView tvContent;
    private EditText etContent;

    public DialogJobDBEdit(@NonNull Context context, MyLisener lisener, String Content) {
        super(context);
        this.lisener = lisener;
        this.context  =context;
        this.content = Content;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_job_db_alertss);
        tvCancle = (TextView) findViewById(R.id.tv_cancle);
        tvOK = (TextView) findViewById(R.id.tv_ok);
        tvContent = (TextView) findViewById(R.id.tv_content);
        tvContent.setText(content);
        etContent = (EditText) findViewById(R.id.et_spyj);

        tvOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lisener.doOK(etContent.getText().toString().trim());
                DialogJobDBEdit.this.dismiss();
            }
        });
        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lisener.doCancel();
                DialogJobDBEdit.this.dismiss();
            }
        });
    }

    public interface MyLisener{
        void doOK(String spyj);
        void doCancel();
    }
}
