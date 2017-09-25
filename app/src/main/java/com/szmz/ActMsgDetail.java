package com.szmz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;

/**
 * 消息详情
 */
public class ActMsgDetail extends ActBase {


    @BindView(R.id.tv_content)
    TextView tvContent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_base_detail;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);


        Intent intent = getIntent();
       String title= intent.getStringExtra("title");
       String content = intent.getStringExtra("content");
        setTitle(title);
    }
}
