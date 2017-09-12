package com.szmz.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.szmz.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/12 0012下午 2:34
 */

public class MyJobDialog extends Dialog{

    private MDialogListener listener;

    public MyJobDialog(@NonNull Context context, MDialogListener listener) {
        super(context);
        this.listener= listener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_db);

        ButterKnife.bind(this);

    }

    @OnClick({
            R.id.ll_tongyi,R.id.ll_tuihui,R.id.ll_yijiao,R.id.ll_butongyi,R.id.ll_butongguo,R.id.ll_guiji
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
            case R.id.ll_yijiao:
                listener.doClick(2);
                break;
            case R.id.ll_butongyi:
                listener.doClick(3);
                this.dismiss();
                break;
            case R.id.ll_butongguo:
                listener.doClick(4);
                this.dismiss();
                break;
            case R.id.ll_guiji:
                listener.doClick(5);
                this.dismiss();
                break;
        }
    }

    public interface MDialogListener{
        void  doClick(int positon);
    }

}
