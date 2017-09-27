package com.szmz.ahdxt.jg;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.materiallistview.MaterialRefreshLayout;
import com.szmz.ActListBase;
import com.szmz.R;
import com.szmz.entity.TestMode;
import com.szmz.utils.BaseListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 监管敏感人员
 */
public class ActJG_ListMGRY2 extends ActListBase {


    @BindView(R.id.lv)
    ListView lv;
    BaseListAdapter<TestMode,MViewHolder> adapter;
    List<TestMode> items = new ArrayList<>();



    @Override
    protected int getLayoutId() {
        return R.layout.comm_list;
    }


    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("敏感人员");


        adapter = new BaseListAdapter<TestMode, MViewHolder>(this,R.layout.list_item_jg_mgry) {
            @Override
            protected void refreshView(int postion, TestMode item, MViewHolder holer) {

                holer.tvSub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        trans(ActJG_YWBL.class);
                    }
                });
                holer.tvSHENHE.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MaterialDialog dialog = new MaterialDialog.Builder(ActJG_ListMGRY2.this)
                                .title("提示")
                                .content("是否进行核对")
                                .onNegative(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                                    }
                                })
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                                    }
                                })
                                .negativeText("取消")
                                .positiveText("确定")
                                .build();
                        dialog.show();
                    }
                });
            }

            @Override
            protected MViewHolder getHolder(View v) {

                MViewHolder holder = new MViewHolder();
                holder.tvType = (TextView)v.findViewById(R.id.tv_type);
                holder.tvName = (TextView)v.findViewById(R.id.tv_name);
                holder.tvSub = (TextView)v.findViewById(R.id.tv_submit);
                holder.tvSHENHE = (TextView)v.findViewById(R.id.tv_shenhe);
                return holder;

            }
        };
        lv.setAdapter(adapter);
        for (int i=0;i<10;i++){
            TestMode item = new TestMode();
            items.add(item);

        }
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void doRefresh(MaterialRefreshLayout materialRefreshLayout) {

    }

    @Override
    public void doRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {

    }

    class MViewHolder{
        TextView tvName;
        TextView tvType;
        TextView tvSub;
        TextView tvSHENHE;
    }
}
