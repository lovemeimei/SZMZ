package com.szmz.search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.materiallistview.MaterialRefreshLayout;
import com.szmz.ActListBase;
import com.szmz.R;
import com.szmz.entity.SearchJZYE;
import com.szmz.utils.BaseListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 救助余额查询
 */

public class ActListSYJZE extends ActListBase {


    @BindView(R.id.lv)
    ListView lv;
    BaseListAdapter<SearchJZYE,MViewHolder> adapter;
    List<SearchJZYE> items = new ArrayList<>();




    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_list_syjze;
    }


    @Override
    protected void initUI() {
        super.initUI();

        String type = getIntent().getStringExtra("type");
        if (type.equals("1")){
            setTitle("各项救助余额");

        }else {
            setTitle("资金发放记录");

        }
        setLeftVisible(true);

        adapter = new BaseListAdapter<SearchJZYE, MViewHolder>(this,R.layout.list_item_search_jzye) {
            @Override
            protected void refreshView(int postion, SearchJZYE item, MViewHolder holer) {
                holer.tvSub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        trans(ActListSYJZE_Detail.class);
                    }
                });
            }

            @Override
            protected MViewHolder getHolder(View v) {
                MViewHolder holder = new MViewHolder();
                holder.tvMoney = (TextView)v.findViewById(R.id.tv_money);
                holder.tvName = (TextView)v.findViewById(R.id.tv_name);
                holder.tvSub = (TextView)v.findViewById(R.id.tv_submit);
                return holder;
            }
        };

        lv.setAdapter(adapter);

        for (int i=0;i<10;i++){
            SearchJZYE item = new SearchJZYE();
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
        TextView tvSub;
    }
}
