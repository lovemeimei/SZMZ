package com.szmz.search;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.materiallistview.MaterialRefreshLayout;
import com.szmz.ActListBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.request.JZSQR_zjfflist_req;
import com.szmz.entity.request.JZ_SQR_historyList_req;
import com.szmz.entity.response.JZSQR_zjff_res;
import com.szmz.entity.response.JZSQR_zjff_res;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.BaseListAdapter;
import com.szmz.utils.UIUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/11 0011下午 3:08
 */

public class ActSQR_zjff_List extends ActListBase {

    @BindView(R.id.lv)
    ListView lv;


    private String typeID="";

    BaseListAdapter<JZSQR_zjff_res.ResultBean, ActSQR_zjff_List.MViewHolder> adapter;

    private List<JZSQR_zjff_res.ResultBean> items = new ArrayList<>();


    @Override
    protected void initUI() {
        super.initUI();
        UIUtil.hideInputMethod(this);

        setLeftVisible(true);
        setTitle("历史记录");

        adapter = new BaseListAdapter<JZSQR_zjff_res.ResultBean, ActSQR_zjff_List.MViewHolder>(this, R.layout.list_item_history) {
            @Override
            protected void refreshView(int postion, final JZSQR_zjff_res.ResultBean item, ActSQR_zjff_List.MViewHolder holer) {


                holer.tvName.setText(item.getBpmBizTitle());
//                holer.tvName.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        Intent intent = new Intent(context,ActSearchDetail.class);
//                        intent.putExtra("item",item);
//                        startActivity(intent);
//
//                    }
//                });
            }

            @Override
            protected ActSQR_zjff_List.MViewHolder getHolder(View converView) {

                ActSQR_zjff_List.MViewHolder holder = new ActSQR_zjff_List.MViewHolder();
                holder.tvName = (TextView) converView.findViewById(R.id.tv_name);

                return holder;
            }
        };
        lv.setAdapter(adapter);
        adapter.setItems(items);

       
    }

    @Override
    protected int getLayoutId() {
        return R.layout.comm_list;
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

    class MViewHolder {
        TextView tvName;
    }

    private void getList(){

        final JZSQR_zjfflist_req req = new JZSQR_zjfflist_req(getUser().getIdCode(),currentPage);

        Call<JZSQR_zjff_res> call = App.getApiProxyJZ().JZSQR_zjff(req);

        ApiUtil<JZSQR_zjff_res> apiUtil = new ApiUtil<>(context,call,new SimpleApiListener<JZSQR_zjff_res>(){
            @Override
            public void doSuccess(JZSQR_zjff_res result) {

                items = result.Result;
                if (items!=null && items.size()>0){
                    noDataLayout.setVerticalGravity(View.GONE);
                    if (currentPage==1){
                        adapter.clearListData();
                    }
                    adapter.setItems(items);
                    adapter.notifyDataSetChanged();
                }else {
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
