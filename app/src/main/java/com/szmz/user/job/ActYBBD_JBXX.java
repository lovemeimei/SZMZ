package com.szmz.user.job;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.szmz.ActBase;
import com.szmz.R;

/**
 * 表单基本信息
 */
public class ActYBBD_JBXX extends ActBase {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ybbd__jbxx;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("基本信息");
    }
}
