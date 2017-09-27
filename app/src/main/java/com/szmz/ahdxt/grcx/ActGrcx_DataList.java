package com.szmz.ahdxt.grcx;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.szmz.R;
import com.szmz.entity.HdxtGrcxInfo;
import com.szmz.utils.GetData;
import com.szmz.ywbl.ActBaseList;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据列表页面
 */
public class ActGrcx_DataList extends ActBaseList<HdxtGrcxInfo> {

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
    protected void doRefreshView(int p, final HdxtGrcxInfo item, View view) {
        TextView nameTv = (TextView) view.findViewById(R.id.nameTv);
        TextView sfzhTv = (TextView) view.findViewById(R.id.sfzhTv);
        Button button = (Button) view.findViewById(R.id.button);
        nameTv.setText(item.getPcmc());
        sfzhTv.setText(item.getYwlx());
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

    @Override
    protected int getListItemID() {
        return R.layout.hdxt_grcx_datalist_item;
    }

    @Override
    protected void doMore(boolean isMore) {

        refresh.finishRefresh();
        refresh.finishRefreshLoadMore();
        List<HdxtGrcxInfo> result = GetData.getDataList();
        if (result != null && result.size() > 0) {

            adapter.clearListData();
            adapter.setListData(result);
            adapter.notifyDataSetChanged();
            noDataLayout.setVisibility(View.GONE);

        } else {
            adapter.clearListData();
            adapter.setListData(new ArrayList<HdxtGrcxInfo>());
            adapter.notifyDataSetChanged();
            noDataLayout.setVisibility(View.VISIBLE);
        }
    }
}
