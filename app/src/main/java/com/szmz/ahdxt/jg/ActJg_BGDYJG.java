package com.szmz.ahdxt.jg;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.entity.HD_JG_BGDY_RES;
import com.szmz.widget.MyLayoutView;

import butterknife.BindView;

/**
 * 报告打印监管
 */
public class ActJg_BGDYJG extends ActBase {

    @BindView(R.id.tv_jg_dybg_bh)
    MyLayoutView tvBH;
    @BindView(R.id.tv_jg_dybg_cs)
    MyLayoutView tvCS;
    @BindView(R.id.tv_jg_dybg_cjsj)
    MyLayoutView tvcjsj;
    @BindView(R.id.tv_jg_dybg_gxsj)
    MyLayoutView tvGXSJ;

    @BindView(R.id.tv_jg_dybg_dyr)
    MyLayoutView tvDRY;
    HD_JG_BGDY_RES.ResultBean item;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_jg__bgdyjg;
    }


    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("报告打印详情");

        item = (HD_JG_BGDY_RES.ResultBean) getIntent().getSerializableExtra("object");
        tvBH.doSetContent(item.getReportCode());
        tvCS.doSetContent(item.getPrintTimes() + "");
        tvDRY.doSetContent(item.getPrinter());
        tvcjsj.doSetContent(item.getCreateTime());
        tvGXSJ.doSetContent(item.getUpdateTime());
    }
}
