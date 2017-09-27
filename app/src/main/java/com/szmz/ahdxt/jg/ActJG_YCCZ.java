package com.szmz.ahdxt.jg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.szmz.ActBase;
import com.szmz.R;

public class ActJG_YCCZ extends ActBase {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_jg__yccz;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("异常操作详情");
    }


}
