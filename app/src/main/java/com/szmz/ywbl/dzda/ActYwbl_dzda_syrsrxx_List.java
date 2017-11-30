package com.szmz.ywbl.dzda;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.szmz.R;
import com.szmz.entity.YwblDzdaFamily;
import com.szmz.entity.YwblDzdaSupportIncome;
import com.szmz.ywbl.ActBaseList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bz on 2017/11/8.
 */

public class ActYwbl_dzda_syrsrxx_List extends ActBaseList<YwblDzdaSupportIncome> {

    private YwblDzdaFamily person;

    @Override
    public void initUI() {
        super.initUI();
        setLeftVisible(true);
        setLeftVisible(true);
        setTitle("赡（扶、抚）养人收入信息");
        person = (YwblDzdaFamily) getIntent().getSerializableExtra("YwblPerson");
        refresh.setLoadMore(false);
        refresh.autoRefresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ywbl_dzda_jtcyxx;
    }

    @Override
    protected void doRefreshView(int p, final YwblDzdaSupportIncome item, View view) {
        TextView nameTv = (TextView) view.findViewById(R.id.nameTv);
        TextView sfzhTv = (TextView) view.findViewById(R.id.sfzhTv);
        Button button = (Button) view.findViewById(R.id.button);
        nameTv.setText(item.getSname() + "," + item.getSsex());
        sfzhTv.setText(item.getSidcard());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActYwbl_dzda_syrsrxx_List.this, ActYwbl_dzda_syrsrxx.class);
                intent.putExtra("YwblDzdaSupportIncome", item);
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
        List<YwblDzdaSupportIncome> result = person.getFamilySupportInfo();
        if (result != null && result.size() > 0) {
            adapter.clearListData();
            adapter.setListData(result);
            adapter.notifyDataSetChanged();
            noDataLayout.setVisibility(View.GONE);

        } else {
            adapter.clearListData();
            adapter.setListData(new ArrayList<YwblDzdaSupportIncome>());
            adapter.notifyDataSetChanged();
            noDataLayout.setVisibility(View.VISIBLE);
        }
    }
}
