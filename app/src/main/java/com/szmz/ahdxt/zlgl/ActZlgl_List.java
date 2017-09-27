package com.szmz.ahdxt.zlgl;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.szmz.R;
import com.szmz.entity.HdxtZlglInfo;
import com.szmz.utils.GetData;
import com.szmz.ywbl.ActBaseList;

import java.util.ArrayList;
import java.util.List;

/**
 * 资料管理数据列表
 */
public class ActZlgl_List extends ActBaseList<HdxtZlglInfo> {


    int type = 0;

    @Override
    public void initUI() {
        super.initUI();
        setLeftVisible(true);
        type = getIntent().getIntExtra("Type", 0);
        switch (type) {
            case 1:
                setTitle("核对政策");
                break;
            case 2:
                setTitle("保密制度");
                break;
            case 3:
                setTitle("核对业务资料");
                break;

        }
        refresh.autoRefresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_zlgl__list;
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