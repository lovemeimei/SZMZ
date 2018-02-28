package com.szmz.user.check;

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
import com.szmz.utils.DateUtil;
import com.szmz.widget.MyLayoutView2;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        pvTime = DatePickerUtil.initPicker(this, DatePickerUtil.yyyyMMdd);
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
        tvTime2.setText(DateUtil.getCurrentDay());
        tvTime1.setText(DateUtil.getDayBeforeMonth(1));
        getInfo();
        tvTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInfo();
            }
        });
    }


    @OnClick({R.id.et_tj_time2, R.id.et_tj_time})
    public void doClick(View view) {
        switch (view.getId()) {
            case R.id.et_tj_time:
                initTimePicker();


                pvTime.show(tvTime1);
                break;
            case R.id.et_tj_time2:
                initTimePicker();

                pvTime.show(tvTime2);
                break;
        }
    }

    private void getInfo() {

        time1 = tvTime1.getText().toString().trim();
        time2 = tvTime2.getText().toString().trim();


        if (TextUtils.isEmpty(time1) || TextUtils.isEmpty(time2)) {

        }
        DateFormat format = new SimpleDateFormat("yyyy-MM");
        try {
            Date date1 = format.parse(time1);
            Date date2 = format.parse(time2);
            if (date1.after(date2)) {
                doToast("起始月份不能大于终止月份");
                return;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        final JZ_DC_req req = new JZ_DC_req(getUser().getIdJZ(), time1, time2);

        Call<JZ_DC_Res> call = App.getApiProxyJZ().getJZ_dc(req);

        ApiUtil<JZ_DC_Res> apiUtil = new ApiUtil<>(context, call, new SimpleApiListener<JZ_DC_Res>() {
            @Override
            public void doSuccess(JZ_DC_Res result) {
                super.doSuccess(result);
                List<JZ_DC_Res.ResultBean> items = result.Result;
                if (items != null && items.size() > 0) {
                    JZ_DC_Res.ResultBean item = items.get(0);
                    initInfo(item);
                }

            }
        }, true);

        apiUtil.excute();
    }

    private void initInfo(JZ_DC_Res.ResultBean item) {
        myLayoutView1.doSetContent(item.getCountHousehold());
        myLayoutView1.doSetContent2(getValue(item.getGradeHousehold()));
        myLayoutView2.doSetContent2(getValue(item.getGradeDemocratic()));
        myLayoutView2.doSetContent(item.getCountDemocratic());
        myLayoutView3.doSetContent2(getValue(item.getGradeCheckPublic()));
        myLayoutView3.doSetContent(item.getCountCheckPublic());

        myLayoutView4.doSetContent2(getValue(item.getGradeHouseholdRandom()));
        myLayoutView4.doSetContent(item.getCountHouseholdRandom());

//        String str5 = item.getGradeApprovalPublic();
        myLayoutView5.doSetContent2(getValue(item.getGradeApprovalPublic()));
        myLayoutView5.doSetContent(item.getCountApprovalPublic());
    }

    public String getValue(String val) {
        if (val == null || val.equals("0"))
            return "0";
        double temValue = Double.valueOf(val) * 100;
        DecimalFormat df = new DecimalFormat(".00");
//        double aa =Math.round(temValue * 100*100) * 0.01d;
        String aa = df.format(temValue);
        return aa;
    }

}
