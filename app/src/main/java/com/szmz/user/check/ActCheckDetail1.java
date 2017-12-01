package com.szmz.user.check;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.widget.StepProgressView;

import org.w3c.dom.Text;

import butterknife.BindView;

public class ActCheckDetail1 extends ActBase {

    @BindView(R.id.stepView)
    StepProgressView stepView;

    @BindView(R.id.tv_check_xm)
    TextView tvXM;
    @BindView(R.id.tv_check_xb)
    TextView tvXB;
    @BindView(R.id.tv_check_jtzz)
    TextView tvJTZZ;
    @BindView(R.id.tv_check_lxdh)
    TextView tvDH;
    @BindView(R.id.tv_check_sfzh)
    TextView tvSFZH;
    @BindView(R.id.tv_check_lfsy)
    TextView tvSY;
    @BindView(R.id.tv_check_jtnr)
    TextView tvNR;
    @BindView(R.id.tv_check_ywlx)
    TextView tvYWLX;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_check_detail1;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("事项详情");
    }
}
