package com.szmz.user.check;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.szmz.ActBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.request.JZ_DC_req;
import com.szmz.entity.response.JZ_DC_Res;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.DatePickerUtil;
import com.szmz.widget.MyLayoutView;
import com.szmz.widget.MyLayoutView2;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

public class ActJZ_DC extends ActBase {

    @BindView(R.id.et_tj_time)
     TextView tvTime1;
    @BindView(R.id.et_tj_time2)
     TextView tvTime2;

    private String time1;
    private String time2;
    private TimePickerView pvTime;

    @BindView(R.id.tv_jz_dc_1)
    MyLayoutView2 myLayoutView1;
    @BindView(R.id.tv_jz_dc_2)
     MyLayoutView2 myLayoutView2;
    @BindView(R.id.tv_jz_dc_3)
     MyLayoutView2 myLayoutView3;
    @BindView(R.id.tv_jz_dc_4)
     MyLayoutView2 myLayoutView4;
    @BindView(R.id.tv_jz_dc_5)
     MyLayoutView2 myLayoutView5;

    private void initTimePicker() {
        pvTime = DatePickerUtil.initPicker(this, DatePickerUtil.yyyyMM);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_jz__dc;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setTitle("业务督察");
        setLeftVisible(true);
        setRightShow("查询");
        setRightVisible(true);

        initTimePicker();
        tvTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInfo();
            }
        });
    }

    @OnClick({R.id.et_tj_time2,R.id.et_tj_time})
    public void doClick(View view){
        switch (view.getId()){
            case R.id.et_tj_time:
                pvTime.show(tvTime1);
                break;
            case R.id.et_tj_time2:
                pvTime.show(tvTime2);
                break;
        }
    }

    private void getInfo(){

        time1 = tvTime1.getText().toString().trim();
        time2 = tvTime2.getText().toString().trim();

       if (TextUtils.isEmpty(time1)||TextUtils.isEmpty(time2)){
           doToast("请输入起止日期");
           return;
       }

        final JZ_DC_req req = new JZ_DC_req(getUser().getIdJZ(),time1,time2);

        Call<JZ_DC_Res> call = App.getApiProxyJZ().getJZ_dc(req);

        ApiUtil<JZ_DC_Res> apiUtil = new ApiUtil<>(context,call,new SimpleApiListener<JZ_DC_Res>(){
            @Override
            public void doSuccess(JZ_DC_Res result) {
                super.doSuccess(result);
                List<JZ_DC_Res.ResultBean> items = result.Result;
                if (items!=null && items.size()>0){
                    JZ_DC_Res.ResultBean item= items.get(0);
                    initInfo(item);
                }

            }
        },true);

        apiUtil.excute();
    }

    private void initInfo(JZ_DC_Res.ResultBean item){
        myLayoutView1.doSetContent(item.getCountHousehold());
        myLayoutView1.doSetContent2(item.getGradeHousehold());
        myLayoutView2.doSetContent2(item.getGradeDemocratic());
        myLayoutView2.doSetContent(item.getCountDemocratic());
        myLayoutView3.doSetContent2(item.getGradeCheckPublic());
        myLayoutView3.doSetContent(item.getCountCheckPublic());

        myLayoutView4.doSetContent2(item.getGradeHouseholdRandom());
        myLayoutView4.doSetContent(item.getCountHouseholdRandom());
        myLayoutView5.doSetContent2(item.getGradeApprovalPublic());
        myLayoutView5.doSetContent(item.getCountApprovalPublic());
    }
}
