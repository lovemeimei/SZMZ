package com.szmz.ywbl.dzda;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.YwblDzdaSalvation;
import com.szmz.entity.request.JZ_YWBL_DZDA_SALVATION_RE;
import com.szmz.entity.response.JZ_YWBL_DZDA_Salvation;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.ywbl.ActBaseList;
import com.szmz.ywbl.ActDchs;
import com.szmz.ywbl.ActMzpy;
import com.szmz.ywbl.ActRhcc;
import com.szmz.ywbl.ActShgs;
import com.szmz.ywbl.ActSpgs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import retrofit2.Call;

/**
 * 救助人列表
 */
public class ActYwbl_dzda_person extends ActBaseList<YwblDzdaSalvation> {
    @BindView(R.id.dsNameTv)
    TextView dsNameTv;
    @BindView(R.id.dsLayout)
    LinearLayout dsLayout;
    private Map<Integer, Boolean> map;
    private int type = 0;
    private String regionId = "";//行政区划ID

    @Override
    public void initUI() {
        super.initUI();
        setLeftVisible(true);
        setRightVisible(true);
        setRightShow("保存");
        type = getIntent().getIntExtra("Type", 0);
        switch (type) {
            case 0:
                setTitle("电子档案");
                break;
            case 1:
                setTitle("调查核实");
                break;
            case 2:
                setTitle("民主评议");
                break;
            case 3:
                setTitle("审核公示");
                break;
            case 4:
                setTitle("入户抽查");
                break;
            case 5:
                setTitle("审批公示");
                break;
            case 10:
                setTitle("救助对象信息");
                break;
        }


        map = new HashMap<>();

        dsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        refresh.autoRefresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ywbl_dzda_person;
    }


    @Override
    protected void doRefreshView(int p, final YwblDzdaSalvation item, View view) {
        TextView nameTv = (TextView) view.findViewById(R.id.nameTv);
        TextView timeTv = (TextView) view.findViewById(R.id.timeTv);
        TextView countyTv = (TextView) view.findViewById(R.id.countyTv);
        TextView typeTv = (TextView) view.findViewById(R.id.typeNameTv);
        CheckBox cb = (CheckBox) view.findViewById(R.id.cb);
        nameTv.setText(item.getName());
        timeTv.setText("");
        countyTv.setText(item.getAddress());
        typeTv.setText(item.getSalvationType());

        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    map.put(item.getId(), isChecked);
                } else {
                    map.remove(item.getId());
                }
            }
        });

        if (map != null && map.containsKey(item.getId())) {
            cb.setChecked(map.get(item.getId()));
        } else {
            cb.setChecked(false);
        }
    }

    @Override
    protected int getListItemID() {
        return R.layout.ywbl_person_item;
    }

    @Override
    protected void doListItemOnClick(YwblDzdaSalvation item) {
        super.doListItemOnClick(item);
        Intent intent;
        switch (type) {
            case 0:
                intent = new Intent(this, ActYwbl_dzda_main.class);
                intent.putExtra("YwblPerson", item);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(this, ActDchs.class);
                intent.putExtra("YwblPerson", item);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(this, ActMzpy.class);
                intent.putExtra("YwblPerson", item);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(this, ActShgs.class);
                intent.putExtra("YwblPerson", item);
                startActivity(intent);
                break;
            case 4:
                intent = new Intent(this, ActRhcc.class);
                intent.putExtra("YwblPerson", item);
                startActivity(intent);
                break;
            case 5:
                intent = new Intent(this, ActSpgs.class);
                intent.putExtra("YwblPerson", item);
                startActivity(intent);
                break;
            case 10:
                intent = new Intent(this, ActYwbl_dzda_main.class);
                intent.putExtra("YwblPerson", item);
                startActivity(intent);
                break;
        }

    }

    @Override
    protected void doMore(boolean isMore) {
        if (isMore) {
            CurrentPage++;
        } else {
            CurrentPage = 1;
        }
        doGetData(regionId, CurrentPage);
    }

    private void doGetData(String regionId, final int CurrentPage) {
        JZ_YWBL_DZDA_SALVATION_RE request = new JZ_YWBL_DZDA_SALVATION_RE(regionId, CurrentPage);
        Call<JZ_YWBL_DZDA_Salvation> call = App.getApiProxyJZ().getJZ_SalvationList(request);
        ApiUtil<JZ_YWBL_DZDA_Salvation> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<JZ_YWBL_DZDA_Salvation>() {
            @Override
            public void doSuccess(JZ_YWBL_DZDA_Salvation result) {

                List<YwblDzdaSalvation> items = result.Result;
                if (items != null && items.size() > 0) {
                    if (CurrentPage == 1) {
                        adapter.clearListData();
                    }
                    adapter.setListData(items);
                    adapter.notifyDataSetChanged();
                    noDataLayout.setVisibility(View.GONE);
                    if (isHasNextPage(CurrentPage, PageSize, result.TotalNum)) {
                        refresh.setLoadMore(true);
                    } else {
                        refresh.setLoadMore(false);
                    }
                } else {
                    adapter.clearListData();
                    adapter.setListData(new ArrayList<YwblDzdaSalvation>());
                    adapter.notifyDataSetChanged();
                    noDataLayout.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void doAfter() {

                refresh.finishRefreshing();
                refresh.finishRefreshLoadMore();
            }
        }, false);

        apiUtil.excute();


    }

}
