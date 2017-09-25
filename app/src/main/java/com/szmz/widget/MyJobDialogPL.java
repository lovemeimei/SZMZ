package com.szmz.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.szmz.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/12 0012下午 2:34
 */

public class MyJobDialogPL extends Dialog{

    private MDialogListener listener;

    public MyJobDialogPL(@NonNull Context context, MDialogListener listener) {
        super(context);
        this.listener= listener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_db_pl);

        ButterKnife.bind(this);

    }

    @OnClick({
            R.id.ll_tongyi,R.id.ll_tuihui,R.id.ll_feichu,R.id.ll_butongguo,
    })
    public void doClick(View v){
        switch (v.getId()){
            case R.id.ll_tongyi:
                listener.doClick(0);
                this.dismiss();
                break;
            case R.id.ll_tuihui:
                listener.doClick(1);
                this.dismiss();
                break;
            case R.id.ll_feichu:
                listener.doClick(2);
                this.dismiss();
                break;
            case R.id.ll_butongguo:
                listener.doClick(3);
                this.dismiss();
                break;
        }
    }

    public interface MDialogListener{
        void  doClick(int positon);
    }

}
