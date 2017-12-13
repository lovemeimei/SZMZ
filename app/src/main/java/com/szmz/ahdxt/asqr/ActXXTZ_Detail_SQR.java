package com.szmz.ahdxt.asqr;

import android.view.View;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.entity.response.HD_XXTZ;
import com.szmz.widget.MyLayoutView;

import butterknife.BindView;

public class ActXXTZ_Detail_SQR extends ActBase {


    HD_XXTZ.ReaultBean info;
    int type = 0;
    @BindView(R.id.sqrView)
    MyLayoutView sqrView;
    @BindView(R.id.sfzhView)
    MyLayoutView sfzhView;
    @BindView(R.id.ywlxView)
    MyLayoutView ywlxView;
    @BindView(R.id.wtsjView)
    MyLayoutView wtsjView;
    @BindView(R.id.jbjdmcView)
    MyLayoutView jbjdmcView;
    @BindView(R.id.sqpcView)
    MyLayoutView sqpcView;

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("详细信息");
        info = (HD_XXTZ.ReaultBean) getIntent().getSerializableExtra("HD_XXTZ");
        type = getIntent().getIntExtra("Type", 0);
        sqpcView.doSetContent(info.getBatchName());
        sqrView.doSetContent(info.getApplyName());
        sfzhView.doSetContent(info.getIdCardNo());
        ywlxView.doSetContent(info.getBizCategory());
        wtsjView.doSetContent(info.getEntrustTime());
        if (type == 2) {
            jbjdmcView.setVisibility(View.GONE);
        } else if (type == 1) {
            jbjdmcView.doSetContent(info.getDealNode());
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_xxtz__detail2;
    }


}
