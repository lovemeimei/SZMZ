package com.szmz.ahdxt.jg;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.entity.response.HD_JG_YWBL2;
import com.szmz.widget.MyLayoutView;

import butterknife.BindView;

/**
 * 业务办理
 */
public class ActJG_YWBL extends ActBase {


    @BindView(R.id.tv_jg_ywbl_xm)
    MyLayoutView tvXM;
    @BindView(R.id.tv_jg_ywbl_xb)
    MyLayoutView tvXB;
    @BindView(R.id.tv_jg_ywbl_sfzh)
    MyLayoutView tvSFZH;
    @BindView(R.id.tv_jg_ywbl_ywlx)
    MyLayoutView tvYWLX;
    @BindView(R.id.tv_jg_ywbl_wtsj)
    MyLayoutView tvWTSJ;


    HD_JG_YWBL2.ResultBean item;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_jg__ywbl;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);

        setTitle("详细信息");


        item = (HD_JG_YWBL2.ResultBean) getIntent().getSerializableExtra("item");

        tvXM.doSetContent(item.getApplyName());
        tvXB.doSetContent(item.getSex());
        tvSFZH.doSetContent(item.getIdCardNo());
        tvYWLX.doSetContent(item.getBizCategory());
        tvWTSJ.doSetContent(item.getEntrustTime());
    }


}
