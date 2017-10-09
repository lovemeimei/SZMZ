package com.szmz.user.job;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.widget.MyLayoutView;

import butterknife.BindView;

/**
 * 救助资料
 */
public class ActYBBD_JZZL extends ActBase {

    @BindView(R.id.tv_bd_jbxx_xh)
    MyLayoutView tvXH;
    @BindView(R.id.tv_bd_jbxx_fjmc)
    MyLayoutView tvFJMC;

    @BindView(R.id.tv_bd_jbxx_scjd)
    MyLayoutView tvSCJD;
    @BindView(R.id.tv_bd_jbxx_scrq)
    MyLayoutView tvSCRQ;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ybbd__jzzl;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("救助资料");
    }
}
