package com.szmz.more;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.szmz.ActBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.request.Comm_getCode_Req;
import com.szmz.entity.response.CommResponse;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.TextUtil;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/7 0007下午 4:49
 */
public class ActModifyPhone extends ActBase {

    @BindView(R.id.et_phonenum)
    EditText etPhone;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_modify_phone;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("更改手机号码");
    }

    @OnClick(R.id.btn_submit)
    public void doClick(View v) {

       doRegist();

    }

    private void doRegist(){
        String phone = etPhone.getText().toString().trim();
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
                Intent intent =new Intent(context,ActModifyPhone2.class);
                intent.putExtra("num",etPhone.getText().toString().trim());
                startActivity(intent);
                myAnimFinish();
            }
        },true);

        apiUtil.excute();
    }
}
