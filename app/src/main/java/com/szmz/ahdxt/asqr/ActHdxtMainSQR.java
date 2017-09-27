package com.szmz.ahdxt.asqr;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.szmz.ActBase;
import com.szmz.R;

import butterknife.BindView;
import butterknife.OnClick;

public class ActHdxtMainSQR extends ActBase {


    @BindView(R.id.grcxLayout)
    LinearLayout grcxLayout;
    @BindView(R.id.xxtzLayout)
    LinearLayout xxtzLayout;
    @BindView(R.id.hdzcLayout)
    LinearLayout hdzcLayout;
    @BindView(R.id.home)
    ImageView home;
    @BindView(R.id.person)
    ImageView person;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_hdxt_main_sqr;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setTitle("核对系统");
    }

    @OnClick({
            R.id.grcxLayout, R.id.hdzcLayout, R.id.xxtzLayout, R.id.person
    })
    public void doClick(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.grcxLayout:
                intent = new Intent(this, ActHdxt_GRCX_SQR.class);
                intent.putExtra("Type", 1);
                startActivity(intent);
                break;
            case R.id.xxtzLayout:
                intent = new Intent(this, ActHdxt_XXTZ_SQR.class);
                intent.putExtra("Type", 2);
                startActivity(intent);
                break;
            case R.id.hdzcLayout:
                intent = new Intent(this, ActHdxt_HDZC_SQR.class);
                intent.putExtra("Type", 3);
                startActivity(intent);
                break;

            case R.id.person:
                doToast("个人中心");
                break;
        }
    }

}
