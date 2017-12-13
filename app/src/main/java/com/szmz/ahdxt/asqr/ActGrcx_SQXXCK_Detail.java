package com.szmz.ahdxt.asqr;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.entity.HdxtGrcxInfo;
import com.szmz.widget.MyLayoutView;

import butterknife.BindView;

public class ActGrcx_SQXXCK_Detail extends ActBase {


    @BindView(R.id.xmView)
    MyLayoutView xmView;
    @BindView(R.id.sfzhView)
    MyLayoutView sfzhView;
    @BindView(R.id.sqlbView)
    MyLayoutView sqlbView;
    @BindView(R.id.wtsjView)
    MyLayoutView wtsjView;
    HdxtGrcxInfo info;
    @BindView(R.id.xbView)
    MyLayoutView xbView;
    @BindView(R.id.sqpcView)
    MyLayoutView sqpcView;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_grcx__sqxxck__detail;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("详细信息");
        info = (HdxtGrcxInfo) getIntent().getSerializableExtra("HdxtGrcxInfo");
        sqpcView.doSetContent(info.getBatchName());
        xmView.doSetContent(info.getApplyName());
        sfzhView.doSetContent(info.getIdCardNo());
        sqlbView.doSetContent(info.getBizCategory());
        wtsjView.doSetContent(info.getEntrustTime());
        xbView.doSetContent(info.getSex());
    }


}
