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
public class ActTjfx_HDDXZRS extends ActBase implements OnChartValueSelectedListener {

    @BindView(R.id.barchart)
     BarChart mChart;

    protected Typeface mTfRegular;
    protected Typeface mTfLight;

    String[] xValueType={"城市低保","农村低收入","农村低保"};
    String[] xValues2 = {"石河子失","和田地区","伊利哈萨克自治州","昌吉回族自治州","aaa"};
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

    private void initBarChart(){
        mTfRegular = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        mTfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");

        mChart.setOnChartValueSelectedListener(this);
        mChart.getDescription().setEnabled(false);

        mChart.setPinchZoom(false);
        mChart.setDrawBarShadow(false);
        mChart.setDrawGridBackground(false);



//        Legend l = mChart.getLegend();
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
//        l.setOrientation(Legend.LegendOrientation.VERTICAL);
//        l.setDrawInside(true);
//        l.setTypeface(mTfLight);
//        l.setYOffset(0f);
//        l.setXOffset(10f);
//        l.setYEntrySpace(0f);
//        l.setTextSize(8f);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setTypeface(mTfLight);
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                System.out.print(""+value);
                return String.valueOf((int)value);
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


    private void initChartData(){


        float barWidth = 0.4f; // x4 DataSet


        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals3 = new ArrayList<BarEntry>();

        float randomMultiplier =  100000f;

        for (int i = 0; i < 2; i++) {
            yVals1.add(new BarEntry(i, (float) (Math.random() * randomMultiplier)));
            yVals2.add(new BarEntry(i, (float) (Math.random() * randomMultiplier)));
            yVals3.add(new BarEntry(i, (float) (Math.random() * randomMultiplier)));
        }

        BarDataSet set1, set2, set3;

            // create 4 DataSets
            set1 = new BarDataSet(yVals1, xValueType[0]);
            set1.setColor(Color.rgb(104, 241, 175));
            set2 = new BarDataSet(yVals2, xValueType[1]);
            set2.setColor(Color.rgb(164, 228, 251));
            set3 = new BarDataSet(yVals3, xValueType[1]);
            set3.setColor(Color.rgb(242, 247, 158));


        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);
        dataSets.add(set2);
        dataSets.add(set3);


            BarData data = new BarData(dataSets);


            data.setValueFormatter(new LargeValueFormatter());
            data.setValueTypeface(mTfLight);

            mChart.setData(data);


        // s
        // pecify the width each bar should have
        mChart.getBarData().setBarWidth(barWidth);

        mChart.invalidate();
    }


    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
