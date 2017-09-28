package com.szmz;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.szmz.entity.User;
import com.szmz.entity.request.HD_SearchDB;
import com.szmz.entity.request.phoneLoginRequest;
import com.szmz.entity.response.HD_SearchDB_RES;
import com.szmz.entity.response.phoneLoginR;
import com.szmz.more.ActFindPW;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

/**
 * 登录
 */
public class ActLogin extends ActBase {

    @BindView(R.id.et_username)
    EditText etUser;
    @BindView(R.id.et_pw)
    EditText etPW;

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

                test();

//                if (!doCheck()) {
//                    return;
//                }
                if (etUser.getText().toString().equals("1")) {
                    Intent intent = new Intent(this, ActMain.class);
                    intent.putExtra("Type", 1);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(this, ActMain.class);
                    intent.putExtra("Type", 0);
                    startActivity(intent);
                }
//                login();
                break;
        }


    }

    private void test(){
        HD_SearchDB reqbody = new HD_SearchDB("510401");

        Call<HD_SearchDB_RES> call = App.getApiProxy().test(reqbody);
        ApiUtil<HD_SearchDB_RES> apiUtil = new ApiUtil<HD_SearchDB_RES>(context,call,new SimpleApiListener<HD_SearchDB_RES>(){
            @Override
            public void doSuccess(HD_SearchDB_RES result) {
                super.doSuccess(result);
            }
        },true);
        apiUtil.excute();

    }

    private void login() {
        final phoneLoginRequest request = new phoneLoginRequest(etUser.getText().toString().trim(), etPW.getText().toString().trim());
        Call<phoneLoginR> call = App.getApiProxy().login(request);
        ApiUtil<phoneLoginR> apiUtil = new ApiUtil<phoneLoginR>(context, call, new SimpleApiListener<phoneLoginR>() {
            @Override
            public void doSuccess(phoneLoginR result) {
                super.doSuccess(result);

                List<phoneLoginR.ResultBean> mResult = result.Result;

                if (mResult != null && mResult.size() > 0) {
                    phoneLoginR.ResultBean bean = mResult.get(0);
                    User user = new User();
                    List<phoneLoginR.ResultBean.SystemMsgBean> systemMsgBeens = bean.getSystemMsg();
                    if (systemMsgBeens!=null && systemMsgBeens.size()>0){
                        for (int i=0;i<systemMsgBeens.size();i++){
                            phoneLoginR.ResultBean.SystemMsgBean item = systemMsgBeens.get(i);
                            if (item.getSystemID().equals(SystemConst.SystemID_JZ)){
                                user.setAccountJZ(item.getAccount());
                            }
                            if (item.getSystemID().equals(SystemConst.SystemID_YZS)){
                                user.setAccountYZS(item.getAccount());
                            }
                            if (item.getSystemID().equals(SystemConst.SystemID_SH)){
                                user.setAccountHD(item.getAccount());
                            }
                        }
                    }
                    user.setUserName(etUser.getText().toString().trim());
                    user.setPw(etPW.getText().toString().trim());
                    App.getInstance().login(user);
                    trans(ActMain.class);
                }


            }
        }, true);
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
}
