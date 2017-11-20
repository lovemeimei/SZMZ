package com.szmz.ywbl.dzda;

import android.view.View;

import com.szmz.R;
import com.szmz.entity.YwblDzdaFamily;
import com.szmz.entity.YwblDzdaFamilyIncome;
import com.szmz.widget.MyLayoutView;
import com.szmz.ywbl.ActBaseList;

import java.util.ArrayList;
import java.util.List;

/**
 * 家庭收入信息
 */
public class ActYwbl_dzda_jtsrxx extends ActBaseList<YwblDzdaFamilyIncome> {


    private YwblDzdaFamily person;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ywbl_dzda_jtsrxx;
    }

    @Override
    protected void doRefreshView(int p, YwblDzdaFamilyIncome item, View view) {
        MyLayoutView srxmView = (MyLayoutView) view.findViewById(R.id.srxmView);
        MyLayoutView srlxView = (MyLayoutView) view.findViewById(R.id.srlxView);
        MyLayoutView srjeView = (MyLayoutView) view.findViewById(R.id.srjeView);
        MyLayoutView ysrView = (MyLayoutView) view.findViewById(R.id.ysrView);
        MyLayoutView nsrView = (MyLayoutView) view.findViewById(R.id.nsrView);
        MyLayoutView hzxmView = (MyLayoutView) view.findViewById(R.id.hzxmView);
        srxmView.doSetContent(item.getIncomeproject());
        srlxView.doSetContent(item.getIncomeType());
        srjeView.doSetContent(item.getIncomeamount());
        ysrView.doSetContent(item.getMonthincome());
        nsrView.doSetContent(item.getYearincome());
        hzxmView.doSetContent(item.getName());
    }

    @Override
    protected int getListItemID() {
        return R.layout.list_item_ywbl_jtsrxx;
    }

    @Override
    protected void doMore(boolean isMore) {

        refresh.finishRefresh();
        refresh.finishRefreshLoadMore();
        List<YwblDzdaFamilyIncome> result = person.getFamilyIncomeInfo();
        if (result != null && result.size() > 0) {
            adapter.clearListData();
            adapter.setListData(result);
            adapter.notifyDataSetChanged();
            noDataLayout.setVisibility(View.GONE);

        } else {
            adapter.clearListData();
            adapter.setListData(new ArrayList<YwblDzdaFamilyIncome>());
            adapter.notifyDataSetChanged();
            noDataLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("家庭收入信息");
        person = (YwblDzdaFamily) getIntent().getSerializableExtra("YwblPerson");
        refresh.setLoadMore(false);
        refresh.autoRefresh();

    }
}
