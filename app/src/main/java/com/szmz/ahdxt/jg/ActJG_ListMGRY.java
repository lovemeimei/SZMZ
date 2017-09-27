package com.szmz.ahdxt.jg;

import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.materiallistview.MaterialRefreshLayout;
import com.szmz.ActListBase;
import com.szmz.R;
import com.szmz.entity.TestMode;
import com.szmz.utils.BaseListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 监管敏感人员
 */
public class ActJG_ListMGRY extends ActListBase {


    @BindView(R.id.lv)
    ListView lv;
    BaseListAdapter<TestMode,MViewHolder> adapter;
    List<TestMode> items = new ArrayList<>();

    @BindView(R.id.tv_jg_search1)
    TextView tvSearch1;
    @BindView(R.id.tv_jg_search2)
    TextView tvSearch2;
    @BindView(R.id.et_jg_search1)
    EditText etSearch1;
    @BindView(R.id.et_jg_search2)
    EditText etSearch2;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_jc__list;
    }


    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("敏感人员");
        setRightVisible(true);
        setRightShow("搜索");

        tvSearch1.setText("批次名称");
        tvSearch2.setText("业务类型");


        adapter = new BaseListAdapter<TestMode, MViewHolder>(this,R.layout.list_item_jg_dybg) {
            @Override
            protected void refreshView(int postion, TestMode item, MViewHolder holer) {

                holer.tvSub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        trans(ActJG_ListMGRY2.class);
                    }
                });
            }

            @Override
            protected MViewHolder getHolder(View v) {

                MViewHolder holder = new MViewHolder();
                holder.tvType = (TextView)v.findViewById(R.id.tv_type);
                holder.tvName = (TextView)v.findViewById(R.id.tv_name);
                holder.tvSub = (TextView)v.findViewById(R.id.tv_submit);
                return holder;

            }
        };
        lv.setAdapter(adapter);
        for (int i=0;i<10;i++){
            TestMode item = new TestMode();
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
        TextView tvType;
        TextView tvSub;
    }
}
