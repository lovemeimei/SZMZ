package com.szmz.home;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.materiallistview.MaterialRefreshLayout;
import com.szmz.ActListBase;
import com.szmz.ActMsgDetail;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.CommMsgSave;
import com.szmz.entity.request.JZ_Comm_list_Req;
import com.szmz.entity.request.JZ_TODO_FuntionTree;
import com.szmz.entity.response.JZ_MSG_FC_Res;
import com.szmz.entity.response.JZ_MSG_SP_Res;
import com.szmz.entity.response.JZ_Todo_MenuTree;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.BaseListAdapter;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;

public class ActMsgListNormal extends ActListBase {

    private String title;
    @BindView(R.id.lv)
    ListView lv;
    BaseListAdapter<CommMsgSave, ActMsgListNormal.MViewHolder> adapterSP;
    BaseListAdapter<CommMsgSave, ActMsgListNormal.MViewHolder> adapterFC;

    List<CommMsgSave> items = new ArrayList<>();
    int type = 0;


    @Override
    protected int getLayoutId() {
        return R.layout.comm_list;
    }


    @Override
    protected void initUI() {
        super.initUI();
        title = getIntent().getStringExtra("title");

        if (title.equals("信访提醒")) {
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
        adapterSP = new BaseListAdapter<CommMsgSave, MViewHolder>(this, R.layout.comm_list_item) {
            @Override
            protected void refreshView(int postion, CommMsgSave item, ActMsgListNormal.MViewHolder holer) {


                holer.tvName.setText(item.getTitle());
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
        adapterFC = new BaseListAdapter<CommMsgSave, MViewHolder>(this, R.layout.comm_list_item) {
            @Override
            protected void refreshView(int postion, CommMsgSave item, ActMsgListNormal.MViewHolder holer) {


                holer.tvName.setText(item.getTitle());
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
        refresh.finishRefresh();
    }

    @Override
    public void doRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
        currentPage++;
        getInfo();
        refresh.finishRefreshLoadMore();
    }

    class MViewHolder {

        TextView tvName;
    }


    private void getInfo() {
        DbManager dbManager = x.getDb(App.getDaoConfig());
        if (type == 1) {
            try {
//                List<User> users = db.selector(User.class)
//                        .where("name","like","%kevin%")
//                        .and("email", "=", "caolbmail@gmail.com")
//                        .orderBy("regTime",true)
//                        .limit(2) //只查询两条记录
//                        .offset(2) //偏移两个,从第三个记录开始返回,limit配合offset达到sqlite的limit m,n的查询
//                        .findAll();
                items =dbManager.findAll(CommMsgSave.class);

            } catch (DbException e) {
                e.printStackTrace();
            }
        } else {

        }
    }




}
