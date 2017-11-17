package com.szmz.more;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.szmz.ActBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.SystemConst;
import com.szmz.entity.request.JZ_Login_Code_Req;
import com.szmz.entity.request.JZ_Scan_QZ_Req;
import com.szmz.entity.response.CommResponse;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.GsonUtil;

import org.json.JSONObject;

import butterknife.OnClick;
import retrofit2.Call;

public class ActCodeLogin extends ActBase {

    private String uuid;
    private String type;
    private String systemID;
    private String account;
    private String msg;
    JZ_Login_Code_Req logReq;
    JZ_Scan_QZ_Req QZreq;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_code_login;
    }

    @Override
    protected void initUI() {
        super.initUI();
        uuid = getIntent().getStringExtra("uuid");
        type = getIntent().getStringExtra("type");
        systemID = getIntent().getStringExtra("systemID");

        msg = getIntent().getStringExtra("msg");

//        {"instanceId":"927862","SealId":"8a8a80235f0e590e015f0e6ad6010024","systemId":"c2hqenh4Z2x4dDE1MDk1MjU1OTM2Mzg=","url":"http://10.10.0.169:8080/jeecg/loginController.do?appQuest"}
    //{"uuid":"33905","systemId":"c2hqenh4Z2x4dDE1MDk1MjU1OTM2Mzg=","url":"http://10.10.0.169:8080/jeecg/loginController.do?appQuest"}

        if (msg.contains("uuid")){
           logReq = GsonUtil.deser(msg,JZ_Login_Code_Req.class);
        }else if (msg.contains("instanceId")){
            QZreq =GsonUtil.deser(msg,JZ_Scan_QZ_Req.class);
        }
    }

    @OnClick({R.id.tv_close,R.id.btn_submit})
    public void doClick(View view){
            switch (view.getId()){
                case R.id.tv_close:
                    myAnimFinish();
                    break;
                case R.id.btn_submit:
                    doLogin();
                    break;
            }
    }

    private void doLogin(){

        if (SystemConst.SystemID_JZ.equals(logReq.getSystemId())){
            logReq.setAccount(App.getInstance().getLoginUser().getAccountJZ());
        }else if (SystemConst.SystemID_YZS.equals(logReq.getSystemId())){
            logReq.setAccount(App.getInstance().getLoginUser().getAccountYZS());
        }


        Call<CommResponse> call = App.getApiProxyJZ().loginCode(logReq);

        ApiUtil<CommResponse> apiUtil = new ApiUtil<>(context,call,new SimpleApiListener<CommResponse>(){
            @Override
            public void doSuccess(CommResponse result) {
                super.doSuccess(result);
            }
        },true);

        apiUtil.excute();
    }

    private void doLogin2(){

        if (SystemConst.SystemID_JZ.equals(QZreq.getSystemId())){
            QZreq.setAccount(App.getInstance().getLoginUser().getAccountJZ());
        }else if (SystemConst.SystemID_YZS.equals(QZreq.getSystemId())){
            QZreq.setAccount(App.getInstance().getLoginUser().getAccountYZS());
        }

        Call<CommResponse> call = App.getApiProxyJZ().scanQZ(QZreq);

        ApiUtil<CommResponse> apiUtil = new ApiUtil<>(context,call,new SimpleApiListener<CommResponse>(){
            @Override
            public void doSuccess(CommResponse result) {
                super.doSuccess(result);
            }
        },true);

        apiUtil.excute();
    }


}
