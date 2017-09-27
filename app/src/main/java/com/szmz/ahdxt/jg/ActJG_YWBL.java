package com.szmz.ahdxt.jg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.szmz.ActBase;
import com.szmz.R;

import butterknife.BindView;

/**
 * 业务办理
 */
public class ActJG_YWBL extends ActBase {


    @BindView(R.id.tv_jg_ywbl_xm)
    TextView tvXM;
    @BindView(R.id.tv_jg_ywbl_xb)
    TextView tvXB;
    @BindView(R.id.tv_jg_ywbl_sfzh)
    TextView tvSFZH;
    @BindView(R.id.tv_jg_ywbl_ywlx)
    TextView tvYWLX;
    @BindView(R.id.tv_jg_ywbl_wtsj)
    TextView tvWTSJ;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_jg__ywbl;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("监察详情");
    }


}
