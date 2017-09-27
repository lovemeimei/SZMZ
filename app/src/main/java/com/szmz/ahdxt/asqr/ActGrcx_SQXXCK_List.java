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

public class ActGrcx_SQXXCK_List extends ActBaseList<HdxtGrcxInfo> {


    @Override
    public void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("个人申请");

        refresh.setLoadMore(false);
        refresh.autoRefresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_grcx__sqxxck;
    }

    @Override
    protected void doRefreshView(int p, final HdxtGrcxInfo item, View view) {
        TextView nameTv = (TextView) view.findViewById(R.id.nameTv);
//        nameTv.setText(item.getPcmc());
        TextView timeTv = (TextView) view.findViewById(R.id.sjTv);
        Button button1 = (Button) view.findViewById(R.id.button1);
        Button button2 = (Button) view.findViewById(R.id.button2);
        Button button3 = (Button) view.findViewById(R.id.button3);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActGrcx_SQXXCK_List.this, ActGrcx_SQXXCK_Detail.class);
                intent.putExtra("HdxtGrcxInfo", item);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doToast("下载附件");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doToast("下载报告");
            }
        });
    }

    @Override
    protected int getListItemID() {
        return R.layout.hdxt_grcx_sqr_grxxck_list_item;
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
