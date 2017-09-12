package com.szmz.user.job;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.szmz.ActBase;
import com.szmz.R;

/**
 * 表单审批区域
 */
public class ActYBBD_SPQY extends ActBase {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ybbd__spqy;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("审批区域");
    }
}
