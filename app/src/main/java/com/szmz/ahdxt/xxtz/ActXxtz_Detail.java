package com.szmz.ahdxt.xxtz;

import android.view.View;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.entity.response.HD_XXTZ;
import com.szmz.widget.MyLayoutView;

import butterknife.BindView;

/**
 * 核对系统工作人员首页模块详细
 */
public class ActXxtz_Detail extends ActBase {


    @BindView(R.id.tv_xxtz_pcmc)
    MyLayoutView tvName;
    @BindView(R.id.tv_xxtz_ywlx)
    MyLayoutView tvType;
    @BindView(R.id.tv_xxtz_wtsj)
    MyLayoutView tvTime;
    @BindView(R.id.tv_xxtz_jbjdmc)
    MyLayoutView tvPoint;
    @BindView(R.id.tv_xxtz_cssj)
    MyLayoutView tvOutTime;

    HD_XXTZ.ReaultBean item = null;
    int type = 0;

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("详细信息");
        type = getIntent().getIntExtra("Type", 0);
        item = (HD_XXTZ.ReaultBean) getIntent().getSerializableExtra("item");

        switch (type) {
            case 1:
                tvOutTime.setVisibility(View.GONE);
                break;
            case 2:
                tvOutTime.setVisibility(View.GONE);
                break;
            case 3:
                break;
            case 4:
                tvOutTime.setVisibility(View.GONE);
                tvPoint.setVisibility(View.GONE);
                break;
        }

        tvName.doSetContent(item.getBatchName());
        tvType.doSetContent(item.getBizCategory());
        tvPoint.doSetContent(item.getDealNode());
        tvTime.doSetContent(item.getEntrustTime());
        tvOutTime.doSetContent(item.getOutTime());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_xxtz__detail;
    }
}
