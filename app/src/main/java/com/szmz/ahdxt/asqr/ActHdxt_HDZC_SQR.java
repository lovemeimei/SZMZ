package com.szmz.ahdxt.asqr;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.szmz.ActBase;
import com.szmz.R;

import butterknife.BindView;
import butterknife.OnClick;

public class ActHdxt_HDZC_SQR extends ActBase {


    @BindView(R.id.hdzcLayout)
    LinearLayout hdzcLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_hdxt__hdzc__sqr;
    }


    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("核对政策");
    }

    @OnClick({
            R.id.hdzcLayout
    })
    public void doClick(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.hdzcLayout:
                intent = new Intent(this, ActHdzc_List.class);
                startActivity(intent);
                break;


        }
    }
}
