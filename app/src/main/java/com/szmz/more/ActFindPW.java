package com.szmz.more;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.szmz.ActBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.SystemConst;
import com.szmz.entity.request.Comm_getCode_Req;
import com.szmz.entity.response.CommResponse;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.CountDownUtil;
import com.szmz.utils.TextUtil;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

public class ActFindPW extends ActBase {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_phonenum)
    EditText etPhoneNum;
    @BindView(R.id.et_code)
    EditText etCode;

    String code;
    String phone;
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
                doRegist();
                break;
            case R.id.btn_submit:

                code = etCode.getText().toString();
                if (TextUtils.isEmpty(code)){
                    doToast("请输入验证码");
                    return;
                }
                Intent intent = new Intent(context, ActFindPW2.class);
                intent.putExtra("code",code);
                intent.putExtra("phone",phone);
                startActivity(intent);
                myAnimFinish();
                break;
            case R.id.iv_back2:
                myAnimFinish();
                break;
        }
    }

    private void doRegist(){
        phone = etPhoneNum.getText().toString().trim();
        if (TextUtils.isEmpty(phone)){
            doToast("请输入手机号");
            return;
        }
        if (!TextUtil.isMobileNumber(phone)){
            doToast("请输入正确的手机号");
            return;
        }
        CountDownUtil util = new CountDownUtil(context,btnGetCode,60*1000,1000);
        util.start();

        Comm_getCode_Req req = new Comm_getCode_Req(phone);

        Call<CommResponse> call = App.getApiProxyComSQR().getCodeSQR_XJ(req);

        ApiUtil<CommResponse> apiUtil = new ApiUtil<>(context,call,new SimpleApiListener<CommResponse>(){
            @Override
            public void doSuccess(CommResponse result) {
                doToast("验证码已发送");
                String  msg = result.Error.getErrorMessage();
//                发送成功！验证码为：121246
                if (SystemConst.systemID==1){
                    code = msg.split(":")[1];//新疆
                }else {

                    code = msg.split("：")[1];
                }
                etCode.setText(code);
            }
        },true);

        apiUtil.excute();
    }
}
