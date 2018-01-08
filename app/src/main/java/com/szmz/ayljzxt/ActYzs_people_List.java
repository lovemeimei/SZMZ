package com.szmz.ayljzxt;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ListView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.materiallistview.MaterialRefreshLayout;
import com.szmz.ActListBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.YZS_xzqh;
import com.szmz.entity.YZS_xzqh;
import com.szmz.entity.request.YZS_History_List_Req;
import com.szmz.entity.request.YZS_people_list_Req;
import com.szmz.entity.request.YZS_qh_req;
import com.szmz.entity.response.JZ_Search_worker_Res;
import com.szmz.entity.response.YZS_history_Res;
import com.szmz.entity.response.YZS_people_Res;
import com.szmz.entity.response.YZS_qh_res;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.search.ActHistoryList;
import com.szmz.search.ActSearchDetail;
import com.szmz.utils.BaseListAdapter;
import com.szmz.ywbl.ActBaseList;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/24 0024上午 10:44
 */

public class ActYzs_people_List extends ActListBase{

    @BindView(R.id.lv)
    ListView lv;

    @BindView(R.id.tv_search_title)
    TextView tvSearchView;

    private List<YZS_people_Res.ResultBean> items = new ArrayList<>();

    private BaseListAdapter<YZS_people_Res.ResultBean,MViewHolder> adapter;

    private String areCode = "";
    private String searchStr = "";


    class MViewHolder {
        TextView tvName;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.comm_list_slt;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setTitle("救助对象信息");
        setLeftVisible(true);
        adapter = new BaseListAdapter<YZS_people_Res.ResultBean, MViewHolder>(this, R.layout.list_item_history) {
            @Override
            protected void refreshView(int postion, final YZS_people_Res.ResultBean item, MViewHolder holer) {


                holer.tvName.setText(item.getNAME()+"\t\t\t"+item.getIDCARD());
                holer.tvName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(context,ActYZS_people_detail.class);
                        intent.putExtra("id",item.getID());
                        startActivity(intent);

                    }
                });
            }

            @Override
            protected MViewHolder getHolder(View converView) {

                MViewHolder holder = new MViewHolder();
                holder.tvName = (TextView) converView.findViewById(R.id.tv_name);

                return holder;
            }
        };
        lv.setAdapter(adapter);
        adapter.setItems(items);

                searchEd.setOnEditorActionListener(new TextView.OnEditorActionListener() {

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

        getQHlist();
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

    private void getList(){
        searchStr = searchEd.getText().toString().trim();
        if (searchStr==null){
            searchStr="";
        }
        if (xzqh!=null){
            areCode = xzqh.getCode();
        }else {
            areCode="";
        }
        YZS_people_list_Req req = new YZS_people_list_Req(getUser().getAccountYZS(),areCode,searchStr,currentPage);

        Call<YZS_people_Res> call = App.getApiProxyYZS().getYZS_people_list(req);

        ApiUtil<YZS_people_Res> apiUtil = new ApiUtil<>(context,call,new SimpleApiListener<YZS_people_Res>(){

            @Override
            public void doSuccess(YZS_people_Res result) {
                super.doSuccess(result);
                items = result.Result;

                if (items!=null && items.size()>0){
                    if (currentPage==1)
                        adapter.clearListData();
                    adapter.setItems(items);
                    adapter.notifyDataSetChanged();
                }else {
                    adapter.clearListData();
                    adapter.notifyDataSetChanged();
                    noDataLayout.setVisibility(View.VISIBLE);
                }

                if (isHasNextPage(currentPage,pageSize,result.TotalNum)){
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

    private YZS_xzqh xzqh;
    private MaterialDialog dialog;
    private AndroidTreeView tView;
    private MaterialDialog.Builder builder;

    private void getQHlist(){

        YZS_qh_req req = new YZS_qh_req(getUser().getAccountYZS());


        Call<YZS_qh_res> call = App.getApiProxyYZS().getYZS_xzqh(req);

        ApiUtil<YZS_qh_res> apiUtil = new ApiUtil<>(context,call,new SimpleApiListener<YZS_qh_res>(){
            @Override
            public void doSuccess(YZS_qh_res result) {
                super.doSuccess(result);

                 List<YZS_xzqh> xzqhs = result.Result;
                 if (xzqhs!=null && xzqhs.size()>0)
                     initData(xzqhs);
            }
        },false);

        apiUtil.excute();

    }

    private void initData(List<YZS_xzqh> list) {
        TreeNode root = TreeNode.root();
        List<YZS_xzqh> myList = new ArrayList<YZS_xzqh>();
        for (int i = 0; i < list.size(); i++) {
            boolean isHaveParent = false;
            for (YZS_xzqh item : list) {
                if (item.getID().equals(list.get(i).getPARENT_ID())) {
                    isHaveParent = true;
                    break;
                }
            }
            if (!isHaveParent) {
                myList.add(list.get(i));
            }
        }
        for (YZS_xzqh item : myList) {
            TreeNode node = new TreeNode(new YZSTreeItemHolder.TreeItem(item)).setViewHolder(new YZSTreeItemHolder(this, new YZSTreeItemHolder.OnClickChildListener() {
                @Override
                public void doClick(YZS_xzqh item) {
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                    xzqh = item;
                    tvSearchView.setText(item.getName());
                    doLoadData();
                }
            }));
            node = addChildNode(node, list);
            root.addChild(node);
        }
        tView = new AndroidTreeView(this, root);
        tView.setDefaultAnimation(false);
        tvSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doShowDialog();
            }
        });
        builder = new MaterialDialog.Builder(context)
                .customView(tView.getView(), true).negativeText("取消").onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                });
    }

    private void doShowDialog() {
        dialog = builder.show();
        dialog.setCanceledOnTouchOutside(false);
    }


    private TreeNode addChildNode(TreeNode node, List<YZS_xzqh> list) {
        for (YZS_xzqh item : list) {
            YZSTreeItemHolder.TreeItem value = (YZSTreeItemHolder.TreeItem) node.getValue();
            if (value.item.getID().equals(item.getPARENT_ID())) {
                node.addChild(addChildNode(new TreeNode(new YZSTreeItemHolder.TreeItem(item)).setViewHolder(new YZSTreeItemHolder(this, new YZSTreeItemHolder.OnClickChildListener() {
                    @Override
                    public void doClick(YZS_xzqh item) {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                        xzqh = item;
                        tvSearchView.setText(item.getName());
                        doLoadData();
                    }
                })), list));
            }
        }

        return node;

    }

}
