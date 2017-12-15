package com.szmz.ahdxt.jg;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.materiallistview.MaterialRefreshLayout;
import com.szmz.ActListBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.ahdxt.ActDictList;
import com.szmz.entity.response.HD_JG_YCCL;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.BaseListAdapter;
import com.szmz.utils.DatePickerUtil;
import com.szmz.utils.Md5Util;

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
 * 监管异常操作
 */
public class ActJG_ListYCCZ extends ActListBase {


    @BindView(R.id.lv)
    ListView lv;
    BaseListAdapter<HD_JG_YCCL.ResultBean, MViewHolder> adapter;
    List<HD_JG_YCCL.ResultBean> items = new ArrayList<>();

    @BindView(R.id.tv_jg_search1)
    TextView tvSearch1;
    @BindView(R.id.tv_jg_search2)
    TextView tvSearch2;
    @BindView(R.id.et_jg_search1)
    TextView etSearch1;
    @BindView(R.id.et_jg_search2)
    TextView etSearch2;
    @BindView(R.id.et_jg_search3)
    TextView etSearch3;


    private String code = "";
    private String startTime = "";
    private String endTime = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_jcyccz__list;
    }


    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("异常操作监管");
        setRightVisible(true);
        setRightShow("搜索");


        tvTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadInfo(false);
            }
        });

        adapter = new BaseListAdapter<HD_JG_YCCL.ResultBean, MViewHolder>(this, R.layout.list_item_jg_dybg) {
            @Override
            protected void refreshView(int postion, final HD_JG_YCCL.ResultBean item, MViewHolder holer) {

                holer.tvSub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(ActJG_ListYCCZ.this, ActJG_YCCZ.class);
                        intent.putExtra("item", item);
                        startActivity(intent);
                    }
                });

                holer.tvName.setText(item.getWarningName());
                holer.tvType.setText(item.getWarningTime());
            }

            @Override
            protected MViewHolder getHolder(View v) {

                MViewHolder holder = new MViewHolder();
                holder.tvType = (TextView) v.findViewById(R.id.tv_type);
                holder.tvName = (TextView) v.findViewById(R.id.tv_name);
                holder.tvSub = (TextView) v.findViewById(R.id.tv_submit);
                return holder;

            }
        };
        lv.setAdapter(adapter);

        adapter.setItems(items);
        adapter.notifyDataSetChanged();

        initTimePicker();
    }

    private TimePickerView pvTime;

    private void initTimePicker() {
        pvTime = DatePickerUtil.initPicker(this, DatePickerUtil.yyyyMMdd);
    }

    @Override
    public void doRefresh(MaterialRefreshLayout materialRefreshLayout) {

        loadInfo(false);
    }

    @Override
    public void doRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {

        loadInfo(true);
    }

    void loadInfo(boolean isMore) {
//        code = etSearch1.getText().toString().trim();
        startTime = etSearch2.getText().toString().trim();
        endTime = etSearch3.getText().toString().trim();

        if (startTime==null)
            startTime="";
        if (endTime==null)
            endTime="";
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
        if (isMore) {
            currentPage++;
        } else {
            currentPage = 1;
        }

        //sysadmin 510401 1001
        String params = getParams(App.getInstance().getLoginUser().getAccountHD(), code, startTime, endTime);

        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;" +
                "charset=UTF-8"), params.getBytes());

        Call<HD_JG_YCCL> call = App.getApiProxy().getJG_ycclList(body);

        ApiUtil<HD_JG_YCCL> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<HD_JG_YCCL>() {

            @Override
            public void doAfter() {
                super.doAfter();
                refresh.finishRefreshing();
                refresh.finishRefreshLoadMore();
            }

            @Override
            public void doSuccess(HD_JG_YCCL result) {

                items = result.Result;

                if (items != null && items.size() > 0) {

                    if (currentPage == 1) {
                        adapter.clearListData();
                    }
                    adapter.setItems(items);
                    adapter.notifyDataSetChanged();


                } else {
                    adapter.clearListData();
                    adapter.notifyDataSetChanged();
                }

                if (isHasNextPage(currentPage, pageSize, result.TotalNum)) {
                    refresh.setLoadMore(true);
                } else {
                    refresh.setLoadMore(false);
                }
            }
        }, false);
        apiUtil.excute();

    }

    String getParams(String userid, String bizCategoryCode, String startTime, String endTime) {

        String md5key = Md5Util.getMd5(userid + bizCategoryCode + startTime+endTime + currentPage + "20");
        StringBuilder sb = new StringBuilder();
        sb.append("userId=");
        sb.append(userid);
        sb.append("&");

        sb.append("bizCategoryCode=");
        sb.append(bizCategoryCode);
        sb.append("&");

        sb.append("startTime=");
        sb.append(startTime);
        sb.append("&");

        sb.append("endTime=");
        sb.append(endTime);
        sb.append("&");


        sb.append("CurrentPage=");
        sb.append(currentPage);
        sb.append("&");
        sb.append("PageSize=20&");
        sb.append("Md5Key=");
        sb.append(md5key);
        return sb.toString();
    }

    class MViewHolder {
        TextView tvName;
        TextView tvType;
        TextView tvSub;
    }

    @OnClick({R.id.et_jg_search1, R.id.et_jg_search2, R.id.et_jg_search3})
    public void doClick(View v) {

        switch (v.getId()) {
            case R.id.et_jg_search1:
                startActivityForResult(new Intent(ActJG_ListYCCZ.this, ActDictList.class), 0);

                break;
            case R.id.et_jg_search2:

                pvTime.show(etSearch2);
                break;
            case R.id.et_jg_search3:

                pvTime.show(etSearch3);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            String name = data.getStringExtra("name");
            code = data.getStringExtra("code");
            etSearch1.setText(name);
        }
    }
}
