package com.szmz.ayljzxt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.szmz.ActBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.request.YZS_History_Detail_Req;
import com.szmz.entity.response.YZS_history_Res;
import com.szmz.entity.response.YZS_people_Res;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.widget.MyLayoutView;

import butterknife.BindView;
import retrofit2.Call;

public class ActYZS_people_detail extends ActBase {

    @BindView(R.id.tv_yzs_people_1)
    MyLayoutView view1;
    @BindView(R.id.tv_yzs_people_2)
    MyLayoutView view2;
    @BindView(R.id.tv_yzs_people_3)
    MyLayoutView view3;
    @BindView(R.id.tv_yzs_people_4)
    MyLayoutView view4;
    @BindView(R.id.tv_yzs_people_5)
    MyLayoutView view5;
    @BindView(R.id.tv_yzs_people_6)
    MyLayoutView view6;

    private String id="";

    private YZS_people_Res.ResultBean item=null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_yzs_people_detail;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("家庭信息");
        id = getIntent().getStringExtra("id");
        getInfo();
    }

    private void  getInfo(){
        YZS_History_Detail_Req req = new YZS_History_Detail_Req(id);

        Call<YZS_people_Res> call= App.getApiProxyYZS().getYZS_people_detail(req);

        ApiUtil<YZS_people_Res> apiUtil = new ApiUtil<>(context,call,new SimpleApiListener<YZS_people_Res>(){
            @Override
            public void doSuccess(YZS_people_Res result) {

                if (result.Result!=null && result.Result.size()>0){
                    item= result.Result.get(0);
                    if (item!=null)
                        setInfo();
                }

            }
        },true);

        apiUtil.excute();
    }

    private void setInfo(){

        view1.doSetContent(item.getNAME());
        view2.doSetContent(item.getREGIONALISM_NAME());
        view3.doSetContent(item.getIDCARD());
        view4.doSetContent(item.getAPPLY_DATE());
        view5.doSetContent(item.getID());
        view6.doSetContent(item.getADDRESS());
    }
}
