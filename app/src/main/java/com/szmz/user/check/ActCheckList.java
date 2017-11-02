package com.szmz.user.check;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.materiallistview.MaterialRefreshLayout;
import com.szmz.ActBase;
import com.szmz.ActListBase;
import com.szmz.R;
import com.szmz.utils.BaseListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ActCheckList extends ActListBase {


    @BindView(R.id.lv)
    ListView lv;

    private BaseListAdapter<String ,MViewHolder> adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.comm_list;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("申请事项");
        adapter = new BaseListAdapter<String, MViewHolder>(this,R.layout.list_item_user_check) {
            @Override
            protected void refreshView(int postion, String item, MViewHolder holer) {

                holer.tvBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        trans(ActCheckDetail1.class);
                    }
                });
            }

            @Override
            protected MViewHolder getHolder(View converView) {
                MViewHolder holder = new MViewHolder();
                holder.tvBtn = (TextView) converView.findViewById(R.id.tv_btn);
                holder.tvName = (TextView)converView.findViewById(R.id.tv_content);
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
        refresh.finishRefresh();
    }

    @Override
    public void doRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
refresh.finishRefreshLoadMore();
    }

    class MViewHolder{
        TextView tvName;
        TextView tvBtn;
    }
}
