package com.szmz.ahdxt.tjfx;

import android.graphics.Color;
import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bigkoo.pickerview.TimePickerView;
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
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.szmz.ActBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.SystemEnv;
import com.szmz.entity.HD_XZQH;
import com.szmz.entity.response.HD_TJ_GX;
import com.szmz.entity.response.HD_XZQH_Response;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.DatePickerUtil;
import com.szmz.utils.DateUtil;
import com.szmz.utils.Md5Util;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * 共享单位核对人数
 */
public class ActTJ_GXDWHDRC extends ActBase {

    @BindView(R.id.barchart)
    BarChart barChart;
    public static final int[] PIE_COLORS = {
            Color.rgb(181, 194, 202), Color.rgb(129, 216, 200), Color.rgb(241, 214, 145),
            Color.rgb(108, 176, 223), Color.rgb(195, 221, 155), Color.rgb(251, 215, 191),
            Color.rgb(237, 189, 189), Color.rgb(172, 217, 243)
    };

    @BindView(R.id.et_tj_xzqh)
    TextView tvXZQH;
    @BindView(R.id.et_tj_time)
    TextView tvStartTime;
    @BindView(R.id.et_tj_time2)
    TextView tvEndTime;
    private HD_XZQH hd_xzqh;
    private MaterialDialog dialog;
    private AndroidTreeView tView;
    private MaterialDialog.Builder builder;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_tjfx__hddxzrs;
    }


    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("共享单位核对人数");
        setRightVisible(true);
        setRightShow("搜索");


        tvEndTime.setText(DateUtil.getCurrentMothLastDay());
        tvStartTime.setText(DateUtil.getCurrentMothFirstDay());

        getXzqhData(App.getInstance().getLoginUser().getAccountHD(), "");
        tvTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInfo();
            }
        });


    }


    private TimePickerView pvTime;

    private void initTimePicker() {
        pvTime = DatePickerUtil.initPicker(this, DatePickerUtil.yyyyMMdd);
    }


    private void initBarChart(final List<HD_TJ_GX.ResultBean> items) {

        //基础设置
        barChart.getDescription().setEnabled(false);//设置描述
        barChart.setPinchZoom(true);//设置按比例放缩柱状图
        barChart.getDescription().setEnabled(false);
        barChart.setPinchZoom(false);
        barChart.setDrawBarShadow(false);
        barChart.setHorizontalScrollBarEnabled(false);
        barChart.setVerticalScrollBarEnabled(false);
        barChart.setDrawGridBackground(false);
//        barChart.setTouchEnabled(false);
        barChart.animateY(1500);
        barChart.setExtraBottomOffset(20f);
        Matrix m=new Matrix();
        m.postScale(1.1f, 1f);//两个参数分别是x,y轴的缩放比例。例如：将x轴的数据放大为之前的1.5倍
        barChart.getViewPortHandler().refresh(m, barChart, false);//将图表动画显示之前进行缩放
        barChart.animateX(1000); // 立即执行的动画,x轴

        //x坐标轴设置
        XAxis xAxis = barChart.getXAxis();//获取x轴
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置X轴标签显示位置
        xAxis.setDrawGridLines(false);//不绘制格网线
        xAxis.setCenterAxisLabels(false);//标签居中显示


//        xAxis.setAxisMaximum(6);
//        xAxis.setLabelCount(4);//设置标签显示的个数
        xAxis.setLabelRotationAngle(20f);
        xAxis.setGranularity(1f);//设置最小间隔，防止当放大时，出现重复标签。
        xAxis.setValueFormatter(new IAxisValueFormatter() {//设置自定义的x轴值格式化器
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if (value >= 0 && value < items.size()) {
                    return items.get((int) value).getShareName();
                }
                return "";
            }
        });
        xAxis.setTextSize(10f);//设置标签字体大小


        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if (value==(int)value)
                    return (int)value+"";
                return "";
            }
        });
        leftAxis.setDrawGridLines(false);
        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        barChart.getAxisRight().setEnabled(false);


        Legend l = barChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(true);
        l.setYOffset(0f);
        l.setXOffset(10f);
        l.setYEntrySpace(0f);
        l.setTextSize(12f);

        List<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            BarEntry entry = new BarEntry(i, items.get(i).getCount());
            entries.add(entry);
        }
        BarDataSet dataSet = new BarDataSet(entries, "共享单位");
        dataSet.setColor(PIE_COLORS[2]);
        dataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {

                int mvalue = (int)value;
                return mvalue+"";
            }
        });
        BarData barData = new BarData(dataSet);

        barData.setHighlightEnabled(false);

        setmChartWidth(items.size(),1.0f);

        barChart.setData(barData);
        barChart.invalidate();

    }

    private void setmChartWidth(int size,float xs){
        if (size<5){
            barChart.getXAxis().setAxisMaximum(4);

        }else if (size<10){
            Matrix m=new Matrix();
            m.postScale(1.5f*xs, 1f);//两个参数分别是x,y轴的缩放比例。例如：将x轴的数据放大为之前的1.5倍
            barChart.getViewPortHandler().refresh(m, barChart, false);//将图表动画显示之前进行缩放
            barChart.animateX(1000); // 立即执行的动画,x轴

        }else {
            Matrix m=new Matrix();
            m.postScale(2.1f*xs, 1f);//两个参数分别是x,y轴的缩放比例。例如：将x轴的数据放大为之前的1.5倍
            barChart.getViewPortHandler().refresh(m, barChart, false);//将图表动画显示之前进行缩放
            barChart.animateX(1000); // 立即执行的动画,x轴

        }
    }

    @OnClick({R.id.et_tj_time2, R.id.et_tj_time, R.id.et_tj_xzqh, R.id.tv_title_right})
    public void doClick(View v) {

        switch (v.getId()) {
            case R.id.et_tj_time:
                initTimePicker();
                pvTime.show(tvStartTime);
                break;
            case R.id.et_tj_time2:
                initTimePicker();
                pvTime.show(tvEndTime);
                break;
            case R.id.et_tj_xzqh:

                break;
            case R.id.tv_title_right:
                getInfo();
                break;
        }
    }

    public void getInfo() {
        String areaId = "";
        if (hd_xzqh != null) {
            areaId = hd_xzqh.getAreaId();
        }

        String startTime = tvStartTime.getText().toString();
        String endTime = tvEndTime.getText().toString();

        if (TextUtils.isEmpty(startTime)){
            doToast("请选择开始时间");
            return;
        }

        if (TextUtils.isEmpty(endTime)){
            doToast("请选择截至时间");
            return;
        }
        if (!TextUtils.isEmpty(startTime) && !TextUtils.isEmpty(endTime)){
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date1 =format.parse(startTime);
                Date date2 =format.parse(endTime);
                if (date1.after(date2)){
                    doToast("截至日期不能早于起始日期");
                    return;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        //sysadmin 510401
        String params = getParams(App.getInstance().getLoginUser().getAccountHD(), areaId, startTime, endTime);

        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;" +
                "charset=UTF-8"), params.getBytes());

        Call<HD_TJ_GX> call = App.getApiProxy().getTJ_HDgx(body);

        ApiUtil<HD_TJ_GX> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<HD_TJ_GX>() {

            @Override
            public void doAfter() {
                super.doAfter();
            }

            @Override
            public void doSuccess(HD_TJ_GX result) {


                List<HD_TJ_GX.ResultBean> items = result.Result;

                barChart.setData(null);
                barChart.invalidate();
                if (items != null && items.size() > 0) {

                    initBarChart(items);
                } else {

                }

            }
        }, true);
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

    private void getXzqhData(String userId, String areaId) {
        List<HD_XZQH> xzqhList = SystemEnv.getXZQHList("XZQH");
        if (xzqhList != null && xzqhList.size() > 0) {
            initData(xzqhList);
            getInfo();
            return;
        }

        String md5key = Md5Util.getMd5(userId + areaId);
        StringBuilder sb = new StringBuilder();
        sb.append("userId=");
        sb.append(userId);
        sb.append("&");
        sb.append("areaId=");
        sb.append(areaId);
        sb.append("&");
        sb.append("Md5Key=");
        sb.append(md5key);
        String params = sb.toString();

        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;" +
                "charset=UTF-8"), params.getBytes());

        Call<HD_XZQH_Response> call = App.getApiProxy().getHD_XZQHList(body);

        ApiUtil<HD_XZQH_Response> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<HD_XZQH_Response>() {


            @Override
            public void doSuccess(HD_XZQH_Response result) {


                List<HD_XZQH> items = result.Result;

                if (items != null && items.size() > 0) {
                    SystemEnv.setXZQHList("XZQH", items);
                    initData(items);
                    getInfo();
                } else {
                }

            }
        }, true);
        apiUtil.excute();
    }

    private void initData(List<HD_XZQH> list) {
        TreeNode root = TreeNode.root();
        List<HD_XZQH> myList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            boolean isHaveParent = false;
            for (HD_XZQH item : list) {
                if (item.getAreaId().equals(list.get(i).getParentAreaId())) {
                    isHaveParent = true;
                    break;
                }
            }
            if (!isHaveParent) {
                myList.add(list.get(i));
            }
        }
        if (myList!=null && myList.size()>0){
            hd_xzqh = myList.get(0);
            tvXZQH.setText(hd_xzqh.getAreaName());
        }
        for (HD_XZQH item : myList) {
            TreeNode node = new TreeNode(new TreeItemHolder.TreeItem(item)).setViewHolder(new TreeItemHolder(this, new TreeItemHolder.OnClickChildListener() {
                @Override
                public void doClick(HD_XZQH item) {
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                    hd_xzqh = item;
                    tvXZQH.setText(item.getAreaName());
                }
            }));
            node = addChildNode(node, list);
            root.addChild(node);
        }
        tView = new AndroidTreeView(this, root);
        tView.setDefaultAnimation(false);
        tvXZQH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doShowDialog();
            }
        });
        builder = new MaterialDialog.Builder(context)
                .customView(tView.getView(), true).negativeText("取消").onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                });
    }

    private void doShowDialog() {
        dialog = builder.show();
        dialog.setCanceledOnTouchOutside(false);
    }


    private TreeNode addChildNode(TreeNode node, List<HD_XZQH> list) {
        for (HD_XZQH item : list) {
            TreeItemHolder.TreeItem value = (TreeItemHolder.TreeItem) node.getValue();
            if (value.item.getAreaId().equals(item.getParentAreaId())) {
                node.addChild(addChildNode(new TreeNode(new TreeItemHolder.TreeItem(item)).setViewHolder(new TreeItemHolder(this, new TreeItemHolder.OnClickChildListener() {
                    @Override
                    public void doClick(HD_XZQH item) {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                        hd_xzqh = item;
                        tvXZQH.setText(item.getAreaName());

                    }
                })), list));
            }
        }

        return node;

    }


}
