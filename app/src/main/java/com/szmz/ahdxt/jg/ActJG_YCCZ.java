package com.szmz.ahdxt.jg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.entity.response.HD_JG_YCCL;
import com.szmz.widget.MyLayoutView;

import butterknife.BindView;

public class ActJG_YCCZ extends ActBase {


    @BindView(R.id.tv_jg_yccz_yjmc)
    MyLayoutView ycmc;
    @BindView(R.id.tv_jg_yccz_ywlx)
    MyLayoutView ywlx;
    @BindView(R.id.tv_jg_yccz_yjxx)
    MyLayoutView yjxx;
    @BindView(R.id.tv_jg_yccz_bjsj)
    MyLayoutView yjsj;
    @BindView(R.id.tv_jg_yccz_yjly)
    MyLayoutView yjly;


    HD_JG_YCCL.ResultBean item;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_jg__yccz;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("异常操作详情");


        item =(HD_JG_YCCL.ResultBean)getIntent().getSerializableExtra("item");

        ycmc.doSetContent(item.getWarningName());
        ywlx.doSetContent(item.getBizCategory());

        yjxx.doSetContent(item.getWarning());
        yjsj.doSetContent(item.getWarningTime());
        yjly.doSetContent(item.getSource());
    }




}
