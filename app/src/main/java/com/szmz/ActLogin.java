package com.szmz;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.szmz.entity.CommMsgSave;
import com.szmz.entity.User;
import com.szmz.entity.UserSQR;
import com.szmz.entity.UserSQR_XJ;
import com.szmz.entity.request.Comm_ipid_req;
import com.szmz.entity.request.LoginSQR_Req;
import com.szmz.entity.request.phoneLoginRequest;
import com.szmz.entity.response.Comm_ipid_res;
import com.szmz.entity.response.LoginSQR_Res;
import com.szmz.entity.response.LoginSQR_XJ_Res;
import com.szmz.entity.response.phoneLoginR;
import com.szmz.more.ActFindPW;
import com.szmz.more.ActFindPW_XJ;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;

import org.xutils.DbManager;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

import static android.support.v4.app.NotificationCompat.PRIORITY_HIGH;
import static android.support.v4.app.NotificationCompat.PRIORITY_MAX;

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

    @BindView(R.id.tv_zc)
    TextView tvZC;
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
        if (type == 1) {
            rbWoker.setChecked(true);
        } else {
            rbUser.setChecked(true);
        }
        if (!TextUtils.isEmpty(name)) {
            etUser.setText(name);
            etPW.setText(pw);
        }

        if (SystemConst.systemID==1){
            //新疆社会人员没有注册功能
            tvZC.setVisibility(View.GONE);
        }

        getIPID();
    }

    @OnClick({R.id.btn_submit, R.id.tv_zc, R.id.tv_wjmm, R.id.btn_submit_outline})
    public void doSubmit(View v) {

        switch (v.getId()) {
            case R.id.tv_zc:
                trans(ActRegist.class);
//                notificaitonMsg();
                break;
            case R.id.tv_wjmm:
                if (type == 1) {
                    doToast("联系系统管理员重置密码!");
                } else {
                    if (SystemConst.systemID==1){
                        //新疆
                        trans(ActFindPW_XJ.class);

                    }else {
                        trans(ActFindPW.class);

                    }
                }
                break;
            case R.id.btn_submit:

                App.setIsOnline(true);
                if (type == 1) {
                    login();
                } else {
                    if (SystemConst.systemID==1){
                        loginSQR_XJ();

                    }else {
                        loginSQR();

                    }
                }

                break;
            case R.id.btn_submit_outline:
                if (type == 1) {
                    App.setIsOnline(false);
                    doOutLineLogin();

//                    Intent intent = new Intent(context,ActWebView.class);
//                    intent.putExtra("url","http://222.222.49.34:6677/");
//                    startActivity(intent);
                } else {
                    doToast("该功能不对社会人员开放!");
                }
                break;
        }


    }
        int id=0;

    NotificationManager notificationManager;
    private void notificaitonMsg(){


        DbManager dbManager = x.getDb(App.getDaoConfig());


        for (int i=0;i<10;i++){

            CommMsgSave item =new CommMsgSave();
            item.setContent("content"+i);
            item.setSender("sender"+i);
            item.setTitle("title"+i);
            try {
                dbManager.save(item);
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
        try {
            List<CommMsgSave> items =dbManager.findAll(CommMsgSave.class);
            com.orhanobut.logger.Logger.d("查到的数据"+items.size());
        } catch (DbException e) {
            e.printStackTrace();
        }
        if (notificationManager==null){
            notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        }
        Intent mainIntent = new Intent(context, com.szmz.home.ActMsgDetail.class);
       PendingIntent mainPendingIntent = PendingIntent.getActivity(context, 0, mainIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notificationCompat = new NotificationCompat.Builder(App.getInstance().getApplicationContext())
//                .setSmallIcon(R.drawable.logo_s)
                .setContentText("aaa" +
                        "")
                .setContentTitle("title")
                .setAutoCancel(true)
                .setContentIntent(mainPendingIntent)
                .setGroup("zhmz_qyx")
                .setGroupSummary(true);


        Notification notification = notificationCompat.build();
        notificationManager.notify(id, notification);
        id++;
    }

    private void doOutLineLogin() {
        if (!doCheck()) {
            return;
        }
        try {
//            List<User> all1 = dbManager.selector(User.class).findAll();
            WhereBuilder builder =  WhereBuilder.b();
            builder.and("userName", "=", etUser.getText().toString().trim());
            builder.and("accountJZ","!=",null);
            List<User> all = dbManager.selector(User.class).where
                    (builder).findAll();
            if (all != null && all.size() > 0) {
                if (etPW.getText().toString().trim().equals(all.get(0).getPw())) {
                    if (all.get(0).getIdJZ() == null || all.get(0).getIdJZ().equals("")) {
                        doToast("离线登录失败");
                        return;
                    } else {
                        doToast("登录成功!");
                        App.getInstance().login(all.get(0));
                        trans(ActMainJZ.class);
                    }
                } else {
                    doToast("请输入正确的用户名密码!");
                    return;
                }

            } else {
                doToast("请输入正确的用户名密码!");
                return;
            }
        } catch (DbException e) {
            e.printStackTrace();
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
                    try {
                        dbManager.saveOrUpdate(user);
                    } catch (DbException e) {
                        e.printStackTrace();
                    }

                    trans(ActMain.class);
                }


            }
        }, true);
        apiUtil.excute();
    }


    private void loginSQR() {
        LoginSQR_Req sqr_req = new LoginSQR_Req(etUser.getText().toString().trim(), etPW.getText().toString().trim());
        Call<LoginSQR_Res> call;

            call = App.getApiProxyComSQR().loginSQR(sqr_req);


        ApiUtil<LoginSQR_Res> apiUtil = new ApiUtil<>(context, call, new SimpleApiListener<LoginSQR_Res>() {

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
                user.setSex(userSQR.getSEX() + "");

                user.setAccountJZ(etUser.getText().toString());
                user.setAccountYZS(etUser.getText().toString());

                App.getInstance().login(user);
                //保存用户名密码
                SystemEnv.saveUserName(etUser.getText().toString().trim());
                SystemEnv.saveUserPw(etPW.getText().toString().trim());
                SystemEnv.saveLoginType(type);

                trans(ActMain.class);
            }
        }, true);
        apiUtil.excute();
    }

    private void loginSQR_XJ() {
        LoginSQR_Req sqr_req = new LoginSQR_Req(etUser.getText().toString().trim(), etPW.getText().toString().trim());
        Call<LoginSQR_XJ_Res> call;
            call = App.getApiProxyJZ().loginSQR_XJ(sqr_req);


        ApiUtil<LoginSQR_XJ_Res> apiUtil = new ApiUtil<>(context, call, new SimpleApiListener<LoginSQR_XJ_Res>() {

            @Override
            public void doSuccess(LoginSQR_XJ_Res result) {
                super.doSuccess(result);

                UserSQR_XJ userSQR = result.Result.get(0);
                User user = new User();
                user.setUserName(etUser.getText().toString().trim());
                user.setPw(etPW.getText().toString().trim());
                user.setType(type);

                user.setRealName(userSQR.getRealName());
//                user.setEmail(userSQR.get());
                user.setAdderss(userSQR.getAddress());
                user.setIdCode(userSQR.getIdcard());
                user.setSex(userSQR.getSex() + "");

                user.setAccountJZ(etUser.getText().toString());
                user.setAccountYZS(etUser.getText().toString());

                App.getInstance().login(user);
                //保存用户名密码
                SystemEnv.saveUserName(etUser.getText().toString().trim());
                SystemEnv.saveUserPw(etPW.getText().toString().trim());
                SystemEnv.saveLoginType(type);

                trans(ActMain.class);
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
            if (notificationManager!=null)
                notificationManager.cancelAll();
            App.exit();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (rbWoker.isChecked()) {
            type = 1;
        } else {
            type = 0;
        }

    }

    private void getIPID() {

        String ips =SystemConst.IP_JZ + "," + SystemConst.IP_YZS + "," + SystemConst.IP_HD+","+SystemConst.IP_SM;
        final Comm_ipid_req req = new Comm_ipid_req(ips);

        Call<Comm_ipid_res> call = App.getApiProxyCom().getIPSID(req);

        ApiUtil<Comm_ipid_res> apiUtil = new ApiUtil<>(context, call, new SimpleApiListener<Comm_ipid_res>() {
            @Override
            public void doSuccess(Comm_ipid_res result) {
                super.doSuccess(result);
                List<Comm_ipid_res.ResultBean> items = new ArrayList<>();

                if (result != null)
                    items = result.Result;

                if (items != null && items.size() > 0) {
                    for (int i = 0; i < items.size(); i++) {
                        Comm_ipid_res.ResultBean item = items.get(i);
                        if (item.getIp().equals(SystemConst.IP_JZ)) {
                            SystemConst.SystemID_JZ = item.getResult();
                        } else if (item.getIp().equals(SystemConst.IP_YZS)) {
                            SystemConst.SystemID_YZS = item.getResult();
                        } else if (item.getIp().equals(SystemConst.IP_HD)) {
                            SystemConst.SystemID_SH = item.getResult();
                        }else if (item.getIp().equals(SystemConst.IP_SM)){
                            SystemConst.SystemID_SM = item.getResult();

                        }
                    }
                }
                //{"Result":[{"result":"emRzaGJ6MTUwNzk2MTQyNjE1Nw==","ip":"222.222.49.34:9095"},{"result":"eWxienl6czE1MDgyMjQyMzc2MzE=","ip":"222.222.49.34:8088"},{"result":"","ip":"zhongkehengyun.com:8081"}],"TotalNum":0,"Error":{"ErrorMessage":"信息获取成功","ErrorCode":0}}


            }
        }, true);

        apiUtil.excute();

    }
}
