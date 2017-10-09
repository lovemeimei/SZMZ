package com.szmz.user.job;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.widget.MyLayoutView;

import butterknife.BindView;

/**
 * 表单医疗救助信息
 */
public class ActYBBD_YLJZXX extends ActBase {


    @BindView(R.id.tv_bd_jbxx_zyh)
    MyLayoutView tvZYH;

    @BindView(R.id.tv_bd_jbxx_lxdh)
    MyLayoutView tvLXDH;

    @BindView(R.id.tv_bd_jbxx_zylx)
    MyLayoutView tvZYLX;

    @BindView(R.id.tv_bd_jbxx_jzjg)
    MyLayoutView tvJZJG;

    @BindView(R.id.tv_bd_jbxx_jbmc)
    MyLayoutView tvJBMC;
    @BindView(R.id.tv_bd_jbxx_zzys)
    MyLayoutView tvZZYS;
    @BindView(R.id.tv_bd_jbxx_ryzd)
    MyLayoutView tvRYZD;
    @BindView(R.id.tv_bd_jbxx_jzjb)
    MyLayoutView tvJZJB;

    @BindView(R.id.tv_bd_jbxx_rysj)
    MyLayoutView tvRYSJ;
    @BindView(R.id.tv_bd_jbxx_cysj)
    MyLayoutView tvCYSJ;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ybbd__yljzxx;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("住院医疗救助信息");
    }
}
