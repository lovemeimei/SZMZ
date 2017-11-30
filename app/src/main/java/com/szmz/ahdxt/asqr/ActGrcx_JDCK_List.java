package com.szmz.ahdxt.asqr;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.HdxtGrcxInfo;
import com.szmz.entity.response.HD_SQR_GRCX_JDCK_RES;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.Md5Util;
import com.szmz.ywbl.ActBaseList;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

public class ActGrcx_JDCK_List extends ActBaseList<HdxtGrcxInfo> {


    @Override
    public void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("申请事项");
        refresh.setLoadMore(false);
        refresh.autoRefresh();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_grcx__jdck;
    }


    @Override
    protected void doRefreshView(int p, final HdxtGrcxInfo item, View view) {
        TextView nameTv = (TextView) view.findViewById(R.id.nameTv);
        nameTv.setText(item.getApplyName());
        TextView typeTv = (TextView) view.findViewById(R.id.typeTv);
        typeTv.setText(item.getBizCategory());
        TextView timeTv = (TextView) view.findViewById(R.id.timeTv);
        timeTv.setText(item.getEntrustTime());
        TextView button = (TextView) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActGrcx_JDCK_List.this, ActGrcx_JDCK_Detail.class);
                intent.putExtra("HdxtGrcxInfo", item);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getListItemID() {
        return R.layout.hdxt_grcx_sqr_list_item;
    }

    @Override
    protected void doMore(boolean isMore) {
        if (isMore) {
            CurrentPage++;
        } else {
            CurrentPage = 1;
        }
        doGetData();
    }


    private void doGetData() {
        String params = getParams(App.getInstance().getLoginUser().getIdCode(), CurrentPage);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), params.getBytes());
        Call<HD_SQR_GRCX_JDCK_RES> call = App.getApiProxy().getApplyProgressList(requestBody);
        ApiUtil<HD_SQR_GRCX_JDCK_RES> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<HD_SQR_GRCX_JDCK_RES>() {
            @Override
            public void doSuccess(HD_SQR_GRCX_JDCK_RES response) {
                List<HdxtGrcxInfo> result = response.Result;
                if (result != null && result.size() > 0) {
                    if (CurrentPage == 1) {
                        adapter.clearListData();
                    }

                    adapter.setListData(result);
                    adapter.notifyDataSetChanged();
                    noDataLayout.setVisibility(View.GONE);
                    if (isHasNextPage(CurrentPage, PageSize, 20)) {
                        refresh.setLoadMore(true);
                    } else {
                        refresh.setLoadMore(false);
                    }
                } else {
                    adapter.clearListData();
                    adapter.setListData(new ArrayList<HdxtGrcxInfo>());
                    adapter.notifyDataSetChanged();
                    noDataLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void doAfter() {
                super.doAfter();
                refresh.finishRefresh();
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
