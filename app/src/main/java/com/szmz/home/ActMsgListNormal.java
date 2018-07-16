package com.szmz.home;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
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
import com.szmz.utils.TextUtil;
import com.szmz.utils.UIUtil;

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
    SwipeMenuListView lv;
    BaseListAdapter<CommMsgSave, ActMsgListNormal.MViewHolder> adapter;

    List<CommMsgSave> items = new ArrayList<>();
    String type = "";
    DbManager dbManager;

    @Override
    protected int getLayoutId() {
        return R.layout.comm_menulist;
    }


    @Override
    protected void initUI() {
        super.initUI();
        title = getIntent().getStringExtra("title");
        dbManager = x.getDb(App.getDaoConfig());
        if (title.equals("信访提醒")) {

        } else if (title.equals("复查事项")) {

        }else if (title.equals("审批进度")){
            type="10204030";
        }else if (title.equals("资金发放")){
            type="10204040";
        }else if (title.equals("复查提醒")){
            type="10204050";
        }else if (title.equals("信访通知")){
            type="10204060";
        }else if (title.equals("系统通知")){
            type="10204070";
        }
        if (TextUtils.isEmpty(title)){

        }
        setTitle(title);
        setLeftVisible(true);
        initAdapter();


    }

    private void initAdapter() {

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(UIUtil.dip2px(context,90));
                // set a icon
//                deleteItem.setIcon(R.drawable.ic_delete);
                deleteItem.setTitle("删除");
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        lv.setMenuCreator(creator);

        lv.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:

                        try {
                            dbManager.delete(items.get(position));
//                            items.remove(position);
//                            adapter.notifyDataSetChanged();
                            getInfo();
                        } catch (DbException e) {
                            e.printStackTrace();
                        }
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
        adapter = new BaseListAdapter<CommMsgSave, MViewHolder>(this, R.layout.comm_list_item) {
            @Override
            protected void refreshView(int postion, final CommMsgSave item, ActMsgListNormal.MViewHolder holer) {


                holer.tvName.setText(item.getTitle());
                holer.tvName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        trans(ActMsgDetail.class, title, item.getContent());
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

    }


    @Override
    protected void onResume() {
        super.onResume();
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

            try {
//                List<User> users = db.selector(User.class)
//                        .where("name","like","%kevin%")
//                        .and("email", "=", "caolbmail@gmail.com")
//                        .orderBy("regTime",true)
//                        .limit(2) //只查询两条记录
//                        .offset(2) //偏移两个,从第三个记录开始返回,limit配合offset达到sqlite的limit m,n的查询
//                        .findAll();

              items=  dbManager.selector(CommMsgSave.class)
                        .where("type","=",type)
                        .findAll();

//                items =dbManager.findAll(CommMsgSave.class);

                if (items==null || items.size()==0){
                    noDataLayout.setVisibility(View.VISIBLE);
                    adapter.clearListData();
                    adapter.notifyDataSetChanged();
                }else {
                    noDataLayout.setVisibility(View.GONE);
                    adapter.setItems(items);
                    adapter.notifyDataSetChanged();

                }
            } catch (DbException e) {
                e.printStackTrace();
            }

        }


}
