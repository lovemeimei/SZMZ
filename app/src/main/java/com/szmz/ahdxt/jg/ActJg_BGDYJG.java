package com.szmz.ahdxt.jg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.szmz.ActBase;
import com.szmz.R;

import butterknife.BindView;

/**
 * 报告打印监管
 */
public class ActJg_BGDYJG extends ActBase {

    @BindView(R.id.tv_jg_dybg_bh)
    TextView tvBH;
    @BindView(R.id.tv_jg_dybg_cs)
    TextView tvCS;
    @BindView(R.id.tv_jg_dybg_cjsj)
    TextView tvcjsj;
    @BindView(R.id.tv_jg_dybg_gxsj)
    TextView tvGXSJ;

    @BindView(R.id.tv_jg_dybg_dyr)
    TextView tvDRY;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_jg__bgdyjg;
    }


    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("打印报告详情");
    }
}
