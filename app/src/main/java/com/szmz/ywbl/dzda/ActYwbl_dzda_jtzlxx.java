package com.szmz.ywbl.dzda;

import android.view.View;

import com.szmz.R;
import com.szmz.entity.YwblDzdaFamily;
import com.szmz.entity.YwblDzdaFamilyMaterial;
import com.szmz.widget.MyLayoutView;
import com.szmz.ywbl.ActBaseList;

import java.util.ArrayList;
import java.util.List;

/**
 * 家庭资料信息
 */
public class ActYwbl_dzda_jtzlxx extends ActBaseList<YwblDzdaFamilyMaterial> {


    YwblDzdaFamily person;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ywbl_daza_jtzlxx;
    }

    @Override
    protected void doRefreshView(int p, YwblDzdaFamilyMaterial item, View view) {
        MyLayoutView zlmView = (MyLayoutView) view.findViewById(R.id.zlmView);
        MyLayoutView zllbView = (MyLayoutView) view.findViewById(R.id.zllbView);
        MyLayoutView zldzView = (MyLayoutView) view.findViewById(R.id.zldzView);
        zlmView.doSetContent(item.getFilename());
        zllbView.doSetContent(item.getMaterialType());
        zldzView.doSetContent(item.getFileurl());
    }

    @Override
    protected int getListItemID() {
        return R.layout.list_item_ywbl_jtzlxx;
    }

    @Override
    protected void doMore(boolean isMore) {

        refresh.finishRefresh();
        refresh.finishRefreshLoadMore();
        List<YwblDzdaFamilyMaterial> result = person.getFamilyMaterialInfo();
        if (result != null && result.size() > 0) {
            adapter.clearListData();
            adapter.setListData(result);
            adapter.notifyDataSetChanged();
            noDataLayout.setVisibility(View.GONE);

        } else {
            adapter.clearListData();
            adapter.setListData(new ArrayList<YwblDzdaFamilyMaterial>());
            adapter.notifyDataSetChanged();
            noDataLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("家庭资料信息");
        person = (YwblDzdaFamily) getIntent().getSerializableExtra("YwblPerson");
        refresh.setLoadMore(false);
        refresh.autoRefresh();
    }

}
