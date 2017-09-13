package com.szmz.user.job;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.szmz.ActBase;
import com.szmz.R;

import butterknife.BindView;

/**
 * 表单基本信息
 */
public class ActYBBD_JBXX extends ActBase {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ybbd__jbxx;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("基本信息");

    }

    @BindView(R.id.tv_bd_jbxx_bh)
    TextView tvBH;
    @BindView(R.id.tv_bd_jbxx_cblx)
    TextView tvCBLX;
    @BindView(R.id.tv_bd_jbxx_xm)
    TextView tvXM;
    @BindView(R.id.tv_bd_jbxx_xb)
    TextView tvXB;
    @BindView(R.id.tv_bd_jbxx_jzlx)
    TextView tvJZLX;
    @BindView(R.id.tv_bd_jbxx_ryfl)
    TextView tvRYFL;
    @BindView(R.id.tv_bd_jbxx_sfzh)
    TextView tvSFZH;
    @BindView(R.id.tv_bd_jbxx_ylzh)
    TextView tvYLZH;
    @BindView(R.id.tv_bd_jbxx_jtzz)
    TextView tvJTZZ;
    @BindView(R.id.tv_bd_jbxx_ztnd)
    TextView tvZTND;

}
