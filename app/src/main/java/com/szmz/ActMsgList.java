package com.szmz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.materiallistview.MaterialRefreshLayout;
import com.szmz.user.job.ActMyJobYB;
import com.szmz.user.job.ActYBBDList;
import com.szmz.utils.BaseListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ActMsgList extends ActListBase {

    private String title;
    @BindView(R.id.lv)
    ListView lv;
    BaseListAdapter<String,ActMsgList.MViewHolder> adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_msg_list;
    }


    @Override
    protected void initUI() {
        super.initUI();
        title= getIntent().getStringExtra("title");
        setTitle(title);
        setLeftVisible(true);

        adapter = new BaseListAdapter<String, ActMsgList.MViewHolder>(this,R.layout.comm_list_item) {
            @Override
            protected void refreshView(int postion, String item, ActMsgList.MViewHolder holer) {


//                holer.tvName.setText("");
                holer.tvName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        trans(ActMsgDetail.class,title,"");
                    }
                });
            }

            @Override
            protected ActMsgList.MViewHolder getHolder(View converView) {

                ActMsgList.MViewHolder holder = new ActMsgList.MViewHolder();
                holder.tvName = (TextView) converView.findViewById(R.id.tv_name);

                return holder;
            }
        };
        lv.setAdapter(adapter);
        List<String> items = new ArrayList<>();
        for (int i=0;i<10;i++){
            items.add(""+i);
        }
        adapter.setItems(items);
    }

    @Override
    public void doRefresh(MaterialRefreshLayout materialRefreshLayout) {

    }

    @Override
    public void doRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {

    }

    class MViewHolder{

        TextView tvName;
    }
}
