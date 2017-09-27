package com.szmz.ahdxt;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.ahdxt.xxtz.ActXxtz_List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 消息通知
 */
public class ActHdxt_XXTZ extends ActBase {

    @BindView(R.id.hddclLayout)
    LinearLayout hddclLayout;
    @BindView(R.id.fhdclLayout)
    LinearLayout fhdclLayout;
    @BindView(R.id.cstxLayout)
    LinearLayout cstxLayout;
    @BindView(R.id.mgmdshtxLayout)
    LinearLayout mgmdshtxLayout;

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("消息通知");
    }

    @OnClick({
            R.id.hddclLayout, R.id.fhdclLayout, R.id.cstxLayout, R.id.mgmdshtxLayout
    })
    public void doClick(View v) {
        Intent intent = new Intent(this, ActXxtz_List.class);

        switch (v.getId()) {
            case R.id.hddclLayout:
                intent.putExtra("Type", 1);
                startActivity(intent);
                break;
            case R.id.fhdclLayout:
                intent.putExtra("Type", 2);
                startActivity(intent);
                break;
            case R.id.cstxLayout:
                intent.putExtra("Type", 3);
                startActivity(intent);
                break;
            case R.id.mgmdshtxLayout:
                intent.putExtra("Type", 4);
                startActivity(intent);
                break;

        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_hdxt__xxtz;
    }


}
