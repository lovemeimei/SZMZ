package com.szmz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.szmz.entity.request.Comm_SQR_modifyPW_Req;
import com.szmz.entity.request.Comm_getCode_Req;
import com.szmz.entity.request.Register_Req;
import com.szmz.entity.response.CommResponse;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.CountDownUtil;
import com.szmz.utils.TextUtil;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

/**
 * 注册
 */
public class ActRegist extends ActBase{

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_phonenum)
    EditText etPhoneNum;
    @BindView(R.id.et_cardnum)
    EditText etCardNum;
    @BindView(R.id.et_code)
    EditText etCode;

    @BindView(R.id.et_pw)
    EditText etPw;
    @BindView(R.id.btn_getCode)
    TextView btnGetCode;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_regist;
    }

    @Override
    protected void initUI() {
        super.initUI();
    }

    @OnClick({R.id.btn_submit,R.id.btn_getCode,R.id.iv_back2})
    public void onSubmit(View view){
        switch (view.getId()){
            case R.id.btn_getCode:
                CountDownUtil util = new CountDownUtil(context,btnGetCode,30*1000,1000);
                util.start();
                getCode();
                break;
            case R.id.btn_submit:
                break;
            case R.id.iv_back2:
                myAnimFinish();
                break;
        }
    }

    private void doRegist(){

        String phone = etPhoneNum.getText().toString().trim();
        String code = etCode.getText().toString().trim();
        String idCard = etCardNum.getText().toString().trim();
        String pw = etPw.getText().toString().trim();
        if (TextUtils.isEmpty(phone)){
            doToast("请输入手机号");
            return;
        }
        if (!TextUtil.isMobileNumber(phone)){
            doToast("请输入正确的手机号");
            return;
        }
        if (TextUtils.isEmpty(code)){
            doToast("请输入验证码");
            return;
        }
        if (TextUtils.isEmpty(idCard)){
            doToast("请输入身份证号码");
            return;
        }
        if (!TextUtil.isPersonCode(idCard)){
            doToast("请输入正确的身份证号码");
            return;
        }
        if (TextUtils.isEmpty(pw)){
            doToast("请输入密码");
            return;
        }
        Register_Req req = new Register_Req("","",idCard,pw,phone,code,"");

        Call<CommResponse> call = App.getApiProxyComSQR().registerSQR(req);

        ApiUtil<CommResponse> apiUtil = new ApiUtil<>(context,call,new SimpleApiListener<CommResponse>(){
            @Override
            public void doSuccess(CommResponse result) {
                doToast("注册成功");
            }
        },true);

        apiUtil.excute();
     }

    private void getCode(){
        String phone = etPhoneNum.getText().toString().trim();
        if (TextUtils.isEmpty(phone)){
            doToast("请输入手机号");
            return;
        }
        if (!TextUtil.isMobileNumber(phone)){
            doToast("请输入正确的手机号");
            return;
        }
        Comm_getCode_Req req = new Comm_getCode_Req(phone);

        Call<CommResponse> call = App.getApiProxyComSQR().getCodeSQR(req);

        ApiUtil<CommResponse> apiUtil = new ApiUtil<>(context,call,new SimpleApiListener<CommResponse>(){
            @Override
            public void doSuccess(CommResponse result) {
                doToast("验证码已发送");
            }
        },true);

        apiUtil.excute();
    }
}
