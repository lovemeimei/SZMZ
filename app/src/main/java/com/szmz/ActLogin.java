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
import com.szmz.entity.request.phoneLoginRequest;
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
                User user = new User();
                user.setAccountHD("510401");
                App.getInstance().login(user);
                Intent intent = new Intent(this, ActMain.class);
                intent.putExtra("Type", type);
                startActivity(intent);
                break;
        }


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
                    if (systemMsgBeens != null && systemMsgBeens.size() > 0) {
                        for (int i = 0; i < systemMsgBeens.size(); i++) {
                            phoneLoginR.ResultBean.SystemMsgBean item = systemMsgBeens.get(i);
                            if (item.getSystemID().equals(SystemConst.SystemID_JZ)) {
                                user.setAccountJZ(item.getAccount());
                            }
                            if (item.getSystemID().equals(SystemConst.SystemID_YZS)) {
                                user.setAccountYZS(item.getAccount());
                            }
                            if (item.getSystemID().equals(SystemConst.SystemID_SH)) {
                                user.setAccountHD(item.getAccount());
                            }
                        }
                    }
                    user.setUserName(etUser.getText().toString().trim());
                    user.setPw(etPW.getText().toString().trim());
                    user.setType(type);
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

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.rb_work:
                type = 1;
                break;
            case R.id.rb_user:
                type = 0;
                break;
        }
    }
}
