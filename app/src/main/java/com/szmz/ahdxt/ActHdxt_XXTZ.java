package com.szmz.ahdxt;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.ahdxt.grcx.ActGrcx_DataList;

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
        Intent intent = new Intent(this, ActGrcx_DataList.class);

        switch (v.getId()) {
            case R.id.hddclLayout:
                doToast("核对待处理");
                break;
            case R.id.fhdclLayout:
                doToast("复核待处理");
                break;
            case R.id.cstxLayout:
                doToast("超时提醒");
                break;
            case R.id.mgmdshtxLayout:
                doToast("敏感名单审核提醒");
                break;

        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_hdxt__xxtz;
    }


}
