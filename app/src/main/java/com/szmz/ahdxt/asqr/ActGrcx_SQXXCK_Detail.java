package com.szmz.ahdxt.asqr;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.widget.MyLayoutView;

import butterknife.BindView;

public class ActGrcx_SQXXCK_Detail extends ActBase {


    @BindView(R.id.xmView)
    MyLayoutView xmView;
    @BindView(R.id.xbView)
    MyLayoutView xbView;
    @BindView(R.id.sfzhView)
    MyLayoutView sfzhView;
    @BindView(R.id.sqlbView)
    MyLayoutView sqlbView;
    @BindView(R.id.wtsjView)
    MyLayoutView wtsjView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_grcx__sqxxck__detail;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("详细信息");
    }
}
