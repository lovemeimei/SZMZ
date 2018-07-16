package com.szmz.ahdxt.tjfx;

import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bigkoo.pickerview.TimePickerView;
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
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.szmz.ActBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.SystemConst;
import com.szmz.SystemEnv;
import com.szmz.entity.HD_XZQH;
import com.szmz.entity.response.HD_TJ_YWQS;
import com.szmz.entity.response.HD_XZQH_Response;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.DatePickerUtil;
import com.szmz.utils.DateUtil;
import com.szmz.utils.Md5Util;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * 核对业务趋势
 */
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
        setTitle("核对业务趋势");
        setRightVisible(true);
        setRightShow("搜索");

        initBarChart();
        getInfo();
        tvTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInfo();
            }
        });

    }
    private TimePickerView pvTime;
    private void initTimePicker() {
        pvTime=  DatePickerUtil.initPicker(this,DatePickerUtil.yyyyMM);
    }

    private void initBarChart() {
        mChart.setNoDataText("暂无数据");

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
        mChart.setScaleEnabled(true);
        mChart.setDrawGridBackground(false);
        mChart.setTouchEnabled(true);
        mChart.animateY(1500);
        mChart.animateX(1500);
        mChart.setExtraBottomOffset(20f);

//        Matrix m=new Matrix();
//        m.postScale(1.2f, 1f);//两个参数分别是x,y轴的缩放比例。例如：将x轴的数据放大为之前的1.5倍
//        mChart.getViewPortHandler().refresh(m, mChart, false);//将图表动画显示之前进行缩放
        mChart.animateX(1000); // 立即执行的动画,x轴


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
        xAxis.setTextSize(10f);
        xAxis.setDrawGridLines(false);
        mChart.getXAxis().setAxisMinimum(-0.5f);
//        xAxis.setLabelRotationAngle(30);
//        xAxis.setCenterAxisLabels(false);

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawLabels(true);

        xAxis.setLabelCount(6);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                if (value==(int)value){
                    if (value >= 0 && value < times.size())
                        return times.get((int) value);
                }

                return "";
            }
        });

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTypeface(mTfLight);
        leftAxis.setDrawGridLines(false);
        leftAxis.setSpaceTop(35f);

        leftAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                if (value==(int)value)
                    return (int)value+"";

                return "";
            }
        });

        mChart.getAxisRight().setEnabled(false);
    }

    @OnClick({R.id.et_tj_xzqh,R.id.tv_title_right})
    public void doClick(View v) {

        switch (v.getId()) {
            case R.id.et_tj_xzqh:
                initTimePicker();
                pvTime.show(tvXZQH);

                break;
            case R.id.tv_title_right:
                getInfo();
                break;
        }
    }

    void setmChartInfo(List<HD_TJ_YWQS.ResultBean> items) {

        times = new ArrayList<>();

        if (items == null || items.size() == 0)
            return;

        for (HD_TJ_YWQS.ResultBean item : items) {

            if (!times.contains(item.getDirectionTime()))
                times.add(item.getDirectionTime());
        }

        float groupSpace = 0.1f;
        float barSpace = 0.05f; // x3 DataSet
        float barWidth = 0.25f; // x3 DataSet
        barWidth = (float) (0.9f - 0.15);

        BarData data = new BarData();
        ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();
        ArrayList<Entry> lineValues = new ArrayList<>();

        for (int j = 0; j < times.size(); j++) {
            yVals.add(new BarEntry(j, getValueByCity(items, times.get(j))));

            Entry entry =new Entry(j,getValueLine(items,times.get(j)));
            lineValues.add(entry);
        }


        LineDataSet lineDataSet = new LineDataSet(lineValues,"趋势");
        lineDataSet.setColor(PIE_COLORS[4]);
        LineData lineData = new LineData(lineDataSet);
//        lineData.setDrawValues(false);
        lineData.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {


                if (value==0){
                    return "";
                }
                return value+"";
            }
        });


        BarDataSet barDataSet = new BarDataSet(yVals, "总数");
        barDataSet.setColor(PIE_COLORS[3]);
        data.addDataSet(barDataSet);

        data.setHighlightEnabled(false);

        data.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {

                int mvalue = (int)value;
                if (mvalue==0){
                    return "";
                }
                return mvalue+"";
            }
        });
        data.setValueTypeface(mTfLight);

        CombinedData combinedData = new CombinedData();
        combinedData.setData(data);
        combinedData.setData(lineData);
        mChart.setData(combinedData);


        mChart.getBarData().setBarWidth(barWidth);

//        setmChartWidth(times.size(),1.0f);


        mChart.invalidate();

    }

    private void setmChartWidth(int size,float xs){

            Matrix m=new Matrix();
            m.postScale(2.1f*xs, 1f);//两个参数分别是x,y轴的缩放比例。例如：将x轴的数据放大为之前的1.5倍
            mChart.getViewPortHandler().refresh(m, mChart, false);//将图表动画显示之前进行缩放
            mChart.animateX(1000); // 立即执行的动画,x轴


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

            sqtime = DateUtil.getCurrentMonth();
            tvXZQH.setText(sqtime);

        }

        //sysadmin 510401
        String params = getParams(App.getInstance().getLoginUser().getAccountHD(), sqtime);

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

                mChart.setData(null);
                mChart.invalidate();
                if (items != null && items.size() > 0) {

                    setmChartInfo(items);
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
