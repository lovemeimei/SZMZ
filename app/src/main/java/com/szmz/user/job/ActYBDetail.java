package com.szmz.user.job;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.szmz.ActBase;
import com.szmz.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 已办详细
 */
public class ActYBDetail extends ActBase {

    @BindView(R.id.tv_time)
    TextView tvTime;

    @BindView(R.id.tv_cjd)
    TextView tvCJD;
    @BindView(R.id.tv_cjdry)
    TextView tvCJDRY;
    @BindView(R.id.tv_djd)
    TextView tvDJD;
    @BindView(R.id.tv_djdry)
    TextView tvDJDRY;
    @BindView(R.id.tv_ddsj)
    TextView tvDDSJ;
    @BindView(R.id.tv_ys)
    TextView tvYS;
    @BindView(R.id.tv_hd)
    TextView tvHD;
    @BindView(R.id.tv_xx)
    TextView tvXX;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ybdetail;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("已办业务");
    }

    @OnClick(R.id.tv_bd)
    public void doClick(View v) {
        trans(ActYBBDList.class);
    }

}
