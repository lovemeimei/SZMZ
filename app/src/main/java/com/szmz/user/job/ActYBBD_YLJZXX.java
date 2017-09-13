package com.szmz.user.job;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.szmz.ActBase;
import com.szmz.R;

/**
 * 表单医疗救助信息
 */
public class ActYBBD_YLJZXX extends ActBase {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ybbd__yljzxx;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("住院医疗救助信息");
    }
}
