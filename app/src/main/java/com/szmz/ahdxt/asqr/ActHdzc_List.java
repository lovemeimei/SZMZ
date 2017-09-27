package com.szmz.ahdxt.asqr;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.szmz.R;
import com.szmz.ahdxt.zlgl.ActZlgl_Detial;
import com.szmz.entity.HdxtZlglInfo;
import com.szmz.utils.GetData;
import com.szmz.ywbl.ActBaseList;

import java.util.ArrayList;
import java.util.List;

public class ActHdzc_List extends ActBaseList<HdxtZlglInfo> {


    @Override
    public void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("核对政策");


        refresh.autoRefresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_hdzc__list;
    }

    @Override
    protected void doListItemOnClick(HdxtZlglInfo item) {
        super.doListItemOnClick(item);
        Intent intent = new Intent(this, ActZlgl_Detial.class);
        intent.putExtra("HdxtZlglInfo", item);
        startActivity(intent);
    }

    @Override
    protected void doRefreshView(int p, HdxtZlglInfo item, View view) {
        TextView nameTv = (TextView) view.findViewById(R.id.nameTv);
//        nameTv.setText(item.getName());

    }

    @Override
    protected int getListItemID() {
        return R.layout.hdxt_zlgl_list_item;
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
