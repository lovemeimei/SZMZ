package com.szmz.user.job;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.szmz.ActBase;
import com.szmz.R;

/**
 * 移交
 */
public class ActDBYijiao extends ActBase {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_yijiao;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setRightVisible(true);

        setTitle("移交");
        setRightShow("确定");
    }

}
