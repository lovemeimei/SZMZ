package com.szmz.ayljzxt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.szmz.ActBase;
import com.szmz.R;

public class ActUserMsg extends ActBase {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_user_msg;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("我的消息");
    }
}
