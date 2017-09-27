package com.szmz.ahdxt.grcx;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.entity.HdxtGrcxInfo;
import com.szmz.widget.MyLayoutView;

import butterknife.BindView;

/**
 * 退回委托详细
 */
public class ActGrcx_TH extends ActBase {


    @BindView(R.id.pcmcView)
    MyLayoutView pcmcView;
    @BindView(R.id.ywlxView)
    MyLayoutView ywlxView;
    @BindView(R.id.wtsjView)
    MyLayoutView wtsjView;
    @BindView(R.id.hzView)
    MyLayoutView hzView;
    @BindView(R.id.thyyView)
    MyLayoutView thyyView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_grcx__th;
    }

    private HdxtGrcxInfo info;

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("详细信息");
        info = (HdxtGrcxInfo) getIntent().getSerializableExtra("HdxtGrcxInfo");

        pcmcView.doSetContent(info.getPcmc());
        ywlxView.doSetContent(info.getYwlx());
        wtsjView.doSetContent(info.getWtsj());
        hzView.doSetContent(info.getHzsj());
        thyyView.doSetContent(info.getThyy());

    }
}
