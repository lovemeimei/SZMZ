package com.szmz.ayljzxt;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.materiallistview.MaterialRefreshLayout;
import com.materiallistview.MaterialRefreshListener;
import com.szmz.App;
import com.szmz.BaseFragment;
import com.szmz.R;
import com.szmz.entity.request.YZS_SQR_jdlist_req;
import com.szmz.entity.response.YZSSQR_jd_Res;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.user.check.ActCheckDetail1;
import com.szmz.utils.BaseListAdapter;
import com.szmz.widget.SearchEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/5 0005上午 11:35
 *  *救助系统困难群众 进度查询
 */

public class FragmentSearchYL_C extends BaseFragment {

    @BindView(R.id.refresh)
    public MaterialRefreshLayout refresh;
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.search_ed)
    SearchEditText searchEditText;
    protected int currentPage = 1;
    protected LinearLayout noDataLayout;
    protected TextView textView;
    private String ApplicationNo ="";

    private BaseListAdapter<YZSSQR_jd_Res.ResultBean,FragmentSearchYL_C.MViewHolder> adapter;

    public  void doRefresh(MaterialRefreshLayout materialRefreshLayout){
        currentPage=1;
        getList();
    };

    public  void doRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout){
        currentPage++;
        getList();
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_comm_list_slt;
    }

    @Override
    protected void bindDates(View v) {
        super.bindDates(v);
        noDataLayout = (LinearLayout)v.findViewById(R.id.noDataLayout);
        textView = (TextView)v.findViewById(R.id.textView);

        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {

                   doLoadData();

                    return true;
                }
                return false;
            }
        });
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

        adapter = new BaseListAdapter<YZSSQR_jd_Res.ResultBean, MViewHolder>(getContext(),R.layout.list_item_user_check) {

            @Override
            protected void refreshView(int postion, final YZSSQR_jd_Res.ResultBean item, final MViewHolder holer) {
                holer.tvName.setText(item.getTypeName()+"\t\t\t\t"+item.getFLOW_RESULT_NAME()+"\t\t\n"+item.getADD_TIME());

                holer.tvBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getContext(),ActYZS_JDDetail.class);
                        intent.putExtra("id",item.getId());
                        intent.putExtra("type",item.getType());
                        startActivity(intent);
                    }
                });
            }

            @Override
            protected FragmentSearchYL_C.MViewHolder getHolder(View converView) {
                FragmentSearchYL_C.MViewHolder holder = new FragmentSearchYL_C.MViewHolder();
                holder.tvBtn = (TextView) converView.findViewById(R.id.tv_btn);
                holder.tvName = (TextView)converView.findViewById(R.id.tv_content);
                return holder;
            }
        };
        lv.setAdapter(adapter);

        refresh.autoRefresh();
    }
    protected void doLoadData() {
        refresh.autoRefresh();
    }

    class MViewHolder{
        TextView tvName;
        TextView tvBtn;
    }

    private void getList(){

        ApplicationNo = searchEditText.getText().toString().trim();
        if (ApplicationNo==null)
            ApplicationNo ="";

        YZS_SQR_jdlist_req req = new YZS_SQR_jdlist_req(App.getInstance().getLoginUser().getIdCode(),ApplicationNo,currentPage);

        Call<YZSSQR_jd_Res> call = App.getApiProxyYZS().getYZS_jdlist_SQR(req);


        ApiUtil<YZSSQR_jd_Res> apiUtil = new ApiUtil<>(getContext(),call,new SimpleApiListener<YZSSQR_jd_Res>(){

            @Override
            public void doSuccess(YZSSQR_jd_Res result) {
                super.doSuccess(result);
                if (result!=null){

                    List<YZSSQR_jd_Res.ResultBean> items = result.Result;
                    if (items!=null && items.size()>0){

                        if (currentPage==1)
                            adapter.clearListData();
                        adapter.setItems(items);
                        adapter.notifyDataSetChanged();
                        noDataLayout.setVisibility(View.GONE);
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


    public static boolean isHasNextPage(int currentPage, int pageNumber, int totalItems) {
        int lastIndex = currentPage * pageNumber;
        if (lastIndex >= totalItems)
            return false;
        return true;
    }
}
