package com.szmz.more;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.szmz.ActBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.SystemConst;
import com.szmz.entity.User;
import com.szmz.entity.request.Comm_SQRXJ_bingphone_Req;
import com.szmz.entity.request.Comm_SQR_bingphone_Req;
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
@BindView(R.id.btn_submit)
    Button btn;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_modify_phone;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        if (TextUtils.isEmpty(App.getInstance().getLoginUser().getPhone())){
            setTitle("绑定手机号码");
        }else {
            setTitle("更改手机号码");
        }

        if (SystemConst.systemID==1){
            //新疆的直接提交
            btn.setText("完成");
        }
    }

    @OnClick(R.id.btn_submit)
    public void doClick(View v) {

        if (SystemConst.systemID==1){
            //新疆的直接提交
            doSubSQR();
        }else {
            doRegist();
        }

    }


    private boolean doCheck(){
         phone = etPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)){
            doToast("请输入手机号");
            return false;
        }
        if (!TextUtil.isMobileNumber(phone)){
            doToast("请输入正确的手机号");
            return false;
        }
        return true;
    }

    private void doSubSQR(){
        if (!doCheck())
            return;

        Comm_SQRXJ_bingphone_Req req = new Comm_SQRXJ_bingphone_Req(getUser().getUserName(),getUser().getPw(),phone);
        Call<CommResponse> call = App.getApiProxyComSQR().bindingPhoneSQR_XJ(req);

        ApiUtil<CommResponse> apiUtil = new ApiUtil<>(context,call,new SimpleApiListener<CommResponse>(){
            @Override
            public void doSuccess(CommResponse result) {
                doToast("操作成功");
               User user= App.getInstance().getLoginUser();
                user.setPhone(phone);
                App.getInstance().login(user);
                myAnimFinish();
            }
        },true);

        apiUtil.excute();
    }
    String phone;

    private void doRegist(){
         phone = etPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)){
            doToast("请输入手机号");
            return;
        }
        if (!TextUtil.isMobileNumber(phone)){
            doToast("请输入正确的手机号");
            return;
        }
        Comm_getCode_Req req = new Comm_getCode_Req(phone);

//        Call<CommResponse> call = App.getApiProxyComSQR().getCodeSQR_XJ(req);
        Call<CommResponse> call = App.getApiProxyComSQR().getCodeSQR(req);

        ApiUtil<CommResponse> apiUtil = new ApiUtil<>(context,call,new SimpleApiListener<CommResponse>(){
            @Override
            public void doSuccess(CommResponse result) {
                doToast("验证码已发送");
                String  msg = result.Error.getErrorMessage();
//                发送成功！验证码为：121246
//              String code = msg.split(":")[1];
                String code = "";
                try{
                     code = msg.split("：")[1];
                    //String code = msg.split(":")[1];
                }catch (Exception e){

                }


                Intent intent =new Intent(context,ActModifyPhone2.class);
                intent.putExtra("num",etPhone.getText().toString().trim());
                intent.putExtra("code",etPhone.getText().toString().trim());

                startActivity(intent);
                myAnimFinish();
            }
        },true);

        apiUtil.excute();
    }
}
