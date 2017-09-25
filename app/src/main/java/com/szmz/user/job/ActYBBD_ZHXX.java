package com.szmz.user.job;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.entity.UserDB_ZHXX;
import com.szmz.utils.BaseListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 表单账号信息
 */
public class ActYBBD_ZHXX extends ActBase {


    @BindView(R.id.lv)
    ListView lv;
    BaseListAdapter<UserDB_ZHXX,MViewHolder> adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ybbd__zhxx;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("账户信息");

        adapter = new BaseListAdapter<UserDB_ZHXX, MViewHolder>(this,R.layout.list_item_db_zhxx) {
            @Override
            protected void refreshView(int postion, UserDB_ZHXX item, MViewHolder holer) {


            }

            @Override
            protected MViewHolder getHolder(View converView) {

                MViewHolder holder = new MViewHolder();
                holder.tvTime = (TextView) converView.findViewById(R.id.tv_db_zhxx_zt);
                holder.tvType = (TextView) converView.findViewById(R.id.tv_db_zhxx_type);
                holder.tvMoneyAll = (TextView) converView.findViewById(R.id.tv_db_zhxx_moneyall);
                holder.tvMoneyLess = (TextView) converView.findViewById(R.id.tv_db_zhxx_moneyless);


                return null;
            }
        };
        List<UserDB_ZHXX> items = new ArrayList<>();
        for (int i=0;i<4;i++){
            items.add(new UserDB_ZHXX());
        }
        adapter.setItems(items);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    class MViewHolder{
        TextView tvTime;
        TextView tvType;
        TextView tvMoneyAll;
        TextView tvMoneyLess;
    }
}
