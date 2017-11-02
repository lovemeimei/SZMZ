package com.szmz.search;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.materiallistview.MaterialRefreshLayout;
import com.szmz.ActListBase;
import com.szmz.R;
import com.szmz.utils.BaseListAdapter;
import com.szmz.utils.UIUtil;
import com.szmz.widget.SearchEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/11 0011下午 3:08
 */

public class ActHistoryList extends ActListBase{

    @BindView(R.id.lv)
    ListView lv;

    @BindView(R.id.search_ed)
    SearchEditText etSearch;


 
    BaseListAdapter<String,ActHistoryList.MViewHolder> adapter;

    @Override
    protected void initUI() {
        super.initUI();
        UIUtil.hideInputMethod(this);

        setLeftVisible(true);
        setTitle("历史记录");

        adapter = new BaseListAdapter<String, ActHistoryList.MViewHolder>(this,R.layout.list_item_history) {
            @Override
            protected void refreshView(int postion, String item, ActHistoryList.MViewHolder holer) {


//                holer.tvName.setText("");
                holer.tvName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        trans(ActSearchDetail.class);
                    }
                });
            }

            @Override
            protected ActHistoryList.MViewHolder getHolder(View converView) {

                ActHistoryList.MViewHolder holder = new ActHistoryList.MViewHolder();
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
    protected int getLayoutId() {
        return R.layout.comm_list;
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
    }
}
