package com.szmz;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dalong.library.listener.OnItemClickListener;
import com.dalong.library.listener.OnItemSelectedListener;
import com.dalong.library.view.LoopRotarySwitchView;
import com.szmz.ahdxt.ActHdxtMain;
import com.szmz.ahdxt.asqr.ActHdxtMainSQR;
import com.szmz.ayljzxt.ActMainYLJZ;
import com.szmz.ayljzxt.ActMainYLJZ2;
import com.szmz.entity.User;
import com.szmz.entity.request.JZ_Comm_Req;
import com.szmz.entity.response.JZ_GetUserInfo;
import com.szmz.more.ActBindPhone_Worker;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

public class ActMain extends ActBase {


    @BindView(R.id.mLoopRotarySwitchView)
    LoopRotarySwitchView loopRotarySwitchView;

    @BindView(R.id.tv_name)
    TextView tvName;

    private int type = 1;

    @Override
    protected void initUI() {
        super.initUI();


        Log.e("abcccccccccccc", Build.BRAND);
//        type = App.getInstance().getLoginUser().getType();

        type = getIntent().getIntExtra("type",1);

        loopRotarySwitchView
                .setR(500)//设置R的大小
                .setAutoRotation(false);

        loopRotarySwitchView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void selected(int item, View view) {
                switch (item) {
                    case 0:
                        tvName.setText("社会救助系统");
                        break;
                    case 1:
                        tvName.setText("医疗救助一站式");
                        break;
                    case 2:
                        tvName.setText("居民经济核对系统");
                        break;
                }
            }
        });
        loopRotarySwitchView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int item, View view) {
                switch (item) {
                    case 0:
                        if (type == 1) {
                            loginJZXT();
                        } else {
                            trans(ActMainJZ2.class);
                        }

                        break;
                    case 1:
                        if (type == 1) {
                            trans(ActMainYLJZ.class);
                        } else {
                            trans(ActMainYLJZ2.class);
                        }
                        break;
                    case 2:
                        if (type == 1) {
                            trans(ActHdxtMain.class);
                        } else {
                            trans(ActHdxtMainSQR.class);
                        }

                        break;
                }
            }
        });

        if (TextUtils.isEmpty(App.getInstance().getLoginUser().getPhone())){
            trans(ActBindPhone_Worker.class);
        }
    }

    @OnClick(R.id.iv_user)
    public void doClick(View v) {
        trans(ActCommUser.class);
    }

    private void loginJZXT() {
//personalId:8a8ab0b246dc81120146dc8181950052
        //"id":"8a8ab0b246dc81120146dc8181950052"
        JZ_Comm_Req req = new JZ_Comm_Req(App.getInstance().getLoginUser().getAccountJZ());
//        JZ_Comm_Req req = new JZ_Comm_Req("admin");

        Call<JZ_GetUserInfo> call = App.getApiProxyJZ().loginJZ(req);

        ApiUtil<JZ_GetUserInfo> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<JZ_GetUserInfo>() {
            @Override
            public void doSuccess(JZ_GetUserInfo result) {
              String id=  result.Result.get(0).getId();
                if (TextUtils.isEmpty(id)){
                    doToast("服务器发生错误");
                    return;
                }
                User user = App.getInstance().getLoginUser();
                user.setIdJZ(result.Result.get(0).getId());
                App.getInstance().login(user);
                trans(ActMainJZ.class);
            }
        }, true);

        apiUtil.excute();

    }

    private void loginHD() {

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
    protected int getLayoutId() {
        return R.layout.activity_act_main;
    }
}
