package com.szmz.ahdxt.asqr;

import com.szmz.ActBase;
import com.szmz.R;

public class ActGrcx_JDCK_Detail extends ActBase {


    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("进度详情");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_grcx__jdck__detail;
    }
}
