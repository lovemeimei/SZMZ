package com.szmz.ayljzxt;

import com.szmz.ActBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.request.YZS_History_Detail_Req;
import com.szmz.entity.response.YZS_history_Res;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.widget.MyLayoutView;

import butterknife.BindView;
import retrofit2.Call;

public class ActYZSSQR_Histroy_Detail extends ActBase {

    String id="";
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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_yzs__histroy__detail;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("救助信息");
        id = getIntent().getStringExtra("id");
        getInfo();
    }

    private void  getInfo(){
        YZS_History_Detail_Req req = new YZS_History_Detail_Req(id);

        Call<YZS_history_Res> call= App.getApiProxyYZS().getYZS_History_detail_SQR(req);

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
    }
}
