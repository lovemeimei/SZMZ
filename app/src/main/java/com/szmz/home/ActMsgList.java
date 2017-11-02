package com.szmz.home;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.materiallistview.MaterialRefreshLayout;
import com.szmz.ActListBase;
import com.szmz.ActMsgDetail;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.request.JZ_TODO_FuntionTree;
import com.szmz.entity.request.JZ_TODO_List;
import com.szmz.entity.response.JZ_Todo_MenuTree;
import com.szmz.entity.response.JZ_Todolist;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.BaseListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;

public class ActMsgList extends ActListBase {

    private String title;
    @BindView(R.id.lv)
    ListView lv;
    BaseListAdapter<String,ActMsgList.MViewHolder> adapter;

    int type = 0;
    private String funtionID="";

    @Override
    protected int getLayoutId() {
        return R.layout.comm_list_slt;
    }


    @Override
    protected void initUI() {
        super.initUI();
        title= getIntent().getStringExtra("title");

        setTitle(title);
        setLeftVisible(true);

        adapter = new BaseListAdapter<String, ActMsgList.MViewHolder>(this,R.layout.comm_list_item) {
            @Override
            protected void refreshView(int postion, String item, ActMsgList.MViewHolder holer) {


                holer.tvName.setText(item);
                holer.tvName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        trans(ActMsgDetail.class,title,"");
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
        List<String> items = new ArrayList<>();
        for (int i=0;i<10;i++){
            items.add("测试消息"+i);
        }
        adapter.setItems(items);


//        initMenuTrer();
//
//        getTodoList();
    }

    @Override
    public void doRefresh(MaterialRefreshLayout materialRefreshLayout) {
            refresh.finishRefresh();
    }

    @Override
    public void doRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
refresh.finishRefreshLoadMore();
    }

    class MViewHolder{

        TextView tvName;
    }

    private void getTodoList(){
        JZ_TODO_List req = new JZ_TODO_List(getUser().getAccountJZ(),getUser().getIdJZ(),funtionID,1);

        Call<JZ_Todolist> call = App.getApiProxyJZ().getJZ_TodoList(req);

        ApiUtil<JZ_Todolist> apiUtil = new ApiUtil<>(context,call,new SimpleApiListener<JZ_Todolist.ResultBean>(){
            @Override
            public void doSuccess(JZ_Todolist.ResultBean result) {
                super.doSuccess(result);
            }
        },false);
    }


    private void  initMenuTrer(){

        JZ_TODO_FuntionTree req = new JZ_TODO_FuntionTree(App.getInstance().getLoginUser().getUserName(),App.getInstance().getLoginUser().getIdJZ());

        Call<JZ_Todo_MenuTree> call = App.getApiProxyJZ().getJZ_FuntionTree(req);

        ApiUtil<JZ_Todo_MenuTree> apiUtil = new ApiUtil<>(context,call,new SimpleApiListener(){
            @Override
            public void doSuccess(Object result) {
                super.doSuccess(result);
            }
        },true);

        apiUtil.excute();


    }
}
