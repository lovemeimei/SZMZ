package com.szmz.ahdxt.asqr;

import android.widget.TextView;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.entity.HdxtGrcxInfo;
import com.szmz.widget.StepProgressView;

import butterknife.BindView;

public class ActGrcx_JDCK_Detail extends ActBase {


    @BindView(R.id.stepView)
    StepProgressView stepView;

    @BindView(R.id.tv_sqr)
    TextView tvSqr;
    @BindView(R.id.tv_sfzh)
    TextView tvSfzh;
    @BindView(R.id.tv_ywlx)
    TextView tvYwlx;
    @BindView(R.id.tv_wtsj)
    TextView tvWtsj;
    private HdxtGrcxInfo info;

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        info = (HdxtGrcxInfo) getIntent().getSerializableExtra("HdxtGrcxInfo");
        setTitle("进度详情");
        tvSqr.setText(info.getApplyName());
        tvSfzh.setText(info.getIdCardNo());
        tvYwlx.setText(info.getBizCategory());
        tvWtsj.setText(info.getEntrustTime());
        int size = info.getTotalNode().size();
        stepView.setStepCounts(size);
        stepView.setStepDesc(info.getTotalNode());
        for (int i = 0; i < size; i++) {
            if (info.getTotalNode().get(i).equals(info.getCurrentNode())) {
                stepView.setCurStepIndex(i);
            }
        }

        stepView.invalidate();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_grcx__jdck__detail;
    }


}
