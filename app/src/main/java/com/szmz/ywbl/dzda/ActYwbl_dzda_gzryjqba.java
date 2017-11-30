package com.szmz.ywbl.dzda;

import android.view.View;

import com.szmz.R;
import com.szmz.entity.YwblDzdaFamily;
import com.szmz.entity.YwblDzdaFamilyShipPer;
import com.szmz.widget.MyLayoutView;
import com.szmz.ywbl.ActBaseList;

import java.util.ArrayList;
import java.util.List;

/**
 * 工作人员近亲备案
 */
public class ActYwbl_dzda_gzryjqba extends ActBaseList<YwblDzdaFamilyShipPer> {

    private YwblDzdaFamily person;

    @Override
    public void initUI() {
        super.initUI();
        setLeftVisible(true);
        setLeftVisible(true);
        setTitle("工作人员近亲备案");
        person = (YwblDzdaFamily) getIntent().getSerializableExtra("YwblPerson");
        refresh.setLoadMore(false);
        refresh.autoRefresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ywbl_dzda_gzryjqba;
    }

    @Override
    protected void doRefreshView(int p, final YwblDzdaFamilyShipPer item, View view) {
        MyLayoutView nameView = (MyLayoutView) view.findViewById(R.id.nameView);
        MyLayoutView idcardView = (MyLayoutView) view.findViewById(R.id.idcardView);
        MyLayoutView gxView = (MyLayoutView) view.findViewById(R.id.gxView);
        nameView.doSetContent(item.getMemberName());
        idcardView.doSetContent(item.getMemberIdcard());
        gxView.doSetContent(item.getRelation());


    }

    @Override
    protected int getListItemID() {
        return R.layout.list_item_ywbl_gzryjqba;
    }

    @Override
    protected void doMore(boolean isMore) {

        refresh.finishRefresh();
        refresh.finishRefreshLoadMore();
        List<YwblDzdaFamilyShipPer> result = person.getFamilyShipPerInfo();
        if (result != null && result.size() > 0) {
            adapter.clearListData();
            adapter.setListData(result);
            adapter.notifyDataSetChanged();
            noDataLayout.setVisibility(View.GONE);

        } else {
            adapter.clearListData();
            adapter.setListData(new ArrayList<YwblDzdaFamilyShipPer>());
            adapter.notifyDataSetChanged();
            noDataLayout.setVisibility(View.VISIBLE);
        }
    }
}
