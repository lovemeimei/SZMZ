package com.szmz.ywbl.dzda;

import android.view.View;

import com.szmz.R;
import com.szmz.entity.YwblDzdaFamily;
import com.szmz.entity.YwblDzdaFamilyProperty;
import com.szmz.widget.MyLayoutView;
import com.szmz.ywbl.ActBaseList;

import java.util.ArrayList;
import java.util.List;

/**
 * 家庭财产信息
 */
public class ActYwbl_dzda_jtccxx extends ActBaseList<YwblDzdaFamilyProperty> {


    private YwblDzdaFamily person;

    @Override
    protected void doRefreshView(int p, YwblDzdaFamilyProperty item, View view) {
        MyLayoutView cclxView = (MyLayoutView) view.findViewById(R.id.cclxView);
        MyLayoutView slView = (MyLayoutView) view.findViewById(R.id.slView);
        MyLayoutView jzView = (MyLayoutView) view.findViewById(R.id.jzView);
        MyLayoutView fwxzView = (MyLayoutView) view.findViewById(R.id.fwxzView);
        MyLayoutView fwmjView = (MyLayoutView) view.findViewById(R.id.fwmjView);
        cclxView.doSetContent(item.getPropertyType());
        slView.doSetContent(item.getCount());
        jzView.doSetContent(item.getValue());
        fwxzView.doSetContent(item.getHousetype());
        fwmjView.doSetContent(item.getHousesize());

    }

    @Override
    protected int getListItemID() {
        return R.layout.list_item_ywbl_jtccxx;
    }

    @Override
    protected void doMore(boolean isMore) {
        refresh.finishRefresh();
        refresh.finishRefreshLoadMore();
        List<YwblDzdaFamilyProperty> result = person.getFamilyPropertyInfo();
        if (result != null && result.size() > 0) {
            adapter.clearListData();
            adapter.setListData(result);
            adapter.notifyDataSetChanged();
            noDataLayout.setVisibility(View.GONE);

        } else {
            adapter.clearListData();
            adapter.setListData(new ArrayList<YwblDzdaFamilyProperty>());
            adapter.notifyDataSetChanged();
            noDataLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("家庭财产信息");
        person = (YwblDzdaFamily) getIntent().getSerializableExtra("YwblPerson");
        refresh.setLoadMore(false);
        refresh.autoRefresh();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ywbl_jtccxx;
    }


}
