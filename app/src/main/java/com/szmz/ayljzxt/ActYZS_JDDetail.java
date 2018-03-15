package com.szmz.ayljzxt;

import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.szmz.ActBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.request.YZSSQR_JD_Detail_Req;
import com.szmz.entity.response.YZSSQR_jd_Res;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.widget.MyLayoutView;
import com.szmz.widget.StepProgressView2;

import butterknife.BindView;
import retrofit2.Call;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/29 0029下午 3:30
 */

public class ActYZS_JDDetail extends ActBase {

    @BindView(R.id.stepView)
    StepProgressView2 stepView;

    @BindView(R.id.ll_stepview)
    LinearLayout llStepView;
    @BindView(R.id.tv_yzs_history_1)
    MyLayoutView view1;
    @BindView(R.id.tv_yzs_history_2)
    MyLayoutView view2;
    @BindView(R.id.tv_yzs_history_3)
    MyLayoutView view3;
    @BindView(R.id.tv_yzs_history_4)
    MyLayoutView view4;
    @BindView(R.id.tv_yzs_history_5)
    MyLayoutView view5;
    @BindView(R.id.tv_yzs_history_6)
    MyLayoutView view6;
    @BindView(R.id.tv_yzs_history_8)
    MyLayoutView view8;

    @BindView(R.id.tv_yzs_history_9)
    MyLayoutView view9;
    private String id;
    private String type;
    private YZSSQR_jd_Res.ResultBean item;

    @BindView(R.id.tv_yzs_history_xb)
    MyLayoutView viewsex;

    @BindView(R.id.tv_yzs_history_cblx)
    MyLayoutView viewcblx;
    @BindView(R.id.tv_yzs_history_zylx)
    MyLayoutView viewzylx;
    @BindView(R.id.tv_yzs_history_jzjb)
    MyLayoutView viewjzjb;
    @BindView(R.id.tv_yzs_history_jzjg)
    MyLayoutView viewjzjg;
    @BindView(R.id.tv_yzs_history_jbmc)
    MyLayoutView viewjbmc;
    @BindView(R.id.tv_yzs_history_bcje)
    MyLayoutView viewbcje;
    @BindView(R.id.tv_yzs_history_qfx)
    MyLayoutView viewqfx;
    @BindView(R.id.tv_yzs_history_dbzf)
    MyLayoutView viewdbzf;
    @BindView(R.id.tv_yzs_history_mzjzfy)
    MyLayoutView viewmzjzfy;
    @BindView(R.id.tv_yzs_history_jzbl)
    MyLayoutView viewjzbl;
    @BindView(R.id.tv_yzs_history_jzje)
    MyLayoutView viewjzje;
    @BindView(R.id.tv_yzs_history_brzf)
    MyLayoutView viewbrzf;
    @BindView(R.id.tv_yzs_history_ms)
    MyLayoutView viewms;

    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;

    @BindView(R.id.ll_rb1)
    LinearLayout llRB1;
    @BindView(R.id.ll_rb2)
    LinearLayout llRB2;


    private String[] stepString = {"审批中"};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_yzs_jd_detail2;
    }

    @Override
    protected void initUI() {
        super.initUI();
        id = getIntent().getStringExtra("id");
        type = getIntent().getStringExtra("type");
        setLeftVisible(true);
        setTitle("进度信息");

        getInfo();

        rb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    llRB1.setVisibility(View.VISIBLE);
                    llRB2.setVisibility(View.GONE);
                }

            }
        });
        rb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    llRB1.setVisibility(View.GONE);
                    llRB2.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    private void getInfo() {

        YZSSQR_JD_Detail_Req req = new YZSSQR_JD_Detail_Req(id, type);

        Call<YZSSQR_jd_Res> call = App.getApiProxyYZS().getYZS_jdDetail_SQR(req);

        ApiUtil<YZSSQR_jd_Res> apiUtil = new ApiUtil<>(context, call, new SimpleApiListener<YZSSQR_jd_Res>() {
            @Override
            public void doSuccess(YZSSQR_jd_Res result) {
                super.doSuccess(result);
                if (result != null && result.Result != null && result.Result.size() > 0)
                    item = result.Result.get(0);
                if (item != null)
                    setInfo();
            }
        }, true);

        apiUtil.excute();

    }

    private void setInfo() {
        String all = item.getAllNode();
        if (TextUtils.isEmpty(all) || all == null || all.equals("null")) {
            llStepView.setVisibility(View.GONE);
        } else {
            llStepView.setVisibility(View.VISIBLE);
            stepString = all.split(",");
            stepView.setStepCounts(stepString.length);
            stepView.setStepDesc(stepString);

            String c = item.getCurrentNode();
            for (int i = 0; i < stepString.length; i++) {
                if (stepString[i].equals(c)) {
//                    stepView.setCurStepIndex(0);
                    stepView.setCurStepIndex(i);
                }
            }

            if (item.getFLOW_RESULT().equals("1")) {
                stepView.setCurStaus(true);
            } else {
                stepView.setCurStaus(false);
            }
            stepView.invalidate();
        }

        view1.doSetContent(item.getNAME());
        view2.doSetContent(item.getCardID());
        view3.doSetContent(item.getTypeName());
        view4.doSetContent(item.getFLOW_RESULT_NAME());
        view5.doSetContent(item.getApprovalOpinion());
        view6.doSetContent(item.getApplicationNo());
        view8.doSetContent(item.getIN_HOSPITAL_DATE());
        view9.doSetContent(item.getLEAVE_HOSPITAL_DATE());
        viewsex.doSetContent(item.getSexName());
        viewjzjg.doSetContent(item.getTREATMENT_NAME());
        viewjbmc.doSetContent(item.getDESEASE_NAME());

        viewcblx.doSetContent(item.getINSURE_CATEGORY());
        viewzylx.doSetContent(item.getINPATIENT_TYPE_NAME());
        viewjzjb.doSetContent(item.getTREATMENT_LEAVEL());

        viewbcje.doSetContent(item.getCOMPENCATE_MONEY());
        viewqfx.doSetContent(item.getSTART_PAY_MONEY());
        viewdbzf.doSetContent(item.getDISEASE_PAY_MONEY());
        viewmzjzfy.doSetContent(item.getSALVATION_MONEY());
        if (TextUtils.isEmpty(item.getRESCUE_PERCENT())) {

            viewjzbl.doSetContent("0%");
        } else {

            viewjzbl.doSetContent(Double.valueOf(item.getRESCUE_PERCENT()) * 100 + "%");
        }
        viewjzje.doSetContent(item.getREAL_RESCUE_MONEY());
        viewbrzf.doSetContent(item.getSELF_PAY_MONEY());
        viewms.doSetContent(item.getREMARK());


    }

}
