package com.szmz.more;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.utils.CountDownUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class ActFindPW2 extends ActBase {

    @BindView(R.id.iv_back)
    ImageView ivBack;

    @BindView(R.id.et_pw1)
    EditText etPw1;

    @BindView(R.id.et_pw2)
    EditText etPw2;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_find_pw2;
    }

    @Override
    protected void initUI() {
        super.initUI();
    }

    @OnClick({R.id.btn_submit,R.id.iv_back2})
    public void onSubmit(View view){
        switch (view.getId()){
            case R.id.btn_submit:
                break;
            case R.id.iv_back2:
                myAnimFinish();
                break;
        }
    }
}
