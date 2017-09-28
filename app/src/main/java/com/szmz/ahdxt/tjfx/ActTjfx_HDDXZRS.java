package com.szmz.ahdxt.tjfx;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.SystemConst;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import butterknife.BindView;

/**
 * 核对对象总人数
 */
public class ActTjfx_HDDXZRS extends ActBase {

    @BindView(R.id.barchart)
    BarChart mChart;

    protected Typeface mTfRegular;
    protected Typeface mTfLight;

    String[] xValueType = {"城市低保", "农村低收入", "农村低保"};
    String[] xValues2 = {"石河子失", "和田地区", "伊利哈萨克自治州", "昌吉回族自治州"};
    private List<String> xDates = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_tjfx__hddxzrs;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("核对对象总人次数");
        initBarChart();
        initChartData();
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

                switch ((int) value){
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                        return xValues2[(int) value];
                }
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


    private void initChartData() {
        float groupSpace = 0.1f;
        float barSpace = 0.05f; // x3 DataSet
        float barWidth = 0.25f; // x3 DataSet
        // (0.25 + 0.05) * 3 + 0.1 = 1.00 -> interval per "group"

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals3 = new ArrayList<BarEntry>();

        float randomMultiplier = 1000f;

        for (int i = 0; i < 4; i++) {
            yVals1.add(new BarEntry(i, (float) (Math.random() * randomMultiplier)+100));
            yVals2.add(new BarEntry(i, (float) (Math.random() * randomMultiplier)+100));
            yVals3.add(new BarEntry(i, (float) (Math.random() * randomMultiplier)+100));
        }

        BarDataSet set1, set2, set3;
        // create 4 DataSets
        set1 = new BarDataSet(yVals1, xValueType[0]);
        set1.setColor(Color.rgb(104, 241, 175));
        set2 = new BarDataSet(yVals2, xValueType[1]);
        set2.setColor(Color.rgb(164, 228, 251));
        set3 = new BarDataSet(yVals3, xValueType[1]);
        set3.setColor(Color.rgb(242, 247, 158));


        BarData data = new BarData(set1,set2,set3);
        data.setValueFormatter(new LargeValueFormatter());
        data.setValueTypeface(mTfLight);

        mChart.setData(data);

        mChart.getXAxis().setAxisMinimum(0);
        mChart.getXAxis().setAxisMaximum(4.0f);
        mChart.getBarData().setBarWidth(barWidth);
        mChart.groupBars(0, groupSpace, barSpace);
        mChart.invalidate();
    }

}
