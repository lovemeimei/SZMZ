package com.szmz.search;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.materiallistview.MaterialRefreshLayout;
import com.szmz.ActListBase;
import com.szmz.R;
import com.szmz.entity.SearchJZYE_Detal;
import com.szmz.utils.BaseListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 救助余额查询
 */

public class ActListSYJZE_Detail extends ActListBase {


    @BindView(R.id.lv)
    ListView lv;
    BaseListAdapter<SearchJZYE_Detal,MViewHolder> adapter;
    List<SearchJZYE_Detal> items = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_list_syjze;
    }


    @Override
    protected void initUI() {
        super.initUI();
        setTitle("费用明细");
        setLeftVisible(true);

        adapter = new BaseListAdapter<SearchJZYE_Detal, MViewHolder>(this,R.layout.list_item_search_jzye2) {
            @Override
            protected void refreshView(int postion, SearchJZYE_Detal item, MViewHolder holer) {

            }

            @Override
            protected MViewHolder getHolder(View v) {
                MViewHolder holder = new MViewHolder();
                holder.tvMoney = (TextView)v.findViewById(R.id.tv_money);
                holder.tvName = (TextView)v.findViewById(R.id.tv_name);
                holder.tvTime = (TextView)v.findViewById(R.id.tv_time);
                return holder;
            }
        };

        lv.setAdapter(adapter);

        for (int i=0;i<10;i++){
            SearchJZYE_Detal item = new SearchJZYE_Detal();
            items.add(item);
        }
        adapter.setItems(items);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void doRefresh(MaterialRefreshLayout materialRefreshLayout) {

    }

    @Override
    public void doRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {

    }

    class MViewHolder{
        TextView tvName;
        TextView tvMoney;
        TextView tvTime;
    }
}
