package com.szmz.ayljzxt;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.materiallistview.MaterialRefreshLayout;
import com.materiallistview.MaterialRefreshListener;
import com.szmz.App;
import com.szmz.BaseFragment;
import com.szmz.R;
import com.szmz.entity.request.YZSSQR_HomeList_Req;
import com.szmz.entity.request.YZSSQR_history_req;
import com.szmz.entity.request.YZS_SQR_jdlist_req;
import com.szmz.entity.request.YZS_todoList_Req;
import com.szmz.entity.response.YZSSQR_HomeList_Res;
import com.szmz.entity.response.YZSSQR_HomeList_Res;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.BaseListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/5 0005上午 11:34
 */

public class FragmentHomeYL_C2 extends BaseFragment {

    @BindView(R.id.refresh)
    public MaterialRefreshLayout refresh;
    protected int currentPage = 1;
    @BindView(R.id.lv)
    ListView lv;

    private List<YZSSQR_HomeList_Res.ResultBean> items = new ArrayList<>();

    protected LinearLayout noDataLayout;
    protected TextView textView;

    private BaseListAdapter<YZSSQR_HomeList_Res.ResultBean,MViewHolder> adapter;

    class MViewHolder {

        TextView tvName;
    }
    public  void doRefresh(MaterialRefreshLayout materialRefreshLayout){
        currentPage=1;
        getList();
    };

    public  void doRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout){

        currentPage++;
        getList();
    };

    private void getList(){

        YZSSQR_HomeList_Req req = new YZSSQR_HomeList_Req(App.getInstance().getLoginUser().getIdCode(),currentPage);

        Call<YZSSQR_HomeList_Res> call = App.getApiProxyYZS().getYZS_homelist_SQR(req);

        ApiUtil<YZSSQR_HomeList_Res> apiUtil = new ApiUtil<>(getContext(),call,new SimpleApiListener<YZSSQR_HomeList_Res>(){

            @Override
            public void doSuccess(YZSSQR_HomeList_Res result) {

                items = result.Result;
                if (items!=null && items.size()>0){
                    if (currentPage==1){
                        adapter.clearListData();
                    }
                    adapter.setItems(items);
                    adapter.notifyDataSetChanged();
                }else {
                    adapter.clearListData();
                    noDataLayout.setVisibility(View.VISIBLE);
                    adapter.notifyDataSetChanged();
                }

                if (isHasNextPage(currentPage,20,result.TotalNum)){
                    refresh.setLoadMore(true);
                }else {
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

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_comm_list;
    }

    @Override
    protected void bindDates(View v) {
        super.bindDates(v);
        noDataLayout = (LinearLayout)v.findViewById(R.id.noDataLayout);
        textView = (TextView)v.findViewById(R.id.textView);

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


        adapter = new BaseListAdapter<YZSSQR_HomeList_Res.ResultBean, MViewHolder>(getContext(),R.layout.comm_list_item) {
            @Override
            protected void refreshView(int postion, final YZSSQR_HomeList_Res.ResultBean item, MViewHolder holer) {

                holer.tvName.setText(item.getNOTE_TITLE());
                holer.tvName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(),ActHomeDetail.class);
                        intent.putExtra("item",item);
                        startActivity(intent);
                    }
                });

            }

            @Override
            protected MViewHolder getHolder(View converView) {

                MViewHolder holder = new MViewHolder();
                holder.tvName =(TextView) converView.findViewById(R.id.tv_name);

                return holder;
            }
        };
        lv.setAdapter(adapter);
        doLoadData();
    }

    protected void doLoadData() {
        refresh.autoRefresh();
    }

    public static boolean isHasNextPage(int currentPage, int pageNumber, int totalItems) {
        int lastIndex = currentPage * pageNumber;
        if (lastIndex >= totalItems)
            return false;
        return true;
    }

}
