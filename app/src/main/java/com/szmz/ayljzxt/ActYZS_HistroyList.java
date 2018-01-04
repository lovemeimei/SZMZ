package com.szmz.ayljzxt;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.materiallistview.MaterialRefreshLayout;
import com.szmz.ActBase;
import com.szmz.ActListBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.request.YZS_History_List_Req;
import com.szmz.entity.response.JZ_Search_worker_Res;
import com.szmz.entity.response.YZS_history_Res;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.search.ActHistoryList;
import com.szmz.search.ActSearchDetail;
import com.szmz.utils.BaseListAdapter;
import com.szmz.utils.DatePickerUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/24 0024上午 9:38
 */

public class ActYZS_HistroyList extends ActListBase {

    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.et_tj_time)
    TextView tvStartTime;
    @BindView(R.id.et_tj_time2)
    TextView tvEndTime;
    
    BaseListAdapter<YZS_history_Res.ResultBean, ActYZS_HistroyList.MViewHolder> adapter;

    private List<YZS_history_Res.ResultBean> items = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.comm_list_yzs_history;
    }

    @Override
    public void doRefresh(MaterialRefreshLayout materialRefreshLayout) {
        currentPage = 1;
        getList();
    }

    @Override
    public void doRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
        currentPage++;
        getList();
    }

    @Override
    protected void initUI() {
        super.initUI();

        setLeftVisible(true);
        setTitle("历史救助信息");

        initTimePicker();
        adapter = new BaseListAdapter<YZS_history_Res.ResultBean, MViewHolder>(context, R.layout.list_item_history) {
            @Override
            protected void refreshView(int postion, final YZS_history_Res.ResultBean item, MViewHolder holer) {

                holer.tvName.setText(item.getNAME() + "\t\t\t" + item.getCARDID());

                holer.tvName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        trans(ActYZS_Histroy_Detail.class);
                        Intent intent = new Intent(context, ActYZS_Histroy_Detail.class);
                        intent.putExtra("id", item.getID());
                        startActivity(intent);
                    }
                });
            }

            @Override
            protected MViewHolder getHolder(View converView) {

                ActYZS_HistroyList.MViewHolder holder = new ActYZS_HistroyList.MViewHolder();
                holder.tvName = (TextView) converView.findViewById(R.id.tv_name);

                return holder;
            }
        };
        lv.setAdapter(adapter);

        doLoadData();
    }
    private TimePickerView pvTime;
    private void initTimePicker() {
        pvTime = DatePickerUtil.initPicker(this, DatePickerUtil.yyyyMMdd);
    }

    @OnClick({R.id.et_tj_time2, R.id.et_tj_time})
    public void doClick(View v) {

        switch (v.getId()) {
            case R.id.et_tj_time:
                pvTime.show(tvStartTime);
                break;
            case R.id.et_tj_time2:
                pvTime.show(tvEndTime);
                break;

        }
    }
    class MViewHolder {
        TextView tvName;
    }

    private void getList() {

        YZS_History_List_Req req = new YZS_History_List_Req(getUser().getAccountYZS(), currentPage);

        Call<YZS_history_Res> call = App.getApiProxyYZS().getYZS_History_list(req);

        ApiUtil<YZS_history_Res> apiUtil = new ApiUtil<>(context, call, new SimpleApiListener<YZS_history_Res>() {

            @Override
            public void doAfter() {
                super.doAfter();
                refresh.finishRefreshLoadMore();
                refresh.finishRefresh();
            }

            @Override
            public void doSuccess(YZS_history_Res result) {
                items = result.Result;
                if (items != null && items.size() > 0) {
                    if (currentPage == 1)
                        adapter.clearListData();
                    adapter.setItems(items);
                    adapter.notifyDataSetChanged();


                } else {
                    noDataLayout.setVisibility(View.VISIBLE);
                    adapter.clearListData();
                    adapter.notifyDataSetChanged();
                }

                if (isHasNextPage(currentPage, pageSize, result.TotalNum)) {
                    refresh.setLoadMore(true);
                } else {
                    refresh.setLoadMore(false);
                }
            }
        }, false);

        apiUtil.excute();
    }
}
