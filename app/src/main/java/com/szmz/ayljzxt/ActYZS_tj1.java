package com.szmz.ayljzxt;

import android.graphics.Color;
import android.graphics.Typeface;
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
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.szmz.ActBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.YZS_xzqh;
import com.szmz.entity.request.YZS_TJ1_Req;
import com.szmz.entity.request.YZS_qh_req;
import com.szmz.entity.response.YZS_TJ1_Res;
import com.szmz.entity.response.YZS_qh_res;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.DatePickerUtil;
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
import retrofit2.Call;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/24 0024下午 3:44
 */

public class ActYZS_tj1 extends ActBase {

    @BindView(R.id.et_tj_xzqh)
    TextView tvXZQH;
    @BindView(R.id.et_tj_time)
    TextView tvStartTime;
    @BindView(R.id.et_tj_time2)
    TextView tvEndTime;

    public static final int[] PIE_COLORS = {
            Color.rgb(181, 194, 202), Color.rgb(129, 216, 200), Color.rgb(241, 214, 145),
            Color.rgb(108, 176, 223), Color.rgb(195, 221, 155), Color.rgb(251, 215, 191),
            Color.rgb(237, 189, 189), Color.rgb(172, 217, 243)
    };

    @Override
    protected int getLayoutId() {
        return R.layout.act_yzs_tj;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setRightVisible(true);
        setRightShow("搜索");
        setTitle("医疗救助对象统计");
        tvTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInfo();
            }
        });
        getQHlist();
        initTimePicker();
        initChart();
    }

    private TimePickerView pvTime;

    private void initTimePicker() {
        pvTime = DatePickerUtil.initPicker(this, DatePickerUtil.yyyyMMdd);
    }

    @OnClick({R.id.et_tj_time2, R.id.et_tj_time, R.id.et_tj_xzqh, R.id.tv_title_right})
    public void doClick(View v) {

        switch (v.getId()) {
            case R.id.et_tj_time:
                pvTime.show(tvStartTime);
                break;
            case R.id.et_tj_time2:
                pvTime.show(tvEndTime);
                break;
            case R.id.et_tj_xzqh:

                break;
            case R.id.tv_title_right:
                getInfo();
                break;
        }
    }


    private void getInfo() {
        String areaId = "";
        if (xzqh != null) {
            areaId = xzqh.getCode();
        }
        if (TextUtils.isEmpty(areaId)) {
            doToast("请选择区划");
            return;
        }
        String startTime = tvStartTime.getText().toString();
        String endTime = tvEndTime.getText().toString();

        if (TextUtils.isEmpty(startTime)) {
            doToast("请选择开始时间");
            return;
        }

        if (TextUtils.isEmpty(endTime)) {
            doToast("请选择截至时间");
            return;
        }

        if (!TextUtils.isEmpty(startTime) && !TextUtils.isEmpty(endTime)) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date1 = format.parse(startTime);
                Date date2 = format.parse(endTime);
                if (date1.after(date2)) {
                    doToast("截至日期不能早于起始日期");
                    return;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        YZS_TJ1_Req req = new YZS_TJ1_Req(areaId, startTime, endTime);

        Call<YZS_TJ1_Res> call = App.getApiProxyYZS().getYZS_tj1(req);

        ApiUtil<YZS_TJ1_Res> apiUtil = new ApiUtil<>(context, call, new SimpleApiListener<YZS_TJ1_Res>() {
            @Override
            public void doSuccess(YZS_TJ1_Res result) {
                super.doSuccess(result);

                List<YZS_TJ1_Res.ResultBean> items = result.Result;
                mChart.setData(null);
                mChart.invalidate();

                if (items != null && items.size() > 0) {

                    setInfo(items);
                }
            }
        }, true);

        apiUtil.excute();
    }

    private void setInfo(List<YZS_TJ1_Res.ResultBean> items) {


        xValues = new ArrayList<>();
        String[] yValueType = {"住院救助", "门诊救助"};

        List<float[]> yValues = new ArrayList<>();

        for (YZS_TJ1_Res.ResultBean item : items) {
            if (!(xValues.contains(item.getRESCUE_CATEGORY_NAME()))) {
                xValues.add(item.getRESCUE_CATEGORY_NAME());
            }
        }

        for (String x : xValues) {
            if (x == null) {
                return;
            }
            float[] yValueitem = new float[2];
            for (YZS_TJ1_Res.ResultBean item : items) {
                if (item.getFAMILY_NUM().equals("0"))
                    continue;
                if (x.equals(item.getRESCUE_CATEGORY_NAME()) && item.getRescueType().equals(yValueType[0])) {
                    yValueitem[0] = Float.valueOf(item.getFAMILY_NUM());
                }
                if (x.equals(item.getRESCUE_CATEGORY_NAME()) && item.getRescueType().equals(yValueType[1])) {
                    yValueitem[1] = Float.valueOf(item.getFAMILY_NUM());
                }
            }
            yValues.add(yValueitem);
        }

        List<BarEntry> barEntries = new ArrayList<>();
        for (int i = 0; i < xValues.size(); i++) {

            BarEntry barEntry = new BarEntry(i, yValues.get(i));
            barEntries.add(barEntry);
        }
        BarDataSet barDataSet = new BarDataSet(barEntries, "医疗救助分类");
        barDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                int n = (int) value;
                return n + "";
            }
        });

        barDataSet.setDrawIcons(false);
        barDataSet.setColors(getColors());
        barDataSet.setStackLabels(yValueType);
        BarData data = new BarData(barDataSet);

        mChart.setData(data);

        mChart.setFitBars(true);
        mChart.animateXY(3000, 3000);
        mChart.invalidate();
    }

    private int[] getColors() {

        int stacksize = 2;

        // have as many colors as stack-values per entry
        int[] colors = new int[stacksize];

        for (int i = 0; i < colors.length; i++) {
            colors[i] = ColorTemplate.MATERIAL_COLORS[i];
        }

        return colors;
    }

    @BindView(R.id.barchart)
    BarChart mChart;
    List<String> xValues = new ArrayList<>();
    protected Typeface mTfRegular;
    protected Typeface mTfLight;

    private void initChart() {
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
//        xAxis.setLabelRotationAngle(25f);
        xAxis.setDrawGridLines(false);
//        xAxis.setCenterAxisLabels(true);//标签居中显示
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                if (value >= 0 && value < xValues.size())
                    return xValues.get((int) value);

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

    //***********************************区划*****************************************?//
    private YZS_xzqh xzqh;
    private MaterialDialog dialog;
    private AndroidTreeView tView;
    private MaterialDialog.Builder builder;

    private void getQHlist() {

        YZS_qh_req req = new YZS_qh_req(getUser().getAccountYZS());


        Call<YZS_qh_res> call = App.getApiProxyYZS().getYZS_xzqh(req);

        ApiUtil<YZS_qh_res> apiUtil = new ApiUtil<>(context, call, new SimpleApiListener<YZS_qh_res>() {
            @Override
            public void doSuccess(YZS_qh_res result) {
                super.doSuccess(result);

                List<YZS_xzqh> xzqhs = result.Result;
                if (xzqhs != null && xzqhs.size() > 0)
                    initData(xzqhs);
            }
        }, false);

        apiUtil.excute();

    }

    private void initData(List<YZS_xzqh> list) {
        TreeNode root = TreeNode.root();
        List<YZS_xzqh> myList = new ArrayList<YZS_xzqh>();
        for (int i = 0; i < list.size(); i++) {
            boolean isHaveParent = false;
            for (YZS_xzqh item : list) {
                if (item.getID().equals(list.get(i).getPARENT_ID())) {
                    isHaveParent = true;
                    break;
                }
            }
            if (!isHaveParent) {
                myList.add(list.get(i));
            }
        }
        if (myList != null && myList.size() > 0) {
            xzqh = myList.get(0);
            tvXZQH.setText(xzqh.getName());
        }
        for (YZS_xzqh item : myList) {
            TreeNode node = new TreeNode(new YZSTreeItemHolder.TreeItem(item)).setViewHolder(new YZSTreeItemHolder(this, new YZSTreeItemHolder.OnClickChildListener() {
                @Override
                public void doClick(YZS_xzqh item) {
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                    xzqh = item;
                    tvXZQH.setText(item.getName());
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


    private TreeNode addChildNode(TreeNode node, List<YZS_xzqh> list) {
        for (YZS_xzqh item : list) {
            YZSTreeItemHolder.TreeItem value = (YZSTreeItemHolder.TreeItem) node.getValue();
            if (value.item.getID().equals(item.getPARENT_ID())) {
                node.addChild(addChildNode(new TreeNode(new YZSTreeItemHolder.TreeItem(item)).setViewHolder(new YZSTreeItemHolder(this, new YZSTreeItemHolder.OnClickChildListener() {
                    @Override
                    public void doClick(YZS_xzqh item) {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                        xzqh = item;
                        tvXZQH.setText(item.getName());

                    }
                })), list));
            }
        }

        return node;

    }
}
