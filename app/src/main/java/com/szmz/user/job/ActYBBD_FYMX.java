package com.szmz.user.job;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.szmz.ActBase;
import com.szmz.R;

public class ActYBBD_FYMX extends ActBase {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ybbd__fymx;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("费用明细");
    }
}
