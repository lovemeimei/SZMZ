package com.szmz.ahdxt.xxtz;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.response.HD_XXTZ;
import com.szmz.entity.response.HD_hdzc;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.Md5Util;
import com.szmz.ywbl.ActBaseList;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

public class ActXxtz_List extends ActBaseList<HD_XXTZ.ReaultBean> {

    int type = 0;

    @Override
    public void initUI() {
        super.initUI();
        setLeftVisible(true);
        type = getIntent().getIntExtra("Type", 0);
        switch (type) {
            case 1:
                setTitle("核对待处理");
                break;
            case 2:
                setTitle("复核待处理");
                break;
            case 3:
                setTitle("超时提醒");
                break;
            case 4:
                setTitle("敏感名单审核提醒");
                break;

        }
        refresh.autoRefresh();
    }

    @Override
    protected void doListItemOnClick(HD_XXTZ.ReaultBean item) {
        Intent intent = new Intent(this, ActXxtz_Detail.class);
        intent.putExtra("item", item);
        intent.putExtra("type",type);
        startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_xxtz_list;
    }

    @Override
    protected void doRefreshView(int p, HD_XXTZ.ReaultBean item, View view) {
        TextView name = (TextView) view.findViewById(R.id.nameTv);
        ImageView iv = (ImageView) view.findViewById(R.id.imageView);
        TextView typeTv = (TextView) view.findViewById(R.id.typeTv);
        ImageView dotIv = (ImageView) view.findViewById(R.id.dotIv);
        name.setText(item.getBatchName());
        typeTv.setText(item.getBizCategory());
        switch (type) {
            case 1:
                iv.setImageResource(R.drawable.icon_hddcl);
                break;
            case 2:
                iv.setImageResource(R.drawable.icon_fhdcl);
                break;
            case 3:
                iv.setImageResource(R.drawable.icon_cstx);
                break;
            case 4:
                iv.setImageResource(R.drawable.icon_mgmdshtx);
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
        params = getParams("510401", CurrentPage);

        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), params.getBytes());
        Call<HD_XXTZ> call = null;
        if (type == 1) {
            call = App.getApiProxy().getHD_XXTZ1(requestBody);
        } else if (type == 2) {
            call = App.getApiProxy().getHD_XXTZ2(requestBody);
        } else if (type == 3) {
            call = App.getApiProxy().getHD_XXTZ3(requestBody);
        }else if (type ==4) {
            call = App.getApiProxy().getHD_XXTZ4(requestBody);
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

    private String getParams(String userid, int currentPage) {
        //userId=510401&CurrentPage=1&PageSize=20&Md5Key=083EB02F6F37E332F7F75789EF2D71DF
        String md5key = Md5Util.getMd5(userid + currentPage + "20");
        StringBuilder sb = new StringBuilder();
        sb.append("userId=");
        sb.append(userid);
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
