package com.szmz;

import android.view.View;
import android.widget.TextView;

import com.dalong.library.listener.OnItemClickListener;
import com.dalong.library.listener.OnItemSelectedListener;
import com.dalong.library.view.LoopRotarySwitchView;
import com.szmz.ahdxt.ActHdxtMain;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

public class ActMain extends ActBase {


    @BindView(R.id.mLoopRotarySwitchView)
    LoopRotarySwitchView loopRotarySwitchView;

    @BindView(R.id.tv_name)
    TextView tvName;

    @Override
    protected void initUI() {
        super.initUI();
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
                        trans(ActMainJZ2.class);
                        break;
                    case 1:
                        doToast("开发中");
                        break;
                    case 2:
                        trans(ActHdxtMain.class);
                        break;
                }
            }
        });
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
