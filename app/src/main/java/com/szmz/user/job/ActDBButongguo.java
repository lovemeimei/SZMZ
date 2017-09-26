package com.szmz.user.job;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.szmz.ActBase;
import com.szmz.R;

/**
 * 不通过
 */
public class ActDBButongguo extends ActBase {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_butongguo;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("不通过");
        setRightShow("确定");
        setRightVisible(true);
    }


}
