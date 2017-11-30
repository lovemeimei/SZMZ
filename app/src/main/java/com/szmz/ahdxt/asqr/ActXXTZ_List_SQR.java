package com.szmz.ahdxt.asqr;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.response.HD_XXTZ;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.Md5Util;
import com.szmz.ywbl.ActBaseList;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

public class ActXXTZ_List_SQR extends ActBaseList<HD_XXTZ.ReaultBean> {

    int type = 0;

    @Override
    public void initUI() {
        super.initUI();
        setLeftVisible(true);
        type = getIntent().getIntExtra("Type", 0);
        switch (type) {
            case 1:
                setTitle("申请状态");
                break;
            case 2:
                setTitle("报告明细");
                break;

        }
        refresh.autoRefresh();
    }

    @Override
    protected void doListItemOnClick(HD_XXTZ.ReaultBean item) {
        Intent intent = new Intent(this, ActXXTZ_Detail_SQR.class);
        intent.putExtra("HD_XXTZ", item);
        intent.putExtra("Type", type);
        startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_xxtz__list;
    }

    @Override
    protected void doRefreshView(int p, HD_XXTZ.ReaultBean item, View view) {
        TextView name = (TextView) view.findViewById(R.id.nameTv);
        ImageView iv = (ImageView) view.findViewById(R.id.imageView);
        TextView typeTv = (TextView) view.findViewById(R.id.typeTv);
        ImageView dotIv = (ImageView) view.findViewById(R.id.dotIv);
        name.setText(item.getBizCategory());
        typeTv.setText(item.getApplyName());
        switch (type) {
            case 1:
                iv.setImageResource(R.drawable.icon_hddcl);
                break;
            case 2:
                iv.setImageResource(R.drawable.icon_hdzc);
                break;

        }

    }

    @Override
    protected int getListItemID() {
        return R.layout.hdxt_xxtz_list_item;
    }

    @Override
    protected void doMore(boolean isMore) {

        String params = "";
        if (isMore) {
            CurrentPage++;
        } else {
            CurrentPage = 1;
        }
        params = getParams(App.getInstance().getLoginUser().getIdCode(), CurrentPage);

        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), params.getBytes());
        Call<HD_XXTZ> call = null;
        if (type == 1) {
            call = App.getApiProxy().getXXTZList1(requestBody);
        } else if (type == 2) {
            call = App.getApiProxy().getXXTZList2(requestBody);
        }
        if (call == null)
            return;

        ApiUtil<HD_XXTZ> apiUtil = new ApiUtil<HD_XXTZ>(this, call, new SimpleApiListener<HD_XXTZ>() {
            @Override
            public void doSuccess(HD_XXTZ result) {
                List<HD_XXTZ.ReaultBean> list = result.Result;

                if (list != null && list.size() > 0) {

                    noDataLayout.setVisibility(View.GONE);
                    if (CurrentPage == 1) {
                        adapter.clearListData();
                    }
                    adapter.setListData(list);
                    adapter.notifyDataSetChanged();

                } else {
                    adapter.clearListData();
                    adapter.setListData(new ArrayList<HD_XXTZ.ReaultBean>());
                    adapter.notifyDataSetChanged();
                    noDataLayout.setVisibility(View.VISIBLE);
                }
                if (isHasNextPage(CurrentPage, PageSize, result.TotalNum)) {
                    refresh.setLoadMore(true);
                } else {
                    refresh.setLoadMore(false);
                }
            }

            @Override
            public void doAfter() {
                super.doAfter();
                refresh.finishRefreshing();
                refresh.finishRefreshLoadMore();
            }
        }, false);

        apiUtil.excute();
    }

    private String getParams(String idCardNo, int currentPage) {
        String md5key = Md5Util.getMd5(idCardNo + currentPage + "20");
        StringBuilder sb = new StringBuilder();
        sb.append("idCardNo=");
        sb.append(idCardNo);
        sb.append("&");
        sb.append("CurrentPage=");
        sb.append(currentPage);
        sb.append("&");
        sb.append("PageSize=20&");
        sb.append("Md5Key=");
        sb.append(md5key);
        return sb.toString();
    }
}
