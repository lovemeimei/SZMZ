package com.szmz.more;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.widget.VerificationCodeInput;

import butterknife.BindView;

public class ActModifyPhone2 extends ActBase {

    @BindView(R.id.verificationCodeInput)
    VerificationCodeInput verificationCodeInput;

    @BindView(R.id.tv_phonenum)
    TextView tvNum;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_modify_phone2;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("更改手机号");
        setRightVisible(true);
        setRightShow("完成");

        verificationCodeInput.setOnCompleteListener(new VerificationCodeInput.Listener() {
            @Override
            public void onComplete(String content) {

            }
        });

        tvTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAnimFinish();
            }
        });
    }

}
