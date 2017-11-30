package com.szmz.ywbl.dzda;

import android.view.View;

import com.szmz.R;
import com.szmz.entity.YwblDzdaFamily;
import com.szmz.entity.YwblDzdaFamilyFcxx;
import com.szmz.widget.MyLayoutView;
import com.szmz.ywbl.ActBaseList;

import java.util.ArrayList;
import java.util.List;

/**
 * 家庭房产信息
 */

public class ActYwbl_dzda_jtfcxx extends ActBaseList<YwblDzdaFamilyFcxx> {


    private YwblDzdaFamily person;

    @Override
    protected void doRefreshView(int p, YwblDzdaFamilyFcxx item, View view) {
        MyLayoutView fwdzView = (MyLayoutView) view.findViewById(R.id.fwdzView);
        MyLayoutView fwmjView = (MyLayoutView) view.findViewById(R.id.fwmjView);
        MyLayoutView fwtsView = (MyLayoutView) view.findViewById(R.id.fwtsView);
        MyLayoutView fwxzView = (MyLayoutView) view.findViewById(R.id.fwxzView);
        MyLayoutView fwlxView = (MyLayoutView) view.findViewById(R.id.fwlxView);
        fwdzView.doSetContent(item.getAddress());
        fwmjView.doSetContent(item.getHousesize());
        fwtsView.doSetContent(item.getEcount());
        fwxzView.doSetContent(item.getPropertyName());
        fwlxView.doSetContent(item.getType());


    }

    @Override
    protected int getListItemID() {
        return R.layout.list_item_ywbl_jtfcxx;
    }

    @Override
    protected void doMore(boolean isMore) {
        refresh.finishRefresh();
        refresh.finishRefreshLoadMore();
        List<YwblDzdaFamilyFcxx> result = person.getFamilyFcxx();
        if (result != null && result.size() > 0) {
            adapter.clearListData();
            adapter.setListData(result);
            adapter.notifyDataSetChanged();
            noDataLayout.setVisibility(View.GONE);

        } else {
            adapter.clearListData();
            adapter.setListData(new ArrayList<YwblDzdaFamilyFcxx>());
            adapter.notifyDataSetChanged();
            noDataLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("家庭房产信息");
        person = (YwblDzdaFamily) getIntent().getSerializableExtra("YwblPerson");
        refresh.setLoadMore(false);
        refresh.autoRefresh();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ywbl_dzda_jtfcxx;
    }


}

