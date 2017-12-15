package com.szmz.ahdxt.grcx;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.request.HD_SearchDB;
import com.szmz.entity.response.HD_SearchDB_RES;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.GetData;
import com.szmz.utils.Md5Util;
import com.szmz.ywbl.ActBaseList;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * 数据列表页面
 */
public class ActGrcx_DataList extends ActBaseList<HD_SearchDB_RES.ResultBean> {

    private int type = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_data_list;
    }

    @Override
    public void initUI() {
        super.initUI();
        setLeftVisible(true);
        type = getIntent().getIntExtra("Type", 0);
        switch (type) {
            case 1:
                setTitle("我的待办");
                break;
            case 2:
                setTitle("我的已办");
                break;
            case 3:
                setTitle("退回委托");
                break;
            case 4:
                setTitle("终止退回委托");
                break;
        }

        refresh.setLoadMore(false);
        refresh.autoRefresh();
    }


    @Override
    protected void doRefreshView(int p, final HD_SearchDB_RES.ResultBean item, View view) {
        TextView nameTv = (TextView) view.findViewById(R.id.nameTv);
        TextView sfzhTv = (TextView) view.findViewById(R.id.sfzhTv);
        TextView sfzhTv_status = (TextView) view.findViewById(R.id.sfzhTv_status);
        Button button = (Button) view.findViewById(R.id.button);
        nameTv.setText(item.getBatchName());
        sfzhTv.setText(item.getBizCategory());
        sfzhTv_status.setText(item.getDealNode());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;

                switch (type) {
                    case 1:
                        intent = new Intent(ActGrcx_DataList.this, ActGrcx_DB.class);
                        intent.putExtra("HdxtGrcxInfo", item);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(ActGrcx_DataList.this, ActGrcx_YB.class);
                        intent.putExtra("HdxtGrcxInfo", item);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(ActGrcx_DataList.this, ActGrcx_TH.class);
                        intent.putExtra("HdxtGrcxInfo", item);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(ActGrcx_DataList.this, ActGrcx_ZZ.class);
                        intent.putExtra("HdxtGrcxInfo", item);
                        startActivity(intent);
                        break;
                }
            }
        });


    }

    private void getInfo() {

    }

    @Override
    protected int getListItemID() {
        return R.layout.hdxt_grcx_datalist_item;
    }

    @Override
    protected void doMore(boolean isMore) {


        String params = "";
        if (isMore) {
            CurrentPage++;
        } else {
            CurrentPage = 1;
        }
        params = getParams(App.getInstance().getLoginUser().getAccountHD(), CurrentPage);

        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), params.getBytes());
        Call<HD_SearchDB_RES> call = null;
        if (type == 1) {
            call = App.getApiProxy().getDBlist3(requestBody);
        } else if (type == 2) {
            call = App.getApiProxy().getYBlist(requestBody);
        } else if (type == 3) {
            call = App.getApiProxy().getTHlist(requestBody);
        } else if (type == 4) {
            call = App.getApiProxy().getZZTHlist(requestBody);
        }
        if (call == null)
            return;

        ApiUtil<HD_SearchDB_RES> apiUtil = new ApiUtil<HD_SearchDB_RES>(this, call, new SimpleApiListener<HD_SearchDB_RES>() {
            @Override
            public void doSuccess(HD_SearchDB_RES result) {


                List<HD_SearchDB_RES.ResultBean> list = result.Result;

                if (list != null && list.size() > 0) {

                    noDataLayout.setVisibility(View.GONE);
                    if (CurrentPage == 1) {
                        adapter.clearListData();
                    }
                    adapter.setListData(list);
                    adapter.notifyDataSetChanged();

                } else {
                    adapter.clearListData();
                    adapter.setListData(new ArrayList<HD_SearchDB_RES.ResultBean>());
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
