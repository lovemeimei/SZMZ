package com.szmz.more;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.szmz.ActBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.request.JZ_Comm_bindphone;
import com.szmz.entity.response.CommResponse;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.CountDownUtil;
import com.szmz.utils.TextUtil;
import com.szmz.utils.UIUtil;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

public class ActBindPhone_Worker extends ActBase {

    @BindView(R.id.et_phonenum)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_nobind)
    TextView tvNobind;
    @BindView(R.id.btn_getCode)
    TextView btnGetCode;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_bind_phone__worker;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("绑定手机号");
    }

    @OnClick({R.id.btn_submit, R.id.btn_getCode, R.id.tv_nobind})
    public void doClick(View v) {

        switch (v.getId()) {
            case R.id.btn_submit:
                if (doCheck()){
                    getWebInfo();
                }
                break;
            case R.id.btn_getCode:

                CountDownUtil util = new CountDownUtil(context, btnGetCode, 60 * 1000, 1000);
                util.start();
                break;
            case R.id.tv_nobind:
                myAnimFinish();
                break;
        }
    }

    private boolean doCheck() {

        String num= etPhone.getText().toString().trim();
        if (TextUtils.isEmpty(num)){
            UIUtil.doToast("请输入手机号");
            return false;
        }
        if (!TextUtil.isMobileNumber(num)){
            UIUtil.doToast("请输入有效手机号");
            return false;
        }
        return true;
    }

    private void getWebInfo() {

        JZ_Comm_bindphone req = new JZ_Comm_bindphone(App.getInstance().getLoginUser().getUserName(), App.getInstance().getLoginUser().getPw(), etPhone.getText().toString().trim());

        Call<CommResponse> call = App.getApiProxyCom().bindPhone(req);

        ApiUtil<CommResponse> apiUtil = new ApiUtil<>(context,call,new SimpleApiListener<CommResponse>(){
            @Override
            public void doSuccess(CommResponse result) {
                super.doSuccess(result);
                myAnimFinish();
            }
        },true);

        apiUtil.excute();
    }
}
