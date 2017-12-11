package com.szmz;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

import com.szmz.entity.User;
import com.szmz.entity.UserSQR;
import com.szmz.entity.request.Comm_ipid_req;
import com.szmz.entity.request.LoginSQR_Req;
import com.szmz.entity.request.phoneLoginRequest;
import com.szmz.entity.response.Comm_ipid_res;
import com.szmz.entity.response.LoginSQR_Res;
import com.szmz.entity.response.phoneLoginR;
import com.szmz.more.ActFindPW;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.TextUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

/**
 * 登录
 */
public class ActLogin extends ActBase implements CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.et_username)
    EditText etUser;
    @BindView(R.id.et_pw)
    EditText etPW;
    @BindView(R.id.rb_work)
    RadioButton rbWoker;
    @BindView(R.id.rb_user)
    RadioButton rbUser;

    private int type = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initUI() {
        super.initUI();
        SystemEnv.deleteDataList();
        rbUser.setOnCheckedChangeListener(this);
        rbWoker.setOnCheckedChangeListener(this);

        String name = SystemEnv.getUserName();

        String pw = SystemEnv.getUserPw();

        type = SystemEnv.getLoginType();
        if (type==1){
            rbWoker.setChecked(true);
        }else {
           rbUser.setChecked(true);
        }
        if (!TextUtils.isEmpty(name)){
            etUser.setText(name);
            etPW.setText(pw);
        }

        getIPID();
    }

    @OnClick({R.id.btn_submit, R.id.tv_zc, R.id.tv_wjmm})
    public void doSubmit(View v) {

        switch (v.getId()) {
            case R.id.tv_zc:
                trans(ActRegist.class);
                break;
            case R.id.tv_wjmm:
                trans(ActFindPW.class);
                break;
            case R.id.btn_submit:

                if (type==1){
                    login();
                }else {
                    loginSQR();
                }

                break;
        }


    }

    private void login() {
        if (!doCheck())
            return;
        final phoneLoginRequest request = new phoneLoginRequest(etUser.getText().toString().trim(), etPW.getText().toString().trim());
        Call<phoneLoginR> call = App.getApiProxyCom().login(request);
        ApiUtil<phoneLoginR> apiUtil = new ApiUtil<phoneLoginR>(context, call, new SimpleApiListener<phoneLoginR>() {
            @Override
            public void doSuccess(phoneLoginR result) {

                List<phoneLoginR.ResultBean> mResult = result.Result;

                if (mResult != null && mResult.size() > 0) {
                    phoneLoginR.ResultBean bean = mResult.get(0);
                    User user = new User();
                    List<phoneLoginR.ResultBean.SystemMsgBean> systemMsgBeens = bean.getSystemMsg();

                    if (systemMsgBeens != null && systemMsgBeens.size() > 0) {

                        for (int i = 0; i < systemMsgBeens.size(); i++) {
                            phoneLoginR.ResultBean.SystemMsgBean item = systemMsgBeens.get(i);
                            if (item.getIdentification().equals(SystemConst.SystemID_JZ)) {
                                user.setAccountJZ(item.getAccount());
                            }
                            if (item.getIdentification().equals(SystemConst.SystemID_YZS)) {
                                user.setAccountYZS(item.getAccount());
                            }
                            if (item.getIdentification().equals(SystemConst.SystemID_SH)) {
                                user.setAccountHD(item.getAccount());
                            }
                        }
                    }

//                    user.setAccountYZS("admin");
//                    user.setAccountJZ("admin");
                    user.setUserName(etUser.getText().toString().trim());
                    user.setPw(etPW.getText().toString().trim());
                    user.setType(type);
                    user.setPersonId(bean.getPersonal().getPersonalId());
                    user.setPhone(bean.getPersonal().getMobilePhone());
                    user.setRealName(bean.getPersonal().getRealName());
                    user.setDepartName(bean.getPersonal().getDepartName());
                    user.setOfficePhone(bean.getPersonal().getOfficePhone());
                    user.setEmail(bean.getPersonal().getEmaile());
                    App.getInstance().login(user);
                    //保存用户名密码
                    SystemEnv.saveUserName(etUser.getText().toString().trim());
                    SystemEnv.saveUserPw(etPW.getText().toString().trim());
                    SystemEnv.saveLoginType(type);

                    trans(ActMain.class);
                }


            }
        }, true);
        apiUtil.excute();
    }


    private void loginSQR(){
        LoginSQR_Req sqr_req = new LoginSQR_Req(etUser.getText().toString().trim(),etPW.getText().toString().trim());
        Call<LoginSQR_Res> call =App.getApiProxyComSQR().loginSQR(sqr_req);
                ApiUtil<LoginSQR_Res> apiUtil = new ApiUtil<>(context,call,new SimpleApiListener<LoginSQR_Res>(){

                    @Override
                    public void doSuccess(LoginSQR_Res result) {
                        super.doSuccess(result);

                        UserSQR userSQR = result.Result.get(0);
                        User user = new User();
                        user.setUserName(etUser.getText().toString().trim());
                        user.setPw(etPW.getText().toString().trim());
                        user.setType(type);

                        user.setPhone(userSQR.getMOBILE());
                        user.setRealName(userSQR.getREALNAME());
                        user.setEmail(userSQR.getEMAIL());
                        user.setAdderss(userSQR.getADDRESS());
                        user.setIdCode(userSQR.getIDCARD());
                        user.setSex(userSQR.getSEX()+"");

                        user.setAccountJZ(etUser.getText().toString());
                        user.setAccountYZS(etUser.getText().toString());

                        App.getInstance().login(user);
                        //保存用户名密码
                        SystemEnv.saveUserName(etUser.getText().toString().trim());
                        SystemEnv.saveUserPw(etPW.getText().toString().trim());
                        SystemEnv.saveLoginType(type);

                        trans(ActMain.class);
                    }
                },true);
        apiUtil.excute();
    }

    private boolean doCheck() {
        if (TextUtils.isEmpty(etUser.getText().toString())) {
            doToast("请输入用户名");
            return false;
        }
        if (TextUtils.isEmpty(etPW.getText().toString())) {
            doToast("请输入密码");
            return false;
        }
        return true;
    }

    private boolean exitFlag = false;

    @Override
    public void onBackPressed() {
        if (!exitFlag) {
            doToast("再按一次,退出系统");
            exitFlag = true;
            final Timer mTimer = new Timer();
            TimerTask mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    exitFlag = false;
                    mTimer.cancel();
                }
            };
            mTimer.schedule(mTimerTask, 3000, 10000);
        } else {
            App.exit();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (rbWoker.isChecked()){
            type = 1;
        }else {
            type = 0;
        }

    }

    private void getIPID(){
        final String ipJZ="222.222.49.34:9095";
        final String ipYZS="222.222.49.34:8088";
        final String ipHD="222.222.49.34:8099";
        String ips=ipJZ+","+ipYZS+","+ipHD;
        final Comm_ipid_req req = new Comm_ipid_req(ips);

        Call<Comm_ipid_res> call = App.getApiProxyCom().getIPSID(req);

        ApiUtil<Comm_ipid_res> apiUtil = new ApiUtil<>(context,call,new SimpleApiListener<Comm_ipid_res>(){
            @Override
            public void doSuccess(Comm_ipid_res result) {
                super.doSuccess(result);
                List<Comm_ipid_res.ResultBean> items = new ArrayList<>();

                if (result!=null )
                    items = result.Result;

                if (items!=null && items.size()>0){
                    for (int i=0;i<items.size();i++){
                        Comm_ipid_res.ResultBean item = items.get(i);
                        if (item.getIp().equals(ipJZ)){
                            SystemConst.SystemID_JZ = item.getResult();
                        }else if (item.getIp().equals(ipYZS)){
                            SystemConst.SystemID_YZS = item.getResult();
                        }else if (item.getIp().equals(ipHD)){
                            SystemConst.SystemID_SH = item.getResult();
                        }
                    }
                }
                //{"Result":[{"result":"emRzaGJ6MTUwNzk2MTQyNjE1Nw==","ip":"222.222.49.34:9095"},{"result":"eWxienl6czE1MDgyMjQyMzc2MzE=","ip":"222.222.49.34:8088"},{"result":"","ip":"zhongkehengyun.com:8081"}],"TotalNum":0,"Error":{"ErrorMessage":"信息获取成功","ErrorCode":0}}


            }
        },true);

        apiUtil.excute();

    }
}
