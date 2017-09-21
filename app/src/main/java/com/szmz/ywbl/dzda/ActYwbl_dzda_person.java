package com.szmz.ywbl.dzda;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.szmz.R;
import com.szmz.entity.YwblPerson;
import com.szmz.utils.GetData;
import com.szmz.ywbl.ActBaseList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 救助人列表
 */
public class ActYwbl_dzda_person extends ActBaseList<YwblPerson> {


    private Map<Integer, Boolean> map;

    @Override
    public void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("电子档案");
        map = new HashMap<>();
        refresh.autoRefresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ywbl_dzda_person;
    }


    @Override
    protected void doRefreshView(int p, final YwblPerson item, View view) {
        TextView nameTv = (TextView) view.findViewById(R.id.nameTv);
        TextView timeTv = (TextView) view.findViewById(R.id.timeTv);
        TextView countyTv = (TextView) view.findViewById(R.id.countyTv);
        TextView typeTv = (TextView) view.findViewById(R.id.typeNameTv);
        CheckBox cb = (CheckBox) view.findViewById(R.id.cb);
        nameTv.setText(item.getName());
        timeTv.setText(item.getTime());
        countyTv.setText(item.getCounty());
        typeTv.setText(item.getTypeName());

        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    map.put(item.getId(), isChecked);
                } else {
                    map.remove(item.getId());
                }
            }
        });

        if (map != null && map.containsKey(item.getId())) {
            cb.setChecked(map.get(item.getId()));
        } else {
            cb.setChecked(false);
        }
    }

    @Override
    protected int getListItemID() {
        return R.layout.ywbl_person_item;
    }

    @Override
    protected void doListItemOnClick(YwblPerson item) {
        super.doListItemOnClick(item);
        Intent intent = new Intent(this, ActYwbl_dzda_main.class);
        intent.putExtra("YwblPerson", item);
        startActivity(intent);
    }

    @Override
    protected void doMore(boolean isMore) {
        if (isMore) {
            CurrentPage++;
        } else {
            CurrentPage = 1;
        }
        refresh.finishRefresh();
        refresh.finishRefreshLoadMore();
        List<YwblPerson> result = GetData.doGetPersonList();
        if (result != null && result.size() > 0) {
            if (CurrentPage == 1) {
                adapter.clearListData();
            }

            adapter.setListData(result);
            adapter.notifyDataSetChanged();
            noDataLayout.setVisibility(View.GONE);
            if (isHasNextPage(CurrentPage, PageSize, 40)) {
                refresh.setLoadMore(true);
            } else {
                refresh.setLoadMore(false);
            }
        } else {
            adapter.clearListData();
            adapter.setListData(new ArrayList<YwblPerson>());
            adapter.notifyDataSetChanged();
            noDataLayout.setVisibility(View.VISIBLE);
        }
    }
}
