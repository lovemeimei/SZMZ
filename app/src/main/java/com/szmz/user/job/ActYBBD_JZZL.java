package com.szmz.user.job;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.szmz.ActBase;
import com.szmz.R;

/**
 * 救助资料
 */
public class ActYBBD_JZZL extends ActBase {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ybbd__jzzl;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("救助资料");
    }
}
