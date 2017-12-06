package com.szmz.ayljzxt;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.materiallistview.MaterialRefreshLayout;
import com.szmz.ActListBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.SearchJZYE;
import com.szmz.entity.request.YZSSQR_JZYE_Req;
import com.szmz.entity.response.YZSSQR_JZYE_Res;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.BaseListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;

/**
 * 救助余额查询
 */

public class ActYZSSQR_SYJZEList extends ActListBase {


    @BindView(R.id.lv)
    ListView lv;
    BaseListAdapter<YZSSQR_JZYE_Res.ResultBean,MViewHolder> adapter;
    List<YZSSQR_JZYE_Res.ResultBean> items = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_list_syjze;
    }


    @Override
    protected void initUI() {
        super.initUI();

        setTitle("救助余额");
        setLeftVisible(true);

        adapter = new BaseListAdapter<YZSSQR_JZYE_Res.ResultBean, MViewHolder>(this,R.layout.list_item_search_jzye) {
            @Override
            protected void refreshView(int postion, final YZSSQR_JZYE_Res.ResultBean item, MViewHolder holer) {

                holer.tvName.setText(item.getCATEGORY_NAME());
                holer.tvMoney.setText(item.getTHIS_YEAR_BALANCE());

                holer.tvSub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context,ActYZSSQR_SYJZE_Detail.class);
                        intent.putExtra("item",item);
                        startActivity(intent);
                    }
                });
            }

            @Override
            protected MViewHolder getHolder(View v) {
                MViewHolder holder = new MViewHolder();
                holder.tvMoney = (TextView)v.findViewById(R.id.tv_money);
                holder.tvName = (TextView)v.findViewById(R.id.tv_name);
                holder.tvSub = (TextView)v.findViewById(R.id.tv_submit);
//                holder.tvSub.setVisibility(View.GONE);
                return holder;
            }
        };

        lv.setAdapter(adapter);

    }

    @Override
    public void doRefresh(MaterialRefreshLayout materialRefreshLayout) {

        currentPage=1;
        getList();
    }

    @Override
    public void doRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {

        currentPage++;
        getList();
    }

    class MViewHolder{
        TextView tvName;
        TextView tvMoney;
        TextView tvSub;
    }

    private void getList(){

        YZSSQR_JZYE_Req req = new YZSSQR_JZYE_Req(getUser().getIdCode(),currentPage);

        Call<YZSSQR_JZYE_Res> call = App.getApiProxyYZS().getYZS_jzye(req);

        ApiUtil<YZSSQR_JZYE_Res> apiUtil = new ApiUtil<>(context,call,new SimpleApiListener<YZSSQR_JZYE_Res>(){

            @Override
            public void doSuccess(YZSSQR_JZYE_Res result) {
                super.doSuccess(result);
                if (result!=null){
                    items = result.Result;
                    if (items!=null && items.size()>0){
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
                }else {

                }
            }

            @Override
            public void doAfter() {
                super.doAfter();
                refresh.finishRefresh();
                refresh.finishRefreshLoadMore();
            }
        },false);

        apiUtil.excute();
    }
}
