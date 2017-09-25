package com.szmz.ywbl.dzda;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.szmz.R;
import com.szmz.entity.YwblJtcy;
import com.szmz.entity.YwblPerson;
import com.szmz.ywbl.ActBaseList;

import java.util.ArrayList;
import java.util.List;

/**
 * 家庭成员信息列表
 */
public class ActYwbl_dzda_jtcyxx extends ActBaseList<YwblJtcy> {

    private YwblPerson person;

    @Override
    public void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("家庭成员信息");
        person = (YwblPerson) getIntent().getSerializableExtra("YwblPerson");
        refresh.setLoadMore(false);
        refresh.autoRefresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ywbl_dzda_jtcyxx;
    }

    @Override
    protected void doRefreshView(int p, final YwblJtcy item, View view) {
        TextView nameTv = (TextView) view.findViewById(R.id.nameTv);
        TextView sfzhTv = (TextView) view.findViewById(R.id.sfzhTv);
        Button button = (Button) view.findViewById(R.id.button);
        nameTv.setText(item.getName() + "," + item.getXb());
        sfzhTv.setText(item.getSfzh());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActYwbl_dzda_jtcyxx.this, ActYwbl_dzda_jtcyxx_detail.class);
                intent.putExtra("YwblJtcy", item);
                startActivity(intent);
            }
        });


    }

    @Override
    protected int getListItemID() {
        return R.layout.ywbl_jtcyxx_item;
    }

    @Override
    protected void doMore(boolean isMore) {

        refresh.finishRefresh();
        refresh.finishRefreshLoadMore();
        List<YwblJtcy> result = person.getJtcyList();
        if (result != null && result.size() > 0) {

            adapter.clearListData();
            adapter.setListData(result);
            adapter.notifyDataSetChanged();
            noDataLayout.setVisibility(View.GONE);

        } else {
            adapter.clearListData();
            adapter.setListData(new ArrayList<YwblJtcy>());
            adapter.notifyDataSetChanged();
            noDataLayout.setVisibility(View.VISIBLE);
        }
    }
}
