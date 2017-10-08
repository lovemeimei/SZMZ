package com.szmz.ahdxt.tjfx;

import android.app.DatePickerDialog;
import android.graphics.Typeface;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.szmz.ActBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.response.HD_TJ_HDDX;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.Md5Util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * 核对对象总人数
 */
public class ActTjfx_HDBGZS extends ActBase {

    @BindView(R.id.barchart)
    BarChart mChart;

    protected Typeface mTfRegular;
    protected Typeface mTfLight;


    private List<String> types = new ArrayList<>();
    private List<String> citys = new ArrayList<>();


    @BindView(R.id.et_tj_xzqh)
    TextView tvXZQH;
    @BindView(R.id.et_tj_time)
    TextView tvStartTime;
    @BindView(R.id.et_tj_time2)
    TextView tvEndTime;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_tjfx__hddxzrs;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("核对报告总数");
        initBarChart();
        getInfo();
    }

    private void initBarChart() {
        mTfRegular = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        mTfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");

        mChart.getDescription().setEnabled(false);
        mChart.setPinchZoom(false);
        mChart.setDrawBarShadow(false);
        mChart.setHorizontalScrollBarEnabled(false);
        mChart.setVerticalScrollBarEnabled(false);
        mChart.setDrawGridBackground(false);
        mChart.setTouchEnabled(false);
        mChart.animateY(1500);
        mChart.setExtraBottomOffset(20f);


        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(true);
        l.setTypeface(mTfLight);
        l.setYOffset(0f);
        l.setXOffset(10f);
        l.setYEntrySpace(0f);
        l.setTextSize(12f);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setTypeface(mTfLight);
        xAxis.setGranularity(1f);
        xAxis.setTextSize(10f);
        xAxis.setLabelRotationAngle(25f);
        xAxis.setDrawGridLines(false);
        xAxis.setCenterAxisLabels(true);//标签居中显示
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                if (value>0 && value<=citys.size())
                    return  citys.get((int)value);
                return "";
            }
        });

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTypeface(mTfLight);
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(false);
        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        mChart.getAxisRight().setEnabled(false);
    }



    @OnClick({R.id.et_tj_time2, R.id.et_tj_time, R.id.et_tj_xzqh})
    public void doClick(View v) {

        switch (v.getId()) {
            case R.id.et_tj_time:
                Calendar cal = Calendar.getInstance();

                DatePickerDialog dialog1 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        tvStartTime.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
                dialog1.show();
                break;
            case R.id.et_tj_time2:
                Calendar ca2 = Calendar.getInstance();

                DatePickerDialog dialog2 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        tvEndTime.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, ca2.get(Calendar.YEAR), ca2.get(Calendar.MONTH), ca2.get(Calendar.DAY_OF_MONTH));
                dialog2.show();
                break;
            case R.id.et_tj_xzqh:

                break;
        }
    }

    void setmChartInfo(List<HD_TJ_HDDX.ResultBean> items) {

        if (items==null || items.size()==0)
            return;

        for (HD_TJ_HDDX.ResultBean item :items){
            if (!types.contains(item.getBizCategory()))
                types.add(item.getBizCategory());
            if (!citys.contains(item.getAreaName()))
                citys.add(item.getAreaName());
        }

        float groupSpace = 0.1f;
        float barSpace = 0.05f; // x3 DataSet
        float barWidth = 0.25f; // x3 DataSet
        barWidth =(float) (0.9f/types.size()-0.05);
        // (0.25 + 0.05) * 3 + 0.1 = 1.00 -> interval per "group"

//        List<ArrayList<BarEntry>> YValuesList = new ArrayList<>();

        BarData data = new BarData();
        for (int i=0;i<types.size();i++){
            ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();
            for (int j=0;j<citys.size();j++){
                yVals.add(new BarEntry(j, getValueByCity(items,citys.get(j))));
            }
//            YValuesList.add(yVals);

            BarDataSet barDataSet =new BarDataSet(yVals, types.get(i));
            data.addDataSet(barDataSet);
        }




//        for (int i = 0; i < citys.size(); i++) {
//
//            String tmpCity = citys.get(i);
//            List<HD_TJ_HDDX.ResultBean> tmpCityBean = new ArrayList<>();
//            for (HD_TJ_HDDX.ResultBean item :items){
//                if (!tmpCity.contains(item.getAreaName()))
//                   tmpCityBean.add(item);
//
//            }
//
//
//            yVals1.add(new BarEntry(i, (float) (Math.random() * randomMultiplier) + 100));
//            yVals2.add(new BarEntry(i, (float) (Math.random() * randomMultiplier) + 100));
//            yVals3.add(new BarEntry(i, (float) (Math.random() * randomMultiplier) + 100));
//            for (ArrayList<BarEntry> item:YValuesList){
//                item.add(new BarEntry(i,(float) ))
//            }
//        }



//        BarDataSet set1, set2, set3;
//        // create 4 DataSets
//        set1 = new BarDataSet(yVals1, xValueType[0]);
//        set1.setColor(Color.rgb(104, 241, 175));
//        set2 = new BarDataSet(yVals2, xValueType[1]);
//        set2.setColor(Color.rgb(164, 228, 251));
//        set3 = new BarDataSet(yVals3, xValueType[1]);
//        set3.setColor(Color.rgb(242, 247, 158));


//        BarData data = new BarData(set1, set2, set3);

        data.setValueFormatter(new LargeValueFormatter());
        data.setValueTypeface(mTfLight);

        mChart.setData(data);

        mChart.getXAxis().setAxisMinimum(0);
        mChart.getXAxis().setAxisMaximum(4.0f);
        mChart.getBarData().setBarWidth(barWidth);
        mChart.groupBars(0, groupSpace, barSpace);
        mChart.invalidate();
    }

    private float getValueByCity(List<HD_TJ_HDDX.ResultBean> items,String city){
        float value =0f;
        for (int i=0;i<items.size();i++){
            if (items.get(i).getAreaName().equals(city))
                value=Float.valueOf(items.get(i).getBizCategory());

        }
        return value;
    }

    public void getInfo() {

        String areaId = tvXZQH.getText().toString();
        String startTime = tvStartTime.getText().toString();
        String endTime = tvEndTime.getText().toString();

        //sysadmin 510401
        String params = getParams("510401", areaId, startTime,endTime);

        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;" +
                "charset=UTF-8"), params.getBytes());

        Call<HD_TJ_HDDX> call = App.getApiProxy().getTJ_HDbgzs(body);

        ApiUtil<HD_TJ_HDDX> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<HD_TJ_HDDX>() {

            @Override
            public void doAfter() {
                super.doAfter();
            }

            @Override
            public void doSuccess(HD_TJ_HDDX result) {


                List<HD_TJ_HDDX.ResultBean> items = result.Result;

                if (items != null && items.size() > 0) {

                            setmChartInfo(items);
                } else {

                }

            }
        }, false);
        apiUtil.excute();
    }

    String getParams(String userid, String areaId, String startTime, String endTime) {

        String md5key = Md5Util.getMd5(userid + areaId + startTime + endTime);
        StringBuilder sb = new StringBuilder();
        sb.append("userId=");
        sb.append(userid);
        sb.append("&");
        sb.append("areaId=");
        sb.append(areaId);
        sb.append("&");
        sb.append("startTime=");
        sb.append(startTime);
        sb.append("&");
        sb.append("endTime=");
        sb.append(endTime);
        sb.append("&");
        sb.append("Md5Key=");
        sb.append(md5key);
        return sb.toString();
    }
}
