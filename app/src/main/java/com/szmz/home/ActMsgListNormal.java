package com.szmz.home;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.materiallistview.MaterialRefreshLayout;
import com.szmz.ActListBase;
import com.szmz.ActMsgDetail;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.request.JZ_Comm_list_Req;
import com.szmz.entity.request.JZ_TODO_FuntionTree;
import com.szmz.entity.response.JZ_MSG_FC_Res;
import com.szmz.entity.response.JZ_MSG_SP_Res;
import com.szmz.entity.response.JZ_Todo_MenuTree;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.BaseListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;

public class ActMsgListNormal extends ActListBase {

    private String title;
    @BindView(R.id.lv)
    ListView lv;
    BaseListAdapter<JZ_MSG_SP_Res.ResultBean, ActMsgListNormal.MViewHolder> adapterSP;
    BaseListAdapter<JZ_MSG_FC_Res.ResultBean, ActMsgListNormal.MViewHolder> adapterFC;

    int type = 0;


    @Override
    protected int getLayoutId() {
        return R.layout.comm_list;
    }


    @Override
    protected void initUI() {
        super.initUI();
        title = getIntent().getStringExtra("title");

        if (title.equals("审批意见")) {
            type = 1;
            initSP();
        } else if (title.equals("复查事项")) {
            type = 2;
            initFC();
        }
        setTitle(title);
        setLeftVisible(true);

    }

    private void initSP() {
        adapterSP = new BaseListAdapter<JZ_MSG_SP_Res.ResultBean, MViewHolder>(this, R.layout.comm_list_item) {
            @Override
            protected void refreshView(int postion, JZ_MSG_SP_Res.ResultBean item, ActMsgListNormal.MViewHolder holer) {


                holer.tvName.setText(item.getResultreason());
                holer.tvName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        trans(ActMsgDetail.class, title, "");
                    }
                });
            }

            @Override
            protected ActMsgListNormal.MViewHolder getHolder(View converView) {

                ActMsgListNormal.MViewHolder holder = new ActMsgListNormal.MViewHolder();
                holder.tvName = (TextView) converView.findViewById(R.id.tv_name);

                return holder;
            }
        };
        lv.setAdapter(adapterSP);
    }

    private void initFC() {
        adapterFC = new BaseListAdapter<JZ_MSG_FC_Res.ResultBean, MViewHolder>(this, R.layout.comm_list_item) {
            @Override
            protected void refreshView(int postion, JZ_MSG_FC_Res.ResultBean item, ActMsgListNormal.MViewHolder holer) {


                holer.tvName.setText(item.getRemark());
                holer.tvName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        trans(ActMsgDetail.class, title, "");
                    }
                });
            }

            @Override
            protected ActMsgListNormal.MViewHolder getHolder(View converView) {

                ActMsgListNormal.MViewHolder holder = new ActMsgListNormal.MViewHolder();
                holder.tvName = (TextView) converView.findViewById(R.id.tv_name);

                return holder;
            }
        };
        lv.setAdapter(adapterFC);
    }


    @Override
    public void doRefresh(MaterialRefreshLayout materialRefreshLayout) {

        currentPage = 1;
        getInfo();
    }

    @Override
    public void doRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
        currentPage++;
        getInfo();
    }

    class MViewHolder {

        TextView tvName;
    }


    private void getInfo() {
        if (type == 1) {
            getSPList();
        } else {
            getFCList();
        }
    }


    private void getSPList() {


        JZ_Comm_list_Req req = new JZ_Comm_list_Req(App.getInstance().getLoginUser().getAccountJZ(), App.getInstance().getLoginUser().getIdJZ(), currentPage);

        Call<JZ_MSG_SP_Res> call = App.getApiProxyJZ().getJZ_MSG_SP_List(req);

        ApiUtil<JZ_MSG_SP_Res> apiUtil = new ApiUtil<>(context, call, new SimpleApiListener<JZ_MSG_SP_Res>() {

            @Override
            public void doAfter() {
                super.doAfter();
                refresh.finishRefreshing();
                refresh.finishRefreshLoadMore();
            }

            @Override
            public void doSuccess(JZ_MSG_SP_Res result) {

                List<JZ_MSG_SP_Res.ResultBean> items = new ArrayList<>();
                items = result.Result;
                if (items != null && items.size() > 0) {
                    noDataLayout.setVisibility(View.GONE);
                    if (currentPage == 1) {
                        adapterSP.clearListData();
                    }
                    adapterSP.setItems(items);
                    adapterSP.notifyDataSetChanged();

                } else {
                    adapterSP.clearListData();
                    adapterSP.notifyDataSetChanged();
                    noDataLayout.setVisibility(View.VISIBLE);
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

    private void getFCList() {

        JZ_Comm_list_Req req = new JZ_Comm_list_Req(App.getInstance().getLoginUser().getAccountJZ(), App.getInstance().getLoginUser().getIdJZ(), currentPage);

        Call<JZ_MSG_FC_Res> call = App.getApiProxyJZ().getJZ_MSG_FC_List(req);

        ApiUtil<JZ_MSG_FC_Res> apiUtil = new ApiUtil<>(context, call, new SimpleApiListener<JZ_MSG_FC_Res>() {
            @Override
            public void doSuccess(JZ_MSG_FC_Res result) {
                List<JZ_MSG_FC_Res.ResultBean> items = new ArrayList<>();
                items = result.Result;
                if (items != null && items.size() > 0) {
                    noDataLayout.setVisibility(View.GONE);
                    if (currentPage == 1) {
                        adapterSP.clearListData();
                    }
                    adapterFC.setItems(items);
                    adapterFC.notifyDataSetChanged();

                } else {
                    adapterFC.clearListData();
                    adapterFC.notifyDataSetChanged();
                    noDataLayout.setVisibility(View.VISIBLE);
                }

                if (isHasNextPage(currentPage, pageSize, result.TotalNum)) {
                    refresh.setLoadMore(true);
                } else {
                    refresh.setLoadMore(false);
                }
            }

            @Override
            public void doAfter() {
                super.doAfter();
                refresh.finishRefreshing();
                refresh.finishRefreshLoadMore();
            }
        }, false);

        apiUtil.excute();


    }

}
