package com.szmz.ahdxt.asqr;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.szmz.ActBase;
import com.szmz.R;

import butterknife.BindView;
import butterknife.OnClick;

public class ActHdxt_GRCX_SQR extends ActBase {

    @BindView(R.id.jdckLayout)
    LinearLayout jdckLayout;
    @BindView(R.id.sqxxckLayout)
    LinearLayout sqxxckLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_hdxt__grcx__sqr;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("个人查询");
    }

    @OnClick({
            R.id.jdckLayout, R.id.sqxxckLayout
    })
    public void doClick(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.jdckLayout:
                intent = new Intent(this, ActGrcx_JDCK_List.class);
                startActivity(intent);
                break;
            case R.id.sqxxckLayout:
                intent = new Intent(this, ActGrcx_SQXXCK_List.class);
                startActivity(intent);
                break;


        }
    }

}
