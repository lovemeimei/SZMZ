package com.szmz.more;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.szmz.ActBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.request.Comm_SQR_findPW;
import com.szmz.entity.request.Comm_SQR_findPW_XJ;
import com.szmz.entity.response.CommResponse;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.TextUtil;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

public class ActFindPW_XJ extends ActBase {

    @BindView(R.id.iv_back)
    ImageView ivBack;


    @BindView(R.id.et_idcard)
    EditText etCard;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.et_pw1)
    EditText etPw1;

    @BindView(R.id.et_pw2)
    EditText etPw2;

    String code;

    String pw,pw2;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_find_pwxj;
    }

    @Override
    protected void initUI() {
        super.initUI();
    }

    @OnClick({R.id.btn_submit,R.id.iv_back2})
    public void onSubmit(View view){
        switch (view.getId()){
            case R.id.btn_submit:
                doSub();
                break;
            case R.id.iv_back2:
                myAnimFinish();
                break;
        }
    }

    private void doSub(){
        pw = etPw1.getText().toString().trim();
        pw2 = etPw2.getText().toString().trim();

        code = etCard.getText().toString().trim();

        if (TextUtils.isEmpty(code)){
            doToast("请输入身份证号");
            return;
        }
        if (!TextUtil.isPersonCode(code)){
            doToast("请输入有效身份证号");
            return;
        }
        if (TextUtils.isEmpty(pw)){
            doToast("请输入新密码");
            return;
        }
        if (TextUtils.isEmpty(pw2)){
            doToast("请输入确认密码");
            return;
        }

        if (!pw.equals(pw2)){
            doToast("密码输入不一致");
            return;
        }

        String mobile =etMobile.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)){
            doToast("请输入手机号");
            return;
        }
        if (!TextUtil.isMobileNumber(mobile)){
            doToast("请输入有效手机号");
            return;
        }

        Comm_SQR_findPW_XJ req = new Comm_SQR_findPW_XJ(getUser().getPhone(),code,pw);

        Call<CommResponse> call = App.getApiProxyComSQR().findPWSQR_XJ(req);

        ApiUtil<CommResponse> apiUtil = new ApiUtil<>(context,call,new SimpleApiListener<CommResponse>(){
            @Override
            public void doSuccess(CommResponse result) {
                doToast("修改成功");
                myAnimFinish();
            }
        },true);

        apiUtil.excute();
    }

}
