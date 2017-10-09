package com.szmz.user.job;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.widget.MyLayoutView;

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
    MyLayoutView tvBH;
    @BindView(R.id.tv_bd_jbxx_cblx)
    MyLayoutView tvCBLX;
    @BindView(R.id.tv_bd_jbxx_xm)
    MyLayoutView tvXM;
    @BindView(R.id.tv_bd_jbxx_xb)
    MyLayoutView tvXB;
    @BindView(R.id.tv_bd_jbxx_jzlx)
    MyLayoutView tvJZLX;
    @BindView(R.id.tv_bd_jbxx_ryfl)
    MyLayoutView tvRYFL;
    @BindView(R.id.tv_bd_jbxx_sfzh)
    MyLayoutView tvSFZH;
    @BindView(R.id.tv_bd_jbxx_ylzh)
    MyLayoutView tvYLZH;
    @BindView(R.id.tv_bd_jbxx_jtzz)
    MyLayoutView tvJTZZ;
    @BindView(R.id.tv_bd_jbxx_ztnd)
    MyLayoutView tvZTND;

}
