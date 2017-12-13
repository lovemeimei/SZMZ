package com.szmz.ahdxt.asqr;

import android.widget.TextView;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.entity.HdxtGrcxInfo;
import com.szmz.widget.StepProgressView;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.tv_sqpc)
    TextView tvSqpc;
    private HdxtGrcxInfo info;

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        info = (HdxtGrcxInfo) getIntent().getSerializableExtra("HdxtGrcxInfo");
        setTitle("进度详情");
        tvSqpc.setText(info.getBatchName());
        tvSqr.setText(info.getApplyName());
        tvSfzh.setText(info.getIdCardNo());
        tvYwlx.setText(info.getBizCategory());
        tvWtsj.setText(info.getEntrustTime());
        int size = info.getTotalNode().size();
        stepView.setStepCounts(size > 1 ? size : 2);
        List<HdxtGrcxInfo.TotalNodeBean> list = new ArrayList<HdxtGrcxInfo.TotalNodeBean>();
        HdxtGrcxInfo.TotalNodeBean node = new HdxtGrcxInfo.TotalNodeBean();
        node.setNode("提交申请");
        list.add(node);
        list.addAll(info.getTotalNode());
        stepView.setStepDesc(size > 1 ? info.getTotalNode() : list);
        if (size > 1) {
            for (int i = 0; i < size; i++) {
                if (info.getTotalNode().get(i).getNode().equals(info.getCurrentNode())) {
                    stepView.setCurStepIndex(i);
                }

            }
        } else {
            for (int i = 0; i < 2; i++) {
                if (list.get(i).getNode().equals(info.getCurrentNode())) {
                    stepView.setCurStepIndex(i);
                }

            }

        }

        stepView.invalidate();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_grcx__jdck__detail;
    }


}
