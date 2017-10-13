package com.szmz.more;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.utils.CountDownUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class ActFindPW extends ActBase {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_phonenum)
    EditText etPhoneNum;
    @BindView(R.id.et_code)
    EditText etCode;

    @BindView(R.id.btn_getCode)
    TextView btnGetCode;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_find_pw;
    }

    @Override
    protected void initUI() {
        super.initUI();
    }

    @OnClick({R.id.btn_submit,R.id.btn_getCode,R.id.iv_back2})
    public void onSubmit(View view){
        switch (view.getId()){
            case R.id.btn_getCode:
                CountDownUtil util = new CountDownUtil(context,btnGetCode,60*1000,1000);
                util.start();
                break;
            case R.id.btn_submit:
                trans(ActFindPW2.class);
                myAnimFinish();
                break;
            case R.id.iv_back2:
                myAnimFinish();
                break;
        }
    }
}
