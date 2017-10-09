package com.szmz.user.job;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.widget.MyLayoutView;

import butterknife.BindView;

public class ActYBBD_FYMX extends ActBase {

    @BindView(R.id.tv_bd_jbxx_bm)
    MyLayoutView tvBM;


    @BindView(R.id.tv_bd_jbxx_mc)
    MyLayoutView tvName;

    @BindView(R.id.tv_bd_jbxx_gg)
    MyLayoutView tvGG;
    @BindView(R.id.tv_bd_jbxx_dw)
    MyLayoutView tvDW;
    @BindView(R.id.tv_bd_jbxx_sl)
    MyLayoutView tvSL;
    @BindView(R.id.tv_bd_jbxx_dj)
    MyLayoutView tvDJ;

    @BindView(R.id.tv_bd_jbxx_zje)
    MyLayoutView tvZJE;

    @BindView(R.id.tv_bd_jbxx_yysj)
    MyLayoutView tvTime;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ybbd__fymx;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("费用明细");
    }
}
