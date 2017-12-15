package com.szmz.ahdxt.grcx;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.entity.HdxtGrcxInfo;
import com.szmz.entity.response.HD_SearchDB_RES;
import com.szmz.widget.MyLayoutView;

import butterknife.BindView;

/**
 * 我的已办详细
 */
public class ActGrcx_YB extends ActBase {


    @BindView(R.id.pcmcView)
    MyLayoutView pcmcView;
    @BindView(R.id.ywlxView)
    MyLayoutView ywlxView;
    @BindView(R.id.wtsjView)
    MyLayoutView wtsjView;
    @BindView(R.id.cjsjView)
    MyLayoutView cjsjView;
    @BindView(R.id.ztsView)
    MyLayoutView ztsView;
    @BindView(R.id.blsjView)
    MyLayoutView blsjView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_grcx__yb;
    }

    private HD_SearchDB_RES.ResultBean info;

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("详细信息");
        info = (HD_SearchDB_RES.ResultBean) getIntent().getSerializableExtra("HdxtGrcxInfo");


        pcmcView.doSetContent(info.getBatchName());
        ywlxView.doSetContent(info.getBizCategory());
        cjsjView.doSetContent(info.getCreateTime());
        wtsjView.doSetContent(info.getEntrustTime());
        ztsView.doSetContent(info.getTotal()+"");
        blsjView.doSetContent("");
    }
}
