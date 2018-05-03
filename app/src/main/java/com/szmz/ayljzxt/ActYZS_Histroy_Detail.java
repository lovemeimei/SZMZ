package com.szmz.ayljzxt;

import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.szmz.ActBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.request.YZSgzry_History_Detail_Req;
import com.szmz.entity.response.YZS_history_Res;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.widget.MyLayoutView;

import butterknife.BindView;
import retrofit2.Call;

public class ActYZS_Histroy_Detail extends ActBase {

    String id="";
    String type="";
    YZS_history_Res.ResultBean item = null;

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
    @BindView(R.id.tv_yzs_history_7)
    MyLayoutView view7;
    @BindView(R.id.tv_yzs_history_8)
    MyLayoutView view8;

    @BindView(R.id.tv_yzs_history_9)
    MyLayoutView view9;
    @BindView(R.id.tv_yzs_history_10)
    MyLayoutView view10;
    @BindView(R.id.tv_yzs_history_11)
    MyLayoutView view11;

    @BindView(R.id.tv_yzs_history_cblx)
    MyLayoutView viewcblx;
    @BindView(R.id.tv_yzs_history_zylx)
    MyLayoutView viewzylx;
    @BindView(R.id.tv_yzs_history_jzjb)
    MyLayoutView viewjzjb;

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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_yzs__histroy__detail;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("历史救助信息");
        id = getIntent().getStringExtra("id");
        type = getIntent().getStringExtra("type");
        getInfo();
        rb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    llRB1.setVisibility(View.VISIBLE);
                    llRB2.setVisibility(View.GONE);
                }

            }
        });
        rb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    llRB1.setVisibility(View.GONE);
                    llRB2.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    private void  getInfo(){
        YZSgzry_History_Detail_Req req = new YZSgzry_History_Detail_Req(id,type);

        Call<YZS_history_Res> call= App.getApiProxyYZS().getYZS_History_detail(req);

        ApiUtil<YZS_history_Res> apiUtil = new ApiUtil<>(context,call,new SimpleApiListener<YZS_history_Res>(){
            @Override
            public void doSuccess(YZS_history_Res result) {
                item= result.Result.get(0);
                if (item!=null)
                    setInfo();
            }
        },true);

        apiUtil.excute();
    }

    private void setInfo(){

        if ("1".equals(item.getTYPE())){
            //住院救助

        }else{
            view8.doSetTitle("门诊日期");
            view9.setVisibility(View.GONE);
            //门诊救助

        }

        view1.doSetContent(item.getNAME());
        view2.doSetContent(item.getSEX_NAME());
        view3.doSetContent(item.getIDCARD());
        view4.doSetContent(item.getADDRESS());
        view5.doSetContent(item.getRESCUE_CATEGORY_NAME());
        view6.doSetContent(item.getTREATMENT_NAME());
        view7.doSetContent(item.getDESEASE_NAME());
        view8.doSetContent(item.getIN_HOSPITAL_DATE());
        view9.doSetContent(item.getLEAVE_HOSPITAL_DATE());
        view10.doSetContent(item.getEXPENSE_MONEY());
        view11.doSetContent(item.getMED_REPAYABLE_MONEY());
        viewcblx.doSetContent(item.getINSURE_CATEGORY());
        viewzylx.doSetContent(item.getINPATIENT_TYPE_NAME());
        viewjzjb.doSetContent(item.getTREATMENT_LEAVEL());

        viewbcje.doSetContent(item.getCOMPENCATE_MONEY());
        viewqfx.doSetContent(item.getSTART_PAY_MONEY());
        viewdbzf.doSetContent(item.getDISEASE_PAY_MONEY());
        viewmzjzfy.doSetContent(item.getSALVATION_MONEY());
        if (TextUtils.isEmpty(item.getRESCUE_PERCENT())){

            viewjzbl.doSetContent("0%");
        }else {

            viewjzbl.doSetContent(Double.valueOf(item.getRESCUE_PERCENT())*100+"%");
        }
        viewjzje.doSetContent(item.getREAL_RESCUE_MONEY());
        viewbrzf.doSetContent(item.getSELF_PAY_MONEY());
        viewms.doSetContent(item.getREMARK());

    }
}
