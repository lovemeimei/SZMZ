package com.szmz.user.job;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.utils.BaseListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 已办
 */
public class ActMyJobYB extends ActBase {

    @BindView(R.id.lv)
    ListView lv;

    BaseListAdapter<String,MViewHolder> adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_my_job_yb;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setTitle("已办业务");
        setLeftVisible(true);

        adapter = new BaseListAdapter<String, MViewHolder>(this,R.layout.list_item_job_yb) {
            @Override
            protected void refreshView(int postion, String item, MViewHolder holer) {


//                holer.tvName.setText("");
                holer.tvName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        trans(ActYBBDList.class);
                    }
                });
            }

            @Override
            protected MViewHolder getHolder(View converView) {

                MViewHolder holder = new MViewHolder();
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

    class MViewHolder{

        TextView tvName;
    }

}
