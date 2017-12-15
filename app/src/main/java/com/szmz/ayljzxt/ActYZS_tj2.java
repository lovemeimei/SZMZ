package com.szmz.ayljzxt;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bigkoo.pickerview.TimePickerView;
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
import com.szmz.entity.YZS_xzqh;
import com.szmz.entity.request.YZS_TJ1_Req;
import com.szmz.entity.request.YZS_qh_req;
import com.szmz.entity.response.YZS_TJ3_Res;
import com.szmz.entity.response.YZS_tj2_Res;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/24 0024下午 3:44
 */

public class ActYZS_tj2 extends ActBase{
    @BindView(R.id.et_tj_xzqh)
    TextView tvXZQH;
    @BindView(R.id.et_tj_time)
    TextView tvStartTime;
    @BindView(R.id.et_tj_time2)
    TextView tvEndTime;

    public static final int[] PIE_COLORS = {
            Color.rgb(241, 214, 145),
            Color.rgb(108, 176, 223), Color.rgb(195, 221, 155), Color.rgb(251, 215, 191),
            Color.rgb(237, 189, 189), Color.rgb(172, 217, 243)
    };
    @Override
    protected int getLayoutId() {
        return R.layout.act_yzs_tj3;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setRightVisible(true);
        setRightShow("搜索");
        setTitle("住院类型统计");
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

    private void getInfo(){
        String areaId = "";
        if (xzqh != null) {
            areaId = xzqh.getCode();
        }
        if (TextUtils.isEmpty(areaId)){
            doToast("请选择区划");
            return;
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
        YZS_TJ1_Req req = new YZS_TJ1_Req(areaId,startTime,endTime);

        Call<YZS_tj2_Res> call = App.getApiProxyYZS().getYZS_tj2(req);

        ApiUtil<YZS_tj2_Res>  apiUtil  = new ApiUtil<>(context,call,new SimpleApiListener<YZS_tj2_Res>(){
            @Override
            public void doSuccess(YZS_tj2_Res result) {
                super.doSuccess(result);

                List<YZS_tj2_Res.ResultBean> items = result.Result;

                if (items!=null && items.size()>0){

                    setInfo(items);
                }else {
                    pieChart.removeAllViews();
                    pieChart.invalidate();
                }
            }
        },true);

        apiUtil.excute();
    }
    float allcount = 0.0f;

    private void setInfo(List<YZS_tj2_Res.ResultBean> items){
//        {"Result":[{"INPATIENT_TYPE_NAME":"常见疾病","REAL_RESCUE_MONEY":"867.00","Num":"4"},{"INPATIENT_TYPE_NAME":"重大疾病","REAL_RESCUE_MONEY":"450.00","Num":"1"}],"Error":{"ErrorCode":"0","ErrorMessage":"success"},"TotalNum":"2"}

        Map<String, Float> maps = new HashMap<>();

        for (YZS_tj2_Res.ResultBean item : items) {

            allcount=allcount+Float.valueOf(item.getREAL_RESCUE_MONEY());
        }
        for (YZS_tj2_Res.ResultBean item : items) {
            maps.put(item.getINPATIENT_TYPE_NAME()+"\n"+item.getREAL_RESCUE_MONEY()+"元", Float.valueOf(item.getREAL_RESCUE_MONEY())*100/allcount );
        }
        setPieChartData(pieChart,maps);
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
//        pieChart.highlightValues(null);
        pieChart.invalidate();
    }

    @BindView(R.id.barchart)
    PieChart pieChart;
    void initChart() {
        pieChart.setUsePercentValues(false);//设置使用百分比
        pieChart.getDescription().setEnabled(false);//设置描述
        pieChart.getDescription().setText("");
        pieChart.setExtraOffsets(20, 15, 20, 15);
//        pieChart.setCenterText("住院类型统计");//设置环中的文字
//        pieChart.setCenterTextSize(22f);//设置环中文字的大小
//        pieChart.setCenterTextColor(R.color.black);
//        pieChart.setDrawCenterText(true);//设置绘制环中文字
//        pieChart.setRotationAngle(120f);//设置旋转角度

        // 设置 pieChart 图表Item文本属性
        pieChart.setDrawEntryLabels(true);              //设置pieChart是否只显示饼图上百分比不显示文字（true：下面属性才有效果）
        pieChart.setEntryLabelColor(Color.WHITE);       //设置pieChart图表文本字体颜色
//        mChart.setEntryLabelTypeface(mTfRegular);     //设置pieChart图表文本字体样式
        pieChart.setEntryLabelTextSize(10f);

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

    //***********************************区划*****************************************?//
    private YZS_xzqh xzqh;
    private MaterialDialog dialog;
    private AndroidTreeView tView;
    private MaterialDialog.Builder builder;

    private void getQHlist(){

        YZS_qh_req req = new YZS_qh_req(getUser().getAccountYZS());


        Call<YZS_qh_res> call = App.getApiProxyYZS().getYZS_xzqh(req);

        ApiUtil<YZS_qh_res> apiUtil = new ApiUtil<>(context,call,new SimpleApiListener<YZS_qh_res>(){
            @Override
            public void doSuccess(YZS_qh_res result) {
                super.doSuccess(result);

                List<YZS_xzqh> xzqhs = result.Result;
                if (xzqhs!=null && xzqhs.size()>0)
                    initData(xzqhs);
            }
        },false);

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
        if (myList!=null && myList.size()>0){
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
