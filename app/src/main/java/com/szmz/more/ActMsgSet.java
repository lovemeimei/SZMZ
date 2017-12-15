package com.szmz.more;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.szmz.ActBase;
import com.szmz.ActLogin;
import com.szmz.App;
import com.szmz.R;
import com.szmz.SystemEnv;

import butterknife.BindView;
import butterknife.OnClick;

public class ActMsgSet extends ActBase {

    MaterialDialog dialog = null;

    @BindView(R.id.tv_msg_type)
    TextView tvMsgType;
    @BindView(R.id.rb_sound)
    CheckBox cbSound;
    @BindView(R.id.cb_shake)
    CheckBox cbShake;
    @BindView(R.id.cb_mdr)
    CheckBox cbMDR;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_msg_set;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("设置");
        cbSound.setChecked(SystemEnv.getSetSound());
        cbShake.setChecked(SystemEnv.getSetShake());
        cbMDR.setChecked(SystemEnv.getSetMdr());

        cbSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SystemEnv.saveSetSound(isChecked);
            }
        });
        cbShake.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SystemEnv.saveSetShake(isChecked);
            }
        });
        cbMDR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SystemEnv.saveSetMDR(isChecked);
            }
        });

        boolean msgType = SystemEnv.getSetNewMsg();
        boolean msgSound = SystemEnv.getSetSound();
        boolean msgShake = SystemEnv.getSetShake();
        boolean msgMDR = SystemEnv.getSetMdr();
        if (msgType){
            tvMsgType.setText("已打开");
        }else {

            tvMsgType.setText("已关闭");
        }
        cbShake.setChecked(msgShake);
        cbSound.setChecked(msgSound);
        cbMDR.setChecked(msgMDR);

        tvMsgType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvMsgType.getText().toString().equals("已打开")){
                    tvMsgType.setText("已关闭");
                    SystemEnv.saveSetMsg(false);
                }else {
                    tvMsgType.setText("已打开");
                    SystemEnv.saveSetMsg(true);
                }
            }
        });

        cbShake.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SystemEnv.saveSetShake(isChecked);
            }
        });
        cbMDR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SystemEnv.saveSetMDR(isChecked);
            }
        });
        cbSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SystemEnv.saveSetSound(isChecked);
            }
        });
    }

    @OnClick({R.id.tv_exit})
    public void doClick(View v) {
        switch (v.getId()) {
            case R.id.tv_exit:

                dialog = new MaterialDialog.Builder(context)
                        .title("提示")
                        .content("确定要退出登录吗？")
                        .positiveText("确定")
                        .negativeText("取消")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                App.exit2();
                                trans(ActLogin.class);
                            }
                        })
                        .build();
                dialog.show();
                break;
        }
    }

}
