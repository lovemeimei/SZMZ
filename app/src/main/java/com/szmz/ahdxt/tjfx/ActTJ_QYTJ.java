package com.szmz.ahdxt.tjfx;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.szmz.ActBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.response.HD_TJ_QYRC;
import com.szmz.entity.response.HD_TJ_QYRC;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.Md5Util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

public class ActTJ_QYTJ extends ActBase {

    public static final int[] PIE_COLORS = {
            Color.rgb(181, 194, 202), Color.rgb(129, 216, 200), Color.rgb(241, 214, 145),
            Color.rgb(108, 176, 223), Color.rgb(195, 221, 155), Color.rgb(251, 215, 191),
            Color.rgb(237, 189, 189), Color.rgb(172, 217, 243)
    };
    @BindView(R.id.barchart)
    PieChart pieChart;
    @BindView(R.id.et_tj_xzqh)
    TextView tvXZQH;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_tj__qytj;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("区域人次统计");
        setRightVisible(true);
        setRightShow("搜索");
        initChart();
        getInfo();
    }

    void initChart() {
        pieChart.setUsePercentValues(true);//设置使用百分比
        pieChart.getDescription().setEnabled(false);//设置描述
        pieChart.setExtraOffsets(20, 15, 20, 15);
        pieChart.setCenterText("区域人次统计");//设置环中的文字
        pieChart.setCenterTextSize(22f);//设置环中文字的大小
        pieChart.setDrawCenterText(true);//设置绘制环中文字
        pieChart.setRotationAngle(120f);//设置旋转角度

        //图例设置
        Legend legend = pieChart.getLegend();

        legend.setEnabled(true);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);

        pieChart.animateX(1500, Easing.EasingOption.EaseInOutQuad);//数据显示动画
    }


    /**
     * 设置饼图数据源
     */
    private static void setPieChartData(PieChart pieChart, Map<String, Float> pieValues) {
        ArrayList<PieEntry> entries = new ArrayList<>();

        Set set = pieValues.entrySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            entries.add(new PieEntry(Float.valueOf(entry.getValue().toString()), entry.getKey().toString()));
        }

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(3f);//设置饼块之间的间隔
        dataSet.setSelectionShift(5f);//设置饼块选中时偏离饼图中心的距离

        dataSet.setColors(PIE_COLORS);//设置饼块的颜色
        dataSet.setValueLinePart1OffsetPercentage(80f);//数据连接线距图形片内部边界的距离，为百分数
        dataSet.setValueLinePart1Length(0.3f);
        dataSet.setValueLinePart2Length(0.4f);
        dataSet.setValueLineColor(Color.BLUE);//设置连接线的颜色
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        PieData pieData = new PieData(dataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(11f);
        pieData.setValueTextColor(Color.DKGRAY);

        pieChart.setData(pieData);
        pieChart.highlightValues(null);
        pieChart.invalidate();
    }
    @OnClick({R.id.et_tj_xzqh,R.id.tv_title_right})
    public void doClick(View v) {

        switch (v.getId()) {
            case R.id.et_tj_xzqh:
                Calendar cal = Calendar.getInstance();

                DatePickerDialog dialog1 = new DatePickerDialog(this, new DatePickerDialog
                        .OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        tvXZQH.setText(year + "-" + (month + 1));
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
                dialog1.show();
                break;
            case R.id.tv_title_right:
                getInfo();
                break;
        }
    }

    public void getInfo() {

        String sqtime = tvXZQH.getText().toString();
        if (TextUtils.isEmpty(sqtime)){
            Calendar cal = Calendar.getInstance();

            sqtime = cal.get(Calendar.YEAR)+"-"+ (cal.get(Calendar.MONTH)+1);
        }

        //sysadmin 510401
        final String params = getParams("510401", sqtime);

        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;" +
                "charset=UTF-8"), params.getBytes());

        Call<HD_TJ_QYRC> call = App.getApiProxy().getTJ_HDqyrc(body);

        ApiUtil<HD_TJ_QYRC> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<HD_TJ_QYRC>() {

            @Override
            public void doAfter() {
                super.doAfter();
            }

            @Override
            public void doSuccess(HD_TJ_QYRC result) {


                List<HD_TJ_QYRC.ResultBean> items = result.Result;

                if (items != null && items.size() > 0) {

                    Map<String,Float> maps = new HashMap<>();
                    for (HD_TJ_QYRC.ResultBean item:items){
                        maps.put(item.getAreaName(),(float)item.getPercent());
                    }
                    setPieChartData(pieChart,maps);
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
