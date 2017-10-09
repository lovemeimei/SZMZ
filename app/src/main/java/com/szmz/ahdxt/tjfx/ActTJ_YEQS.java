package com.szmz.ahdxt.tjfx;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.szmz.ActBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.response.HD_TJ_YWQS;
import com.szmz.entity.response.HD_TJ_YWQS;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.Md5Util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

public class ActTJ_YEQS extends ActBase {


    @BindView(R.id.barchart)
    CombinedChart mChart;


    protected Typeface mTfRegular;
    protected Typeface mTfLight;
    public static final int[] PIE_COLORS = {
            Color.rgb(181, 194, 202), Color.rgb(129, 216, 200), Color.rgb(241, 214, 145),
            Color.rgb(108, 176, 223), Color.rgb(195, 221, 155), Color.rgb(251, 215, 191),
            Color.rgb(237, 189, 189), Color.rgb(172, 217, 243)
    };
    private List<String> times = new ArrayList<>();

    @BindView(R.id.et_tj_xzqh)
    TextView tvXZQH;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_tj__yeqs;
    }


    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("核对报告趋势");
        setRightVisible(true);
        setRightShow("搜索");
        initBarChart();
        initTimePicker();
        getInfo();

    }
    private TimePickerView pvTime;
    private void initTimePicker() {
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(2013, 0, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2019, 11, 28);
        //时间选择器
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                /*btn_Time.setText(getTime(date));*/
                TextView btn = (TextView) v;
                btn.setText(getTime(date));
            }
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, false, false, false, false})
                .setLabel("", "", "", "", "", "")
                .isCenterLabel(false)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(21)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
                .setDecorView(null)
                .build();
    }
    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        return format.format(date);
    }

    private void initBarChart() {

        //设置绘制顺序，让线在柱的上层
        mChart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.LINE
        });
        mTfRegular = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        mTfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");

        mChart.getDescription().setEnabled(false);
        mChart.setPinchZoom(false);
        mChart.setDrawBarShadow(false);
        mChart.setHorizontalScrollBarEnabled(true);
        mChart.setVerticalScrollBarEnabled(false);
        mChart.setDrawGridBackground(false);
        mChart.setTouchEnabled(false);
        mChart.animateY(1500);
        mChart.animateX(1500);
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
//        xAxis.setLabelRotationAngle(25f);
        xAxis.setDrawGridLines(false);
        xAxis.setCenterAxisLabels(true);//标签居中显示
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawLabels(true);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                if (value > 0 && value < times.size())
                    return times.get((int) value);
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

    @OnClick({R.id.et_tj_xzqh,R.id.tv_title_right})
    public void doClick(View v) {

        switch (v.getId()) {
            case R.id.et_tj_xzqh:
                pvTime.show(tvXZQH);

                break;
            case R.id.tv_title_right:
                getInfo();
                break;
        }
    }

    void setmChartInfo(List<HD_TJ_YWQS.ResultBean> items) {

        if (items == null || items.size() == 0)
            return;

        for (HD_TJ_YWQS.ResultBean item : items) {

            if (!times.contains(item.getDirectionTime()))
                times.add(item.getDirectionTime());
        }

        float groupSpace = 0.1f;
        float barSpace = 0.05f; // x3 DataSet
        float barWidth = 0.25f; // x3 DataSet
        barWidth = (float) (0.9f - 0.05);
        // (0.25 + 0.05) * 3 + 0.1 = 1.00 -> interval per "group"

        BarData data = new BarData();
        ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();
        ArrayList<Entry> lineValues = new ArrayList<>();

        for (int j = 0; j < times.size(); j++) {
            yVals.add(new BarEntry(j, getValueByCity(items, times.get(j))));
            lineValues.add(new Entry(j,getValueLine(items,times.get(j))));
        }


        LineDataSet lineDataSet = new LineDataSet(lineValues,"趋势");
        lineDataSet.setColor(PIE_COLORS[4]);
        LineData lineData = new LineData(lineDataSet);

        BarDataSet barDataSet = new BarDataSet(yVals, "总数");
        barDataSet.setColor(PIE_COLORS[3]);
        data.addDataSet(barDataSet);


        data.setValueFormatter(new LargeValueFormatter());
        data.setValueTypeface(mTfLight);

        CombinedData combinedData = new CombinedData();
        combinedData.setData(data);
        combinedData.setData(lineData);
        mChart.setData(combinedData);

//        mChart.getXAxis().setAxisMinimum(0);
//        mChart.getXAxis().setAxisMaximum(4.0f);
        mChart.getBarData().setBarWidth(barWidth);
        mChart.invalidate();

    }


    private float getValueByCity(List<HD_TJ_YWQS.ResultBean> items, String city) {
        float value = 0f;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getDirectionTime().equals(city))
                value = Float.valueOf(items.get(i).getTotal());

        }
        return value;
    }
    private float getValueLine(List<HD_TJ_YWQS.ResultBean> items, String city) {
        float value = 0f;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getDirectionTime().equals(city))
                value = (float)items.get(i).getPercent();

        }
        return value;
    }

    public void getInfo() {

        String sqtime = tvXZQH.getText().toString();

        if (TextUtils.isEmpty(sqtime)){
            Calendar cal = Calendar.getInstance();

            sqtime = cal.get(Calendar.YEAR)+"-"+ (cal.get(Calendar.MONTH)+1);
        }

        //sysadmin 510401
        String params = getParams("510401", sqtime);

        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;" +
                "charset=UTF-8"), params.getBytes());

        Call<HD_TJ_YWQS> call = App.getApiProxy().getTJ_HDywqs(body);

        ApiUtil<HD_TJ_YWQS> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<HD_TJ_YWQS>() {

            @Override
            public void doAfter() {
                super.doAfter();
            }

            @Override
            public void doSuccess(HD_TJ_YWQS result) {


                List<HD_TJ_YWQS.ResultBean> items = result.Result;

                if (items != null && items.size() > 0) {

                    setmChartInfo(items);
                } else {

                }

            }
        }, true);
        apiUtil.excute();
    }

    String getParams(String userid, String sqTime) {

        String md5key = Md5Util.getMd5(userid + sqTime);
        StringBuilder sb = new StringBuilder();
        sb.append("userId=");
        sb.append(userid);
        sb.append("&");
        sb.append("entrustTime=");
        sb.append(sqTime);
        sb.append("&");
        sb.append("Md5Key=");
        sb.append(md5key);
        return sb.toString();
    }
}
