package com.szmz.ayljzxt;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.materiallistview.MaterialRefreshLayout;
import com.materiallistview.MaterialRefreshListener;
import com.szmz.ActMsgDetail;
import com.szmz.App;
import com.szmz.entity.request.YZS_todoList_Req;
import com.szmz.entity.response.YZS_todoList_Res;
import com.szmz.home.ActMsgList;
import com.szmz.BaseFragment;
import com.szmz.R;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.BaseListAdapter;
import com.szmz.utils.MyBaseListAdapter;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/5 0005上午 11:34
 */

public class FragmentHomeYL extends BaseFragment {

    @BindView(R.id.refresh)
    public MaterialRefreshLayout refresh;
    protected int currentPage = 1;
    @BindView(R.id.lv)
    ListView lv;

    protected LinearLayout noDataLayout;
    protected TextView textView;

    private BaseListAdapter<YZS_todoList_Res.ResultBean,MViewHolder> adapter;

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

        YZS_todoList_Req req = new YZS_todoList_Req("admin",currentPage);

        Call<YZS_todoList_Res> call = App.getApiProxyYZS().getYZS_TodoList(req);

        ApiUtil<YZS_todoList_Res> apiUtil = new ApiUtil<>(getContext(),call,new SimpleApiListener<YZS_todoList_Res>(){

            @Override
            public void doSuccess(YZS_todoList_Res result) {
                super.doSuccess(result);

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


        adapter = new BaseListAdapter<YZS_todoList_Res.ResultBean, MViewHolder>(getContext(),R.layout.comm_list_item) {
            @Override
            protected void refreshView(int postion, YZS_todoList_Res.ResultBean item, MViewHolder holer) {

//                holer.tvName.setText(item.ge);
//                holer.tvName.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        trans(ActMsgDetail.class, "", "");
//                    }
//                });

            }

            @Override
            protected MViewHolder getHolder(View converView) {

                MViewHolder holder = new MViewHolder();
                holder.tvName =(TextView) converView.findViewById(R.id.tv_name);

                return holder;
            }
        };


//        doLoadData();
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
