package com.szmz.more;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
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

import butterknife.OnClick;
import retrofit2.Call;

public class ActCodeQZ extends ActBase {


    private String account;
    private String msg;
    private String type="A003";
    private String sealid="";
    private String systemID="";
    ScanCode code;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_code_qz;
    }

    @Override
    protected void initUI() {
        super.initUI();

        setTitle("电子签章");
        setLeftVisible(true);

        msg = getIntent().getStringExtra("msg");

//        {"instanceId":"927862","SealId":"8a8a80235f0e590e015f0e6ad6010024","systemId":"c2hqenh4Z2x4dDE1MDk1MjU1OTM2Mzg=","url":"http://10.10.0.169:8080/jeecg/loginController.do?appQuest"}

        code = GsonUtil.deser(msg,ScanCode.class);
//        if (SystemConst.SystemID_JZ.equals(code.getSystemId())){
////            account= App.getInstance().getLoginUser().getAccountJZ();
//            account=App.getInstance().getLoginUser().getIdJZ();
//
//        }else if (SystemConst.SystemID_YZS.equals(code.getSystemId())){
//            account=App.getInstance().getLoginUser().getAccountYZS();
//        }

        if(!TextUtils.isEmpty(code.getSystemId())){
            if ( code.getSystemId().contains(SystemConst.SystemID_JZ)){
//            account=App.getInstance().getLoginUser().getAccountJZ();
                account=App.getInstance().getLoginUser().getIdJZ();
                systemID=SystemConst.SystemID_JZ;

            }else if (code.getSystemId().contains(SystemConst.SystemID_YZS)){
                account=App.getInstance().getLoginUser().getAccountYZS();
                systemID=SystemConst.SystemID_YZS;

            }else {
                systemID=code.getSystemId();
                account="";
            }
        }else {
            account="";
            systemID=code.getSystemId();

        }
    }

    @OnClick({R.id.btn_cancel,R.id.btn_submit})
    public void doClick(View view){
        switch (view.getId()){
            case R.id.btn_cancel:
                MaterialDialog dialog = new MaterialDialog.Builder(context)
                        .title("提示")
                        .content("确定要取消吗？")
                        .positiveText("确定")
                        .cancelable(false)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                ActCodeQZ.this.finish();
                            }
                        })
                        .negativeText("取消")
                        .show();
                break;
            case R.id.btn_submit:
                doLogin();
                break;
        }
    }

    private void doLogin(){


        JZ_Login_Code_Req logReq;
        sealid  = code.getSealId();

        logReq = new JZ_Login_Code_Req(code.getUuid(),account,systemID,type,sealid);

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


}
