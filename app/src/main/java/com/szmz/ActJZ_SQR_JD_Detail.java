package com.szmz;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.szmz.entity.HdxtGrcxInfo;
import com.szmz.entity.Jz_sqr_jd;
import com.szmz.entity.Jz_sqr_jd_detail;
import com.szmz.entity.Jz_sqr_jd_xf_detail;
import com.szmz.entity.request.JZ_SQR_JD_DETAIL_RE;
import com.szmz.entity.request.JZ_SQR_JD_XF_DETAIL_RE;
import com.szmz.entity.response.JZ_SQR_JD_DETAIL_RES;
import com.szmz.entity.response.JZ_SQR_JD_XF_DETAIL_RES;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.widget.StepProgressView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;

public class ActJZ_SQR_JD_Detail extends ActBase {


    List<HdxtGrcxInfo.TotalNodeBean> totalNode = new ArrayList<>();
    @BindView(R.id.stepView)
    StepProgressView stepView;
    @BindView(R.id.xmTv)
    TextView xmTv;
    @BindView(R.id.sfzhTv)
    TextView sfzhTv;
    @BindView(R.id.jtzzTv)
    TextView jtzzTv;
    @BindView(R.id.lxdhTv)
    TextView lxdhTv;
    @BindView(R.id.sqrqTv)
    TextView sqrqTv;
    @BindView(R.id.ywlxTv)
    TextView ywlxTv;
    @BindView(R.id.blztTv)
    TextView blztTv;
    @BindView(R.id.sqyyTv)
    TextView sqyyTv;
    @BindView(R.id.layout1)
    LinearLayout layout1;
    @BindView(R.id.xfxfrTV)
    TextView xfxfrTV;
    @BindView(R.id.xfsfzhTv)
    TextView xfsfzhTv;
    @BindView(R.id.xfjtzzTv)
    TextView xfjtzzTv;
    @BindView(R.id.xflxdhTv)
    TextView xflxdhTv;
    @BindView(R.id.xfxfsjTv)
    TextView xfxfsjTv;
    @BindView(R.id.xfxffsTv)
    TextView xfxffsTv;
    @BindView(R.id.xfywlxTv)
    TextView xfywlxTv;
    @BindView(R.id.xfxfyyTv)
    TextView xfxfyyTv;
    @BindView(R.id.xflfblsjTv)
    TextView xflfblsjTv;
    @BindView(R.id.xfblqkTv)
    TextView xfblqkTv;
    @BindView(R.id.xfblzt)
    TextView xfblzt;
    @BindView(R.id.xfzbsjTv)
    TextView xfzbsjTv;
    @BindView(R.id.xfzbsmTv)
    TextView xfzbsmTv;
    @BindView(R.id.xfzbztTv)
    TextView xfzbztTv;
    @BindView(R.id.layout2)
    LinearLayout layout2;
    private Jz_sqr_jd info;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_jz__sqr__jd__detail;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setTitle("详细信息");
        setLeftVisible(true);
        info = (Jz_sqr_jd) getIntent().getSerializableExtra("JZ_SQR_JD");
        stepView.setStepCounts(2);
        HdxtGrcxInfo.TotalNodeBean item = new HdxtGrcxInfo.TotalNodeBean();
        item.setNode("审批中");
        totalNode.add(item);
        HdxtGrcxInfo.TotalNodeBean item2 = new HdxtGrcxInfo.TotalNodeBean();
        item2.setNode("审批完成");
        totalNode.add(item2);
        stepView.setStepDesc(totalNode);
        if (info != null) {
            if (info.getBpmStatus() != null) {
                if ("审批中".equals(info.getBpmStatus())||"审核中".equals(info.getBpmStatus())) {
                    stepView.setCurStepIndex(0);
                } else {
                    stepView.setCurStepIndex(1);
                }

            }
            if (info.getTypeName() != null && info.getTypeName().contains("信访")) {
                doGetXFInfo(info.getId());
            } else {
                doGetSQInfo(info.getId());
            }
        }


    }

    private void doGetXFInfo(String code) {
        JZ_SQR_JD_XF_DETAIL_RE request = new JZ_SQR_JD_XF_DETAIL_RE(code);
        Call<JZ_SQR_JD_XF_DETAIL_RES> call = App.getApiProxyJZ().get_JZ_SQR_JD_XF_DETAIL(request);
        ApiUtil<JZ_SQR_JD_XF_DETAIL_RES> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<JZ_SQR_JD_XF_DETAIL_RES>() {
            @Override
            public void doSuccess(JZ_SQR_JD_XF_DETAIL_RES result) {
                if (result != null) {
                    List<Jz_sqr_jd_xf_detail> result1 = result.Result;
                    if (result1 != null && result1.size() > 0) {
                        doSetValue2(result1.get(0));
                    }
                }

            }


        }, true);

        apiUtil.excute();

    }

    private void doGetSQInfo(String id) {
        final JZ_SQR_JD_DETAIL_RE request = new JZ_SQR_JD_DETAIL_RE(id);
        Call<JZ_SQR_JD_DETAIL_RES> call = App.getApiProxyJZ().get_JZ_SQR_JD_DETAIL(request);
        ApiUtil<JZ_SQR_JD_DETAIL_RES> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<JZ_SQR_JD_DETAIL_RES>() {
            @Override
            public void doSuccess(JZ_SQR_JD_DETAIL_RES result) {
                if (request != null) {
                    List<Jz_sqr_jd_detail> result1 = result.Result;
                    if (result1 != null && result1.size() > 0) {
                        doSetValue1(result1.get(0));
                    }
                }


            }


        }, true);

        apiUtil.excute();
    }

    private void doSetValue1(Jz_sqr_jd_detail item) {
        layout1.setVisibility(View.VISIBLE);
        layout2.setVisibility(View.GONE);
        xmTv.setText(item.getName());
        sfzhTv.setText(item.getIdcard());
        lxdhTv.setText(item.getPhone());
        sqyyTv.setText(item.getApplyReason());
        sqrqTv.setText(item.getApplyDate());
        jtzzTv.setText(item.getAddress());
        ywlxTv.setText(item.getSalvationType());
        blztTv.setText(item.getBpmStatus());
    }

    private void doSetValue2(Jz_sqr_jd_xf_detail item) {
        layout1.setVisibility(View.GONE);
        layout2.setVisibility(View.VISIBLE);
        xfxfrTV.setText(item.getVisitor());
        xfsfzhTv.setText(item.getIdcard());
        xflxdhTv.setText(item.getPhone());
        xfjtzzTv.setText(item.getAddress());
        xfxfsjTv.setText(item.getVdate());
        xfxffsTv.setText(item.getVmode());
        xfywlxTv.setText(item.getBstype());
        xfxfyyTv.setText(item.getVcause());
        xflfblsjTv.setText(item.getHdate());
        xfblqkTv.setText(item.getHandleInfo());
        xfblzt.setText(item.getHstatus());
        xfzbsjTv.setText(item.getTdate());
        xfzbsmTv.setText(item.getTinfo());
        xfzbztTv.setText(item.getTstatus());

    }


}
