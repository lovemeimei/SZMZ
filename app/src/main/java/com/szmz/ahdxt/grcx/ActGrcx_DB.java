package com.szmz.ahdxt.grcx;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.entity.HdxtGrcxInfo;
import com.szmz.widget.MyLayoutView;

import butterknife.BindView;

/**
 * 我的待办详细
 */
public class ActGrcx_DB extends ActBase {


    @BindView(R.id.pcmcView)
    MyLayoutView pcmcView;
    @BindView(R.id.ywlxView)
    MyLayoutView ywlxView;
    @BindView(R.id.wtsjView)
    MyLayoutView wtsjView;
    @BindView(R.id.hzsjView)
    MyLayoutView hzsjView;
    @BindView(R.id.ztsView)
    MyLayoutView ztsView;
    @BindView(R.id.ycltsView)
    MyLayoutView ycltsView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_grcx__db;
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
        hzsjView.doSetContent(info.getHzsj());
        ztsView.doSetContent(info.getZts());
        ycltsView.doSetContent(info.getYclts());

    }
}
