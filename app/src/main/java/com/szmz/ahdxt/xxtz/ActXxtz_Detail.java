package com.szmz.ahdxt.xxtz;

import android.widget.TextView;

import com.szmz.ActBase;
import com.szmz.R;

import butterknife.BindView;

public class ActXxtz_Detail extends ActBase {

    @BindView(R.id.tv_content)
    TextView tvContent;

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("详细信息");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_xxtz__detail;
    }
}
