package com.szmz.ahdxt.asqr;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.szmz.ActBase;
import com.szmz.R;

import butterknife.BindView;
import butterknife.OnClick;

public class ActHdxt_XXTZ_SQR extends ActBase {


    @BindView(R.id.sqztLayout)
    LinearLayout sqztLayout;
    @BindView(R.id.bgmxLayout)
    LinearLayout bgmxLayout;

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("消息通知");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_hdxt__xxtz__sqr;
    }

    @OnClick({
            R.id.sqztLayout, R.id.bgmxLayout
    })
    public void doClick(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.sqztLayout:
                intent = new Intent(this, ActXXTZ_List.class);
                intent.putExtra("Type", 1);
                startActivity(intent);
                break;
            case R.id.bgmxLayout:
                intent = new Intent(this, ActXXTZ_List.class);
                intent.putExtra("Type", 2);
                startActivity(intent);
                break;

        }
    }
}