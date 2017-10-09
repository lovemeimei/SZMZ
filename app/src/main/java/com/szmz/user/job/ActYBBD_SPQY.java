package com.szmz.user.job;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.widget.MyLayoutView;

import butterknife.BindView;

/**
 * 表单审批区域
 */
public class ActYBBD_SPQY extends ActBase {

    @BindView(R.id.tv_bd_jbxx_sj1)
    MyLayoutView tvSJ;
    @BindView(R.id.tv_bd_jbxx_sj2)
    MyLayoutView tvTime;
    @BindView(R.id.tv_bd_jbxx_czy)
    MyLayoutView tvCZY;
    @BindView(R.id.tv_bd_jbxx_xx)
    MyLayoutView tvMSG;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ybbd__spqy;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("审批区域");
    }
}
