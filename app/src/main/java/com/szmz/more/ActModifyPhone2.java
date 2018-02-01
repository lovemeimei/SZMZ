package com.szmz.more;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.szmz.ActBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.request.Comm_SQRXJ_bingphone_Req;
import com.szmz.entity.request.Comm_SQR_bingphone_Req;
import com.szmz.entity.request.JZ_Comm_modifyPhone;
import com.szmz.entity.response.CommResponse;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.widget.VerificationCodeInput;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

public class ActModifyPhone2 extends ActBase {

    @BindView(R.id.verificationCodeInput)
    VerificationCodeInput verificationCodeInput;

    @BindView(R.id.tv_phonenum)
    TextView tvNum;
    private String phoneNum;
    private String code;
    private String codeSQR;
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

        phoneNum = getIntent().getStringExtra("num");
        codeSQR = getIntent().getStringExtra("code");

        if (!TextUtils.isEmpty(code))


        if (!TextUtils.isEmpty(phoneNum))
            tvNum.setText(phoneNum);

        verificationCodeInput.setOnCompleteListener(new VerificationCodeInput.Listener() {
            @Override
            public void onComplete(String content) {
                code = content;
            }
        });

        tvTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getUser().getType()==1){

                    doSub();
                }else {
                    doSubSQR();
                }
            }
        });
    }

    private void doSubSQR(){
        if (!doCheck())
            return;
//申请人本地校验
//        if (!code.equals(codeSQR)){
//            doToast("验证码不正确");
//            return;
//        }

        Comm_SQR_bingphone_Req req = new Comm_SQR_bingphone_Req(getUser().getUserName(),getUser().getPw(),phoneNum);
        Call<CommResponse> call = App.getApiProxyComSQR().bindingPhoneSQR(req);

//        Comm_SQRXJ_bingphone_Req req = new Comm_SQRXJ_bingphone_Req(getUser().getUserName(),getUser().getPw(),phoneNum);
//        Call<CommResponse> call = App.getApiProxyComSQR().bindingPhoneSQR_XJ(req);

        ApiUtil<CommResponse> apiUtil = new ApiUtil<>(context,call,new SimpleApiListener<CommResponse>(){
            @Override
            public void doSuccess(CommResponse result) {
                doToast("修改成功");
                myAnimFinish();
            }
        },true);

        apiUtil.excute();
    }

    private void doSub(){

        if (!doCheck())
            return;

        JZ_Comm_modifyPhone req = new JZ_Comm_modifyPhone(App.getInstance().getLoginUser().getPhone(),phoneNum,code);
        Call<CommResponse> call = App.getApiProxyCom().modifyPhone(req);

        ApiUtil<CommResponse> apiUtil = new ApiUtil<>(context,call,new SimpleApiListener<CommResponse>(){
            @Override
            public void doSuccess(CommResponse result) {
               doToast("修改成功");
                myAnimFinish();
            }
        },true);

        apiUtil.excute();
    }

    private boolean doCheck(){
        if (TextUtils.isEmpty(code)){
            doToast("请输入验证码");
            return false;
        }
        return true;
    }
}
