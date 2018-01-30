package com.szmz.tjfx;

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
import com.szmz.entity.YwblDzdaXzqh;
import com.szmz.entity.request.JZ_Tj1_Req;
import com.szmz.entity.request.JZ_YWBL_DZDA_XZQH_RE;
import com.szmz.entity.response.JZ_YWBL_DZDA_XZQH;
import com.szmz.entity.response.JZ_tj1;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.DatePickerUtil;
import com.szmz.utils.DateUtil;
import com.szmz.ywbl.dzda.JzTreeItemHolder;
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
 * 创建时间：2017/11/21 0021下午 3:19
 */

public class ActJZ_1 extends ActBase {

    @BindView(R.id.barchart)
    BarChart mChart;

    protected Typeface mTfRegular;
    protected Typeface mTfLight;


    private List<String> types = new ArrayList<>();

    private List<JZ_tj1.ResultBean> items = new ArrayList<>();

    private TimePickerView pvTime;

    @BindView(R.id.et_tj_time)
    TextView tvStartTime;
    @BindView(R.id.et_tj_time2)
    TextView tvEndTime;

    @BindView(R.id.et_tj_xzqh)
    TextView tvXZQH;

    private String qhId = "";
    private String regionId = "";//行政区划ID
    private YwblDzdaXzqh xzqh;
    private MaterialDialog dialog;
    private AndroidTreeView tView;
    private MaterialDialog.Builder builder;
    public static final int[] PIE_COLORS = {
            Color.rgb(181, 194, 202), Color.rgb(129, 216, 200), Color.rgb(241, 214, 145),
            Color.rgb(108, 176, 223), Color.rgb(195, 221, 155), Color.rgb(251, 215, 191),
            Color.rgb(237, 189, 189), Color.rgb(172, 217, 243)
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_tjfx__hddxzrs;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setRightVisible(true);
        setRightShow("搜索");
        setTitle("救助对象统计");

        initBarChart();
        doGetXzqh();

        tvEndTime.setText(DateUtil.getCurrentDay());
        tvStartTime.setText(DateUtil.getDayBeforeMonth(1));

        tvTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInfo();
            }
        });

    }

    private void initTimePicker() {
        pvTime = DatePickerUtil.initPicker(this, DatePickerUtil.yyyyMMdd);
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
//        mChart.setTouchEnabled(false);
        mChart.animateY(1500);
        mChart.setExtraBottomOffset(20f);
//        mChart.setExtraRightOffset(20f);
//        mChart.setClickable(false);

        Matrix m=new Matrix();
        m.postScale(1.1f, 1f);//两个参数分别是x,y轴的缩放比例。例如：将x轴的数据放大为之前的1.5倍
        mChart.getViewPortHandler().refresh(m, mChart, false);//将图表动画显示之前进行缩放
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
        xAxis.setGranularity(1f);
        xAxis.setTextSize(10f);
//        xAxis.setXOffset(20f);
//        xAxis.setLabelRotationAngle(25f);
        xAxis.setDrawGridLines(false);
//        xAxis.setAxisMinimum(2);
        xAxis.setAxisMaximum(6);
//        xAxis.setCenterAxisLabels(true);//标签居中显示
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                if (value >= 0 && value < items.size())
                    return items.get((int) value).getRescueCategoryName();
                return "";
            }
        });

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTypeface(mTfLight);
//        leftAxis.setValueFormatter(new IndexAxisValueFormatter());
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

        mChart.getAxisRight().setEnabled(false);
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

    void setmChartInfo(List<JZ_tj1.ResultBean> items) {

        if (items == null || items.size() == 0)
            return;


        ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();

        for (int i = 0; i < items.size(); i++) {
            yVals.add(new BarEntry(i, items.get(i).getPernum()));
        }
        BarDataSet barDataSet = new BarDataSet(yVals, "救助对象人次");
        barDataSet.setColor(PIE_COLORS[1]);
        barDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                int n = (int) value;
                return n + "";
            }
        });
        BarData data = new BarData();
        data.addDataSet(barDataSet);



        mChart.setData(data);

        mChart.invalidate();
    }

    public void getInfo() {

        String areaId = "";
        if (xzqh != null) {
            areaId = xzqh.getRegioncode();
        }

        String time1 = tvStartTime.getText().toString().trim();
        String time2 = tvEndTime.getText().toString().trim();
        if (TextUtils.isEmpty(time1)) {
            doToast("请选择开始时间");
            return;
        }

        if (TextUtils.isEmpty(time2)) {
            doToast("请选择截至时间");
            return;
        }

        if (!TextUtils.isEmpty(time1) && !TextUtils.isEmpty(time2)) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date1 = format.parse(time1);
                Date date2 = format.parse(time2);
                if (date1.after(date2)) {
                    doToast("截至日期不能早于起始日期");
                    return;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        JZ_Tj1_Req req = new JZ_Tj1_Req(areaId, time1, time2);

        Call<JZ_tj1> call = App.getApiProxyJZ().getJZ_tj1(req);

        ApiUtil<JZ_tj1> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<JZ_tj1>() {

            @Override
            public void doAfter() {
                super.doAfter();
            }

            @Override
            public void doSuccess(JZ_tj1 result) {


                items = result.Result;

                mChart.setData(null);
                mChart.invalidate();
                if (items != null && items.size() > 0) {

                    setmChartInfo(items);
                } else {

                }

            }
        }, true);
        apiUtil.excute();
    }

    private void doGetXzqh() {
        JZ_YWBL_DZDA_XZQH_RE request = new JZ_YWBL_DZDA_XZQH_RE(App.getInstance().getLoginUser().getIdJZ());
        Call<JZ_YWBL_DZDA_XZQH> call = App.getApiProxyJZ().getJZ_XZQHList(request);
        ApiUtil<JZ_YWBL_DZDA_XZQH> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<JZ_YWBL_DZDA_XZQH>() {

            @Override
            public void doSuccess(JZ_YWBL_DZDA_XZQH result) {

                if (result != null) {
                    List<YwblDzdaXzqh> listDatas = result.Result;
                    initData(listDatas);
                    getInfo();
                }

            }


        }, true);

        apiUtil.excute();
    }

    private void initData(List<YwblDzdaXzqh> list) {
        TreeNode root = TreeNode.root();
        List<YwblDzdaXzqh> myList = new ArrayList<YwblDzdaXzqh>();
        for (int i = 0; i < list.size(); i++) {
            boolean isHaveParent = false;
            for (YwblDzdaXzqh item : list) {
                if (item.getId().equals(list.get(i).getParentId())) {
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
            tvXZQH.setText(xzqh.getRegionname());
        }
        for (YwblDzdaXzqh item : myList) {
            TreeNode node = new TreeNode(new JzTreeItemHolder.TreeItem(item)).setViewHolder(new JzTreeItemHolder(this, new JzTreeItemHolder.OnClickChildListener() {
                @Override
                public void doClick(YwblDzdaXzqh item) {
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                    xzqh = item;
                    tvXZQH.setText(item.getRegionname());
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


    private TreeNode addChildNode(TreeNode node, List<YwblDzdaXzqh> list) {
        for (YwblDzdaXzqh item : list) {
            JzTreeItemHolder.TreeItem value = (JzTreeItemHolder.TreeItem) node.getValue();
            if (value.item.getId().equals(item.getParentId())) {
                node.addChild(addChildNode(new TreeNode(new JzTreeItemHolder.TreeItem(item)).setViewHolder(new JzTreeItemHolder(this, new JzTreeItemHolder.OnClickChildListener() {
                    @Override
                    public void doClick(YwblDzdaXzqh item) {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                        xzqh = item;
                        tvXZQH.setText(item.getRegionname());

                    }
                })), list));
            }
        }

        return node;

    }

}
