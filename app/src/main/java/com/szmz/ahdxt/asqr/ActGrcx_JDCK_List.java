package com.szmz.ahdxt.asqr;

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
        nameTv.setText(item.getPcmc());
        Button button = (Button) view.findViewById(R.id.button);
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
