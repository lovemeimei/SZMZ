package com.szmz.home;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ListView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.materiallistview.MaterialRefreshLayout;
import com.szmz.ActListBase;
import com.szmz.ActMsgDetail;
import com.szmz.App;
import com.szmz.R;
import com.szmz.ahdxt.tjfx.TreeItemHolder;
import com.szmz.entity.HD_XZQH;
import com.szmz.entity.request.JZ_TODO_FuntionTree;
import com.szmz.entity.request.JZ_TODO_List;
import com.szmz.entity.response.JZ_Todo_MenuTree;
import com.szmz.entity.response.JZ_Todolist;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.BaseListAdapter;
import com.szmz.utils.UIUtil;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;

public class ActMsgList extends ActListBase {

    private String title;
    private String keyWords="";
    @BindView(R.id.lv)
    ListView lv;
    BaseListAdapter<JZ_Todolist.ResultBean, ActMsgList.MViewHolder> adapter;

    int type = 0;
//    private String funtionID = "245A8F94567E4368ADFD680824851F27";
    private String funtionID = "0";//默认传0

    @BindView(R.id.tv_search_title)
    TextView tvSearchView;

    @Override
    protected int getLayoutId() {
        return R.layout.comm_list_slt;
    }


    @Override
    protected void initUI() {
        super.initUI();
        title = getIntent().getStringExtra("title");

        setTitle(title);
        setLeftVisible(true);

        adapter = new BaseListAdapter<JZ_Todolist.ResultBean, ActMsgList.MViewHolder>(this, R.layout.comm_list_item) {
            @Override
            protected void refreshView(int postion, final JZ_Todolist.ResultBean item, ActMsgList.MViewHolder holer) {


                holer.tvName.setText(item.getTitle());
                holer.tvName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context,ActMsgDetail_JZ.class);
                        intent.putExtra("item",item);
                        startActivity(intent);
                    }
                });
            }

            @Override
            protected ActMsgList.MViewHolder getHolder(View converView) {

                ActMsgList.MViewHolder holder = new ActMsgList.MViewHolder();
                holder.tvName = (TextView) converView.findViewById(R.id.tv_name);

                return holder;
            }
        };
        lv.setAdapter(adapter);


        initMenuTrer();
        searchEd.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {

                    String str = searchEd.getText().toString();
                    if (TextUtils.isEmpty(str)){
                        UIUtil.doToast("请输入查询内容");
                    }else {

                        doLoadData();
                    }

                    return true;
                }
                return true;
            }
        });
    }

    @Override
    public void doRefresh(MaterialRefreshLayout materialRefreshLayout) {

        currentPage=1;
        getTodoList();
    }

    @Override
    public void doRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {

        currentPage++;
        getTodoList();
    }

    class MViewHolder {

        TextView tvName;
    }

    private void getTodoList() {
        keyWords = searchEd.getText().toString();
        if (TextUtils.isEmpty(keyWords))
            keyWords ="";
        if (TextUtils.isEmpty(funtionID))
            funtionID ="";
        JZ_TODO_List req = new JZ_TODO_List(getUser().getAccountJZ(), funtionID,keyWords, currentPage);

        Call<JZ_Todolist> call = App.getApiProxyJZ().getJZ_TodoList(req);

        ApiUtil<JZ_Todolist> apiUtil = new ApiUtil<>(context, call, new SimpleApiListener<JZ_Todolist>() {

            @Override
            public void doAfter() {
                super.doAfter();
                refresh.finishRefresh();
                refresh.finishRefreshLoadMore();
            }

            @Override
            public void doSuccess(JZ_Todolist result) {
                super.doSuccess(result);
                List<JZ_Todolist.ResultBean> items = result.Result;
                if (items != null && items.size() > 0) {
                    noDataLayout.setVerticalGravity(View.GONE);
                    if (currentPage == 1) {
                        adapter.clearListData();
                    }
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


    private void initMenuTrer() {

        JZ_TODO_FuntionTree req = new JZ_TODO_FuntionTree(App.getInstance().getLoginUser().getUserName(), App.getInstance().getLoginUser().getIdJZ());

        Call<JZ_Todo_MenuTree> call = App.getApiProxyJZ().getJZ_FuntionTree(req);

        ApiUtil<JZ_Todo_MenuTree> apiUtil = new ApiUtil<>(context, call, new SimpleApiListener<JZ_Todo_MenuTree>() {
            @Override
            public void doSuccess(JZ_Todo_MenuTree result) {
                super.doSuccess(result);
                List<JZ_Todo_MenuTree.ResultBean> temps = result.Result;
                if (temps != null && temps.size() > 0) {
                    for (int i = 0; i < temps.size(); i++) {
                        JZ_Todo_MenuTree.ResultBean treeItem = temps.get(i);
                        HD_XZQH item = new HD_XZQH();
                        item.setAreaId(treeItem.getId());
                        item.setAreaName(treeItem.getFunctionname());
                        item.setParentAreaId(treeItem.getParentfunctionid());
                        items.add(item);
                    }
                    initData(items);

                }
            }
        }, true);

        apiUtil.excute();


    }

    //**************************筛选条件部分*******************************************//

    private List<HD_XZQH> items = new ArrayList<>();
    private MaterialDialog dialog;
    private AndroidTreeView tView;
    private MaterialDialog.Builder builder;
    private HD_XZQH hd_xzqh;

    private void initData(List<HD_XZQH> list) {
        TreeNode root = TreeNode.root();
        //根目录
        List<HD_XZQH> myList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (TextUtils.isEmpty(list.get(i).getParentAreaId())) {
                myList.add(list.get(i));
            }
        }
        for (HD_XZQH item : myList) {
            TreeNode node = new TreeNode(new TreeItemHolder.TreeItem(item)).setViewHolder(new TreeItemHolder(this, new TreeItemHolder.OnClickChildListener() {
                @Override
                public void doClick(HD_XZQH item) {
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                    hd_xzqh = item;
                    tvSearchView.setText(item.getAreaName());
                    funtionID = hd_xzqh.getAreaId();
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

    private TreeNode addChildNode(TreeNode node, List<HD_XZQH> list) {
        for (HD_XZQH item : list) {
            TreeItemHolder.TreeItem value = (TreeItemHolder.TreeItem) node.getValue();
            if (value.item.getAreaId().equals(item.getParentAreaId())) {
                node.addChild(addChildNode(new TreeNode(new TreeItemHolder.TreeItem(item)).setViewHolder(new TreeItemHolder(this, new TreeItemHolder.OnClickChildListener() {
                    @Override
                    public void doClick(HD_XZQH item) {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                        hd_xzqh = item;
                        tvSearchView.setText(item.getAreaName());

                        //
                        funtionID = hd_xzqh.getAreaId();
                      doLoadData();
                    }
                })), list));
            }
        }

        return node;

    }
}
