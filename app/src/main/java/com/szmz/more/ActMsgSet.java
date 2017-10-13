package com.szmz.more;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.szmz.ActBase;
import com.szmz.R;

public class ActMsgSet extends ActBase {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_msg_set;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("新消息通知");

    }

}
