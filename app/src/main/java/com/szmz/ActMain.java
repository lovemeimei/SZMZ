package com.szmz;

import android.support.v4.app.FragmentTabHost;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.dalong.library.listener.OnItemClickListener;
import com.dalong.library.listener.OnItemSelectedListener;
import com.dalong.library.listener.OnLoopViewTouchListener;
import com.dalong.library.view.LoopRotarySwitchView;
import com.szmz.fragment.FragmentHome;
import com.szmz.fragment.FragmentJob;
import com.szmz.fragment.FragmentSearch;
import com.szmz.fragment.FragmentStatistical;
import com.szmz.fragment.FragmentUser;
import com.szmz.utils.UIUtil;

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
                switch (item){
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
                switch (item){
                    case 0:
                        trans(ActMainJZ.class);
                        break;
                    case 1:
                        UIUtil.doToast("开发中");
                        break;
                    case 2:
                        UIUtil.doToast("开发中");
                        break;
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_main;
    }
}
