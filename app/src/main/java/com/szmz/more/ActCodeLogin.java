package com.szmz.more;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.szmz.ActBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.SystemConst;
import com.szmz.entity.ScanCode;
import com.szmz.entity.request.JZ_Login_Code_Req;
import com.szmz.entity.request.JZ_Scan_QZ_Req;
import com.szmz.entity.response.CommResponse;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.GsonUtil;
import com.szmz.utils.TextUtil;

import org.json.JSONObject;

import butterknife.OnClick;
import retrofit2.Call;

public class ActCodeLogin extends ActBase {

    private String account;
    private String msg;
    private String systemID="";
    private String type="A001";

    ScanCode code;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_code_login;
    }

    @Override
    protected void initUI() {
        super.initUI();

        msg = getIntent().getStringExtra("msg");
//        type = getIntent().getStringExtra("type");

//        {"instanceId":"927862","SealId":"8a8a80235f0e590e015f0e6ad6010024","systemId":"c2hqenh4Z2x4dDE1MDk1MjU1OTM2Mzg=","url":"http://10.10.0.169:8080/jeecg/loginController.do?appQuest"}

        code = GsonUtil.deser(msg,ScanCode.class);
        if(!TextUtils.isEmpty(code.getSystemId())){
            if ( code.getSystemId().contains(SystemConst.SystemID_JZ)){
//            account=App.getInstance().getLoginUser().getAccountJZ();
                account=App.getInstance().getLoginUser().getIdJZ();
                systemID=SystemConst.SystemID_JZ;
            }else if (code.getSystemId().contains(SystemConst.SystemID_YZS)){
                account=App.getInstance().getLoginUser().getAccountYZS();
                systemID=SystemConst.SystemID_YZS;
            }else {
                account="";
                systemID=code.getSystemId();

            }
        }else {
            account="";
            systemID=code.getSystemId();

        }

    }

    @OnClick({R.id.tv_close,R.id.btn_submit})
    public void doClick(View view){
            switch (view.getId()){
                case R.id.tv_close:
                    myAnimFinish();
                    break;
                case R.id.btn_submit:
                    if (code.getSiteType()==1){
                        doLoginYZS();
                    }else {
                        doLogin();

                    }
                    break;
            }
    }

    private void doLogin(){

//        account=getUser().get;

        JZ_Login_Code_Req logReq;

        logReq = new JZ_Login_Code_Req(code.getUuid(),account,systemID,type,"");

        Call<CommResponse> call = App.getApiProxyJZ().loginCode(logReq);

        ApiUtil<CommResponse> apiUtil = new ApiUtil<>(context,call,new SimpleApiListener<CommResponse>(){
            @Override
            public void doSuccess(CommResponse result) {
                super.doSuccess(result);
                doToast("确认成功！");
                myAnimFinish();
            }
        },true);

        apiUtil.excute();
    }

    private void doLoginYZS(){

//        account=getUser().get;

        JZ_Login_Code_Req logReq;

        logReq = new JZ_Login_Code_Req(code.getUuid(),account,systemID,type,"");

        Call<CommResponse> call = App.getApiProxyYZS().loginCode_YZS(logReq);

        ApiUtil<CommResponse> apiUtil = new ApiUtil<>(context,call,new SimpleApiListener<CommResponse>(){
            @Override
            public void doSuccess(CommResponse result) {
                super.doSuccess(result);
                doToast("确认成功！");
                myAnimFinish();
            }
        },true);

        apiUtil.excute();
    }


}
