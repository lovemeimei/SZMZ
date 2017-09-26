package com.szmz.fragment;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.materiallistview.MaterialRefreshLayout;
import com.materiallistview.MaterialRefreshListener;
import com.szmz.BaseFragment;
import com.szmz.R;
import com.szmz.search.ActHistoryList;
import com.szmz.user.check.ActCheckDetail1;
import com.szmz.utils.BaseListAdapter;
import com.szmz.ywbl.dzda.ActYwbl_dzda_person;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/5 0005上午 11:35
 *  *救助系统困难群众 进度查询
 */

public class FragmentSearch_C extends BaseFragment {
    @BindView(R.id.refresh)
    public MaterialRefreshLayout refresh;
    @BindView(R.id.lv)
    ListView lv;

    protected int currentPage = 1;
    protected int totleNum = 1;
    private BaseListAdapter<String ,FragmentSearch_C.MViewHolder> adapter;

    public  void doRefresh(MaterialRefreshLayout materialRefreshLayout){

    };

    public  void doRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout){

    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search_c_jd;
    }

    @Override
    protected void bindDatas() {

        refresh.setLoadMore(false);
        refresh.finishRefreshLoadMore();
        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                doRefresh(materialRefreshLayout);
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {

                doRefreshLoadMore(materialRefreshLayout);
            }
        });

        adapter = new BaseListAdapter<String, FragmentSearch_C.MViewHolder>(getContext(),R.layout.list_item_user_check) {
            @Override
            protected void refreshView(int postion, String item, FragmentSearch_C.MViewHolder holer) {

                holer.tvBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        trans(ActCheckDetail1.class);
                    }
                });
            }

            @Override
            protected FragmentSearch_C.MViewHolder getHolder(View converView) {
                FragmentSearch_C.MViewHolder holder = new FragmentSearch_C.MViewHolder();
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
    protected void doLoadData() {
        refresh.autoRefresh();
    }

    class MViewHolder{
        TextView tvName;
        TextView tvBtn;
    }
}
