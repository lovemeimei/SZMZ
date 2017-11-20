package com.szmz.ywbl.dzda;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.YwblDzdaFamily;
import com.szmz.entity.YwblDzdaSalvation;
import com.szmz.entity.YwblDzdaXzqh;
import com.szmz.entity.request.JZ_YWBL_DZDA_FAMILY_RE;
import com.szmz.entity.request.JZ_YWBL_DZDA_SALVATION_RE;
import com.szmz.entity.request.JZ_YWBL_DZDA_XZQH_RE;
import com.szmz.entity.response.JZ_YWBL_DZDA_Family;
import com.szmz.entity.response.JZ_YWBL_DZDA_Salvation;
import com.szmz.entity.response.JZ_YWBL_DZDA_XZQH;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.GsonUtil;
import com.szmz.ywbl.ActBaseList;
import com.szmz.ywbl.ActDchs;
import com.szmz.ywbl.ActMzpy;
import com.szmz.ywbl.ActRhcc;
import com.szmz.ywbl.ActShgs;
import com.szmz.ywbl.ActSpgs;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
    private Map<String, YwblDzdaSalvation> map;
    private int type = 0;
    private String regionId = "";//行政区划ID
    private YwblDzdaXzqh xzqh;
    private MaterialDialog dialog;
    private AndroidTreeView tView;
    private MaterialDialog.Builder builder;
    private DbManager db;
    private String keyWords = "";

    @Override
    public void initUI() {
        super.initUI();
        setLeftVisible(true);
        setRightVisible(true);
        setRightShow("保存");
        tvTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null && map.size() > 0) {
                    doSaveData(map);
                }
            }
        });
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

        if (isOnline) {
            doGetXzqh();
            refresh.autoRefresh();
        } else {
            db = x.getDb(App.getDaoConfig());
            try {
                List<YwblDzdaSalvation> items = db.selector(YwblDzdaSalvation.class).findAll();
                if (items != null && items.size() > 0) {
                    adapter.clearListData();
                    adapter.setListData(items);
                    adapter.notifyDataSetChanged();
                    noDataLayout.setVisibility(View.GONE);
                    refresh.setLoadMore(false);
                } else {
                    adapter.clearListData();
                    adapter.setListData(new ArrayList<YwblDzdaSalvation>());
                    adapter.notifyDataSetChanged();
                    noDataLayout.setVisibility(View.VISIBLE);
                }
            } catch (DbException e) {
                e.printStackTrace();
            }
        }

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
                    map.put(item.getId(), item);
                } else {
                    map.remove(item.getId());
                }
            }
        });

        if (map != null && map.containsKey(item.getId())) {
            cb.setChecked(true);
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
        keyWords = searchEd.getText().toString().trim();
        doGetData(regionId, keyWords, CurrentPage);
    }

    private void doGetXzqh() {
        JZ_YWBL_DZDA_XZQH_RE request = new JZ_YWBL_DZDA_XZQH_RE(App.getInstance().getLoginUser().getIdJZ());
        Call<JZ_YWBL_DZDA_XZQH> call = App.getApiProxyJZ().getJZ_XZQHList(request);
        ApiUtil<JZ_YWBL_DZDA_XZQH> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<JZ_YWBL_DZDA_XZQH>() {
            @Override
            public void doSuccess(JZ_YWBL_DZDA_XZQH result) {

                if (result != null) {
                    List<YwblDzdaXzqh> listDatas = result.Result;
                    initData(listDatas);
                }

            }


        }, true);

        apiUtil.excute();
    }

    private void doGetData(String regionId, String keyWords, final int CurrentPage) {
        JZ_YWBL_DZDA_SALVATION_RE request = new JZ_YWBL_DZDA_SALVATION_RE(regionId, keyWords, CurrentPage);
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

    private void initData(List<YwblDzdaXzqh> list) {
        TreeNode root = TreeNode.root();
        List<YwblDzdaXzqh> myList = new ArrayList<YwblDzdaXzqh>();
        for (int i = 0; i < list.size(); i++) {
            boolean isHaveParent = false;
            for (YwblDzdaXzqh item : list) {
                if (item.getId().equals(list.get(i).getParentId())) {
                    isHaveParent = true;
                    break;
                }
            }
            if (!isHaveParent) {
                myList.add(list.get(i));
            }
        }
        for (YwblDzdaXzqh item : myList) {
            TreeNode node = new TreeNode(new JzTreeItemHolder.TreeItem(item)).setViewHolder(new JzTreeItemHolder(this, new JzTreeItemHolder.OnClickChildListener() {
                @Override
                public void doClick(YwblDzdaXzqh item) {
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                    xzqh = item;
                    dsNameTv.setText(item.getRegionname());
                }
            }));
            node = addChildNode(node, list);
            root.addChild(node);
        }
        tView = new AndroidTreeView(this, root);
        tView.setDefaultAnimation(false);
        dsLayout.setOnClickListener(new View.OnClickListener() {
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


    private TreeNode addChildNode(TreeNode node, List<YwblDzdaXzqh> list) {
        for (YwblDzdaXzqh item : list) {
            JzTreeItemHolder.TreeItem value = (JzTreeItemHolder.TreeItem) node.getValue();
            if (value.item.getId().equals(item.getParentId())) {
                node.addChild(addChildNode(new TreeNode(new JzTreeItemHolder.TreeItem(item)).setViewHolder(new JzTreeItemHolder(this, new JzTreeItemHolder.OnClickChildListener() {
                    @Override
                    public void doClick(YwblDzdaXzqh item) {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                        xzqh = item;
                        dsNameTv.setText(item.getRegionname());

                    }
                })), list));
            }
        }

        return node;

    }

    private void doSaveData(final Map<String, YwblDzdaSalvation> myMap) {
        Iterator<Map.Entry<String, YwblDzdaSalvation>> iterator = myMap.entrySet().iterator();
        if (iterator.hasNext()) {
            final Map.Entry<String, YwblDzdaSalvation> next = iterator.next();

            JZ_YWBL_DZDA_FAMILY_RE request = new JZ_YWBL_DZDA_FAMILY_RE(next.getKey());
            Call<JZ_YWBL_DZDA_Family> call = App.getApiProxyJZ().getJZ_GetAllData(request);
            ApiUtil<JZ_YWBL_DZDA_Family> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<JZ_YWBL_DZDA_Family>() {
                @Override
                public void doSuccess(JZ_YWBL_DZDA_Family result) {
                    List<YwblDzdaFamily> family = result.Result;
                    if (family != null && family.size() > 0) {
                        YwblDzdaFamily myFamily = family.get(0);
                        YwblDzdaSalvation value = next.getValue();
                        value.setJsonStr(GsonUtil.ser(myFamily));
                        try {
                            db.save(value);
                            myMap.remove(next.getKey());
                            if (myMap != null && myMap.size() > 0) {
                                doSaveData(myMap);
                            }

                        } catch (DbException e) {
                            e.printStackTrace();
                        }
                    }

                }

            }, true);

            apiUtil.excute();

        }


    }
}
