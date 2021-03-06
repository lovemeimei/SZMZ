package com.szmz.ahdxt.asqr;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.szmz.R;
import com.szmz.ahdxt.xxtz.ActXxtz_Detail;
import com.szmz.entity.HdxtZlglInfo;
import com.szmz.utils.GetData;
import com.szmz.ywbl.ActBaseList;

import java.util.ArrayList;
import java.util.List;

public class ActXXTZ_List extends ActBaseList<HdxtZlglInfo> {

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
    protected void doListItemOnClick(HdxtZlglInfo item) {
        Intent intent = new Intent(this, ActXxtz_Detail.class);
        intent.putExtra("HdxtZlglInfo", item);
        startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_xxtz__list;
    }

    @Override
    protected void doRefreshView(int p, HdxtZlglInfo item, View view) {
        TextView name = (TextView) view.findViewById(R.id.nameTv);
        ImageView iv = (ImageView) view.findViewById(R.id.imageView);
        TextView typeTv = (TextView) view.findViewById(R.id.typeTv);
        ImageView dotIv = (ImageView) view.findViewById(R.id.dotIv);
        name.setText(item.getName());
        typeTv.setText(item.getType());
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
        if (isMore) {
            CurrentPage++;
        } else {
            CurrentPage = 1;
        }
        refresh.finishRefresh();
        refresh.finishRefreshLoadMore();
        List<HdxtZlglInfo> result = GetData.getHdxtZlglInfoList();
        if (result != null && result.size() > 0) {
            if (CurrentPage == 1) {
                adapter.clearListData();
            }

            adapter.setListData(result);
            adapter.notifyDataSetChanged();
            noDataLayout.setVisibility(View.GONE);
            if (isHasNextPage(CurrentPage, PageSize, 1)) {
                refresh.setLoadMore(true);
            } else {
                refresh.setLoadMore(false);
            }
        } else {
            adapter.clearListData();
            adapter.setListData(new ArrayList<HdxtZlglInfo>());
            adapter.notifyDataSetChanged();
            noDataLayout.setVisibility(View.VISIBLE);
        }
    }
}
