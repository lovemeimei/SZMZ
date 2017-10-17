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
    BaseListAdapter<String,ActMsgListNormal.MViewHolder> adapter;

    int type = 0;


    @Override
    protected int getLayoutId() {
        return R.layout.comm_list;
    }


    @Override
    protected void initUI() {
        super.initUI();
        title= getIntent().getStringExtra("title");

        if (title.equals("待办业务")){
            type=0;
        }else if (title.equals("审批意见")){
            type=1;
        }else if (title.equals("复查事项")){
            type=2;
        }

        setTitle(title);
        setLeftVisible(true);

        adapter = new BaseListAdapter<String, ActMsgListNormal.MViewHolder>(this,R.layout.comm_list_item) {
            @Override
            protected void refreshView(int postion, String item, ActMsgListNormal.MViewHolder holer) {


//                holer.tvName.setText("");
                holer.tvName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        trans(ActMsgDetail.class,title,"");
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
        lv.setAdapter(adapter);
        List<String> items = new ArrayList<>();
        for (int i=0;i<10;i++){
            items.add(""+i);
        }
        adapter.setItems(items);

        if (type==0)
            initMenuTrer();
    }

    @Override
    public void doRefresh(MaterialRefreshLayout materialRefreshLayout) {

    }

    @Override
    public void doRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {

    }

    class MViewHolder{

        TextView tvName;
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
