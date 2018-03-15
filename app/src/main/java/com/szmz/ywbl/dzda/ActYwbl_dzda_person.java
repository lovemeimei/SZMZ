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
import com.szmz.entity.YwblDzdaFamilyApproveInfo;
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
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.io.Serializable;
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
    private int type = -1;
    private String regionId = "";//行政区划ID
    private YwblDzdaXzqh xzqh;
    private MaterialDialog dialog;
    private AndroidTreeView tView;
    private MaterialDialog.Builder builder;
    private DbManager db;
    private String keyWords = "";

    private YwblDzdaSalvation checkSalvation;
    private List<YwblDzdaSalvation> itemSeleted;
    private boolean isMore = true;//是否多选
    private boolean isChose = false;
    private Map<String, YwblDzdaSalvation> maps;

    private boolean isFromJZXX = false;//是否从救助对象信息模块跳转过来的

    @Override
    public void initUI() {
        super.initUI();
        setLeftVisible(true);
        setRightVisible(true);
        setTitle("数据查询");
        isFromJZXX = getIntent().getBooleanExtra("isFromJZXX", false);
        type = getIntent().getIntExtra("Type", -1);
        isChose = getIntent().getBooleanExtra("isChose", false);
        isMore = getIntent().getBooleanExtra("isMore", true);
        if (isFromJZXX) {
            setTitle("救助对象信息列表");
            setRightVisible(false);
        } else {
            setTitle("电子档案");
            if (!isChose) {
                setRightShow("保存");
                tvTitleRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (maps != null && maps.size() > 0) {
                            doSaveData(maps);
                        }
                    }
                });
            } else {
                setRightShow("确定");
                tvTitleRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isMore) {
                            if (itemSeleted != null && itemSeleted.size() > 0) {
                                Intent intent;
                                switch (type) {
                                    case 2:
                                        intent = new Intent(ActYwbl_dzda_person.this, ActMzpy.class);
                                        intent.putExtra("YwblDzdaSalvations", (Serializable) itemSeleted);
                                        setResult(1111, intent);
                                        finish();
                                        break;
                                    case 4:
                                        intent = new Intent(ActYwbl_dzda_person.this, ActShgs.class);
                                        intent.putExtra("YwblDzdaSalvations", (Serializable) itemSeleted);
                                        setResult(1221, intent);
                                        finish();
                                        break;
                                    case 5:
                                        intent = new Intent(ActYwbl_dzda_person.this, ActSpgs.class);
                                        intent.putExtra("YwblDzdaSalvations", (Serializable) itemSeleted);
                                        setResult(1441, intent);
                                        finish();
                                        break;
                                }

                            } else {
                                doToast("请选择申请人");
                            }
                        } else {
                            if (checkSalvation != null) {
                                Intent intent = new Intent();
                                switch (type) {
                                    case 1:
                                        intent.setClass(ActYwbl_dzda_person.this, ActDchs.class);
                                        intent.putExtra("YwblDzdaSalvation", checkSalvation);
                                        setResult(1001, intent);
                                        finish();
                                        break;
                                    case 3:
                                        intent.setClass(ActYwbl_dzda_person.this, ActRhcc.class);
                                        intent.putExtra("YwblDzdaSalvation", checkSalvation);
                                        setResult(1331, intent);
                                        finish();
                                        break;
                                }

                            } else {
                                doToast("请选择申请人");
                            }
                        }
                    }
                });
            }
        }


        maps = new HashMap<>();
        db = x.getDb(App.getDaoConfig());
        if (isOnline) {
            doGetXzqh();
            dsLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    doGetXzqh();
                }
            });
        } else {
            if (isFromJZXX) {
                return;
            }
            doGetOutlineData("");
        }


        if (isMore) {
            itemSeleted = (List<YwblDzdaSalvation>) getIntent().getSerializableExtra(
                    "YwblDzdaSalvations");
            if (itemSeleted != null && itemSeleted.size() > 0) {
                maps.clear();
                for (YwblDzdaSalvation item : itemSeleted) {
                    maps.put(item.getFamilyId(), item);
                }
            } else {
                itemSeleted = new ArrayList<YwblDzdaSalvation>();
            }
        } else {
            checkSalvation = (YwblDzdaSalvation) getIntent().getSerializableExtra("YwblDzdaSalvation");
            if (checkSalvation != null) {
                maps.clear();
                maps.put(checkSalvation.getFamilyId(), checkSalvation);
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ywbl_dzda_person;
    }


    private void doGetOutlineData(String keyword) {
        try {

            List<YwblDzdaSalvation> items = null;
            if (type != -1) {
                if (keyword == null || keyword.equals("")) {
                    items = db.selector(YwblDzdaSalvation.class).where("type", "=", type).findAll();
                } else {
                    items = db.selector(YwblDzdaSalvation.class).where("type", "=", type).
                            and(WhereBuilder.b("name", "like", "%" + keyword + "%").
                                    or(WhereBuilder.b("disName", "like", "%" + keyword + "%").
                                            or(WhereBuilder.b("address", "like", "%" + keyword + "%"))))
                            .findAll();
                }
            } else {
                if (keyword == null || keyword.equals("")) {
                    items = db.selector(YwblDzdaSalvation.class).findAll();
                } else {
                    items = db.selector(YwblDzdaSalvation.class).
                            where(WhereBuilder.b("name", "like", "%" + keyword + "%").
                                    or(WhereBuilder.b("disName", "like", "%" + keyword + "%").
                                            or(WhereBuilder.b("address", "like", "%" + keyword + "%"))))
                            .findAll();
                }
            }
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
            refresh.finishRefreshing();
            refresh.finishRefreshLoadMore();
        } catch (DbException e) {
            e.printStackTrace();
            refresh.finishRefreshing();
            refresh.finishRefreshLoadMore();
        }

    }

    @Override
    protected void doRefreshView(int p, final YwblDzdaSalvation item, View view) {
        TextView nameTv = (TextView) view.findViewById(R.id.nameTv);
        TextView timeTv = (TextView) view.findViewById(R.id.timeTv);
        TextView countyTv = (TextView) view.findViewById(R.id.countyTv);
        TextView typeTv = (TextView) view.findViewById(R.id.typeNameTv);
        TextView btn_delete = (TextView) view.findViewById(R.id.btn_delete);
        if (item.isSave()) {
            btn_delete.setVisibility(View.VISIBLE);
            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new MaterialDialog.Builder(ActYwbl_dzda_person.this).title("系统提示").content("是否确定删除该条本地数据？").positiveText("确定").negativeText("取消").onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            try {
                                db.deleteById(YwblDzdaSalvation.class, item.getFamilyId());
                                item.setSave(false);
                                doToast("删除成功!");
                                adapter.notifyDataSetChanged();
                            } catch (DbException e) {
                                doToast("删除失败!");
                                e.printStackTrace();
                            }
                        }
                    }).onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                            dialog.dismiss();
                            ;
                        }
                    }).show();

                }
            });
        } else {
            btn_delete.setVisibility(View.GONE);
        }


        nameTv.setText(item.getName() + (item.isSave() ? "（已保存）" : ""));
        timeTv.setText("");

        typeTv.setText(item.getSalvationType());

        CheckBox cb = (CheckBox) view.findViewById(R.id.cb);
        if (isFromJZXX) {
            cb.setVisibility(View.GONE);
            countyTv.setText(item.getDisName());
        } else {
            cb.setVisibility(View.VISIBLE);
            countyTv.setText(item.getAddress());
        }
        cb.setTag(item);
        cb.setFocusable(true);
        cb.setClickable(true);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                YwblDzdaSalvation item = (YwblDzdaSalvation) buttonView.getTag();
                if (isMore) {
                    if (isChecked) {
                        if (!maps.containsKey(item.getFamilyId())) {
                            maps.put(item.getFamilyId(), item);
                            itemSeleted.add(item);
                        }

                    } else {
                        if (maps.containsKey(item.getFamilyId())) {
                            maps.remove(item.getFamilyId());
                            removeById(item.getFamilyId());

                        }

                    }
                } else {
                    if (isChecked) {
                        buttonView.setEnabled(false);
                        maps.clear();
                        maps.put(item.getFamilyId(), item);
                        checkSalvation = item;
                        adapter.notifyDataSetChanged();
                    } else {
                        buttonView.setEnabled(true);
                    }
                }


            }
        });
        if (maps != null && maps.containsKey(item.getFamilyId())) {
            cb.setChecked(true);
            if (!isMore) {
                cb.setEnabled(false);
            }

        } else {
            cb.setChecked(false);
            if (!isMore) {
                cb.setEnabled(true);
            }
        }

    }

    protected void removeById(String id) {
        if (id == null) {
            return;
        }
        if (itemSeleted == null) {
            return;
        }
        for (int i = itemSeleted.size() - 1; i >= 0; i--) {
            if (id.equals(itemSeleted.get(i).getFamilyId())) {
                itemSeleted.remove(i);
            }
        }
    }

    @Override
    protected int getListItemID() {
        return R.layout.ywbl_person_item;
    }

    @Override
    protected void doListItemOnClick(YwblDzdaSalvation item) {
        super.doListItemOnClick(item);
        Intent intent = new Intent(this, ActYwbl_dzda_main.class);
        intent.putExtra("YwblPerson", item);
        intent.putExtra("isFromJZXX", isFromJZXX);
        startActivity(intent);
    }

    @Override
    protected void doMore(boolean isMore) {
        keyWords = searchEd.getText().toString().trim();
        if (!isOnline) {
            doGetOutlineData(keyWords);
            return;
        }
        if (isMore) {
            CurrentPage++;
        } else {
            CurrentPage = 1;
        }
//        doGetData("510421100001", keyWords, CurrentPage);
//        doGetData("620102001004", keyWords, CurrentPage);
        doGetData(App.getInstance().getLoginUser().getIdJZ(), xzqh != null ? xzqh.getRegioncode() : "", keyWords, CurrentPage);
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

    private void doGetData(String userId, String regionId, String keyWords, final int CurrentPage) {
        JZ_YWBL_DZDA_SALVATION_RE request;
        switch (type) {
            case 1:
                request = new JZ_YWBL_DZDA_SALVATION_RE(userId, "20203028", regionId, keyWords, CurrentPage, isFromJZXX);
                break;
            case 2:
                request = new JZ_YWBL_DZDA_SALVATION_RE(userId, "20203029", regionId, keyWords, CurrentPage, isFromJZXX);
                break;
            case 4:
                request = new JZ_YWBL_DZDA_SALVATION_RE(userId, "20203030", regionId, keyWords, CurrentPage, isFromJZXX);
                break;
            case 3:
                request = new JZ_YWBL_DZDA_SALVATION_RE(userId, "20203031", regionId, keyWords, CurrentPage, isFromJZXX);
                break;
            case 5:
                request = new JZ_YWBL_DZDA_SALVATION_RE(userId, "20203032", regionId, keyWords, CurrentPage, isFromJZXX);
                break;
            case -1:
                request = new JZ_YWBL_DZDA_SALVATION_RE(userId, "", regionId, keyWords, CurrentPage, isFromJZXX);
                break;
            default:
                request = new JZ_YWBL_DZDA_SALVATION_RE(userId, "", regionId, keyWords, CurrentPage, isFromJZXX);
                break;


        }

        Call<JZ_YWBL_DZDA_Salvation> call;
        if (isFromJZXX) {
            call = App.getApiProxyJZ().getJZ_SalvationList(request);
        } else {
            call = App.getApiProxyJZ().getJZ_SalvationTempList(request);
        }

        ApiUtil<JZ_YWBL_DZDA_Salvation> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<JZ_YWBL_DZDA_Salvation>() {
            @Override
            public void doSuccess(JZ_YWBL_DZDA_Salvation result) {

                List<YwblDzdaSalvation> items = result.Result;

                if (items != null && items.size() > 0) {
                    for (YwblDzdaSalvation item : items) {
                        YwblDzdaSalvation byId = null;
                        try {
                            byId = db.findById(YwblDzdaSalvation.class, item.getFamilyId());
                        } catch (DbException e) {
                            e.printStackTrace();
                        }
                        if (byId != null) {
                            item.setSave(true);
                        }
                        item.setType(type);
                    }
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
        if (myList != null && myList.size() > 0) {
            xzqh = myList.get(0);
            dsNameTv.setText(xzqh.getRegionname());
            for (YwblDzdaXzqh item : myList) {
                TreeNode node = new TreeNode(new JzTreeItemHolder.TreeItem(item)).setViewHolder(new JzTreeItemHolder(this, new JzTreeItemHolder.OnClickChildListener() {
                    @Override
                    public void doClick(YwblDzdaXzqh item) {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                        xzqh = item;
                        dsNameTv.setText(item.getRegionname());
                        doMore(false);
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
        refresh.autoRefresh();
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
                        doMore(false);

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
            Call<JZ_YWBL_DZDA_Family> call = App.getApiProxyJZ().getJZ_GetAllDataTemp(request);
            ApiUtil<JZ_YWBL_DZDA_Family> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<JZ_YWBL_DZDA_Family>() {
                @Override
                public void doSuccess(JZ_YWBL_DZDA_Family result) {
                    List<YwblDzdaFamily> family = result.Result;
                    if (family != null && family.size() > 0) {
                        YwblDzdaFamily myFamily = family.get(0);
                        YwblDzdaSalvation value = next.getValue();
                        if (value.getType() == -1) {
                            List<YwblDzdaFamilyApproveInfo> familyApproveInfo = myFamily.getFamilyApproveInfo();
                            if (familyApproveInfo != null && familyApproveInfo.size() > 0) {
                                YwblDzdaFamilyApproveInfo ywblDzdaFamilyApproveInfo = familyApproveInfo.get(0);
                                if (ywblDzdaFamilyApproveInfo.getStreetCheckUser() == null || ywblDzdaFamilyApproveInfo.getStreetCheckUser().equals("")) {
                                    value.setType(1);
                                } else if (ywblDzdaFamilyApproveInfo.getStreetCommentUser() == null || ywblDzdaFamilyApproveInfo.getStreetCommentUser().equals("")) {
                                    value.setType(2);
                                } else if (ywblDzdaFamilyApproveInfo.getStreetSubmitUser() == null || ywblDzdaFamilyApproveInfo.getStreetSubmitUser().equals("")) {
                                    value.setType(4);
                                } else if (ywblDzdaFamilyApproveInfo.getCountySpotCheckUser() == null || ywblDzdaFamilyApproveInfo.getCountySpotCheckUser().equals("")) {
                                    value.setType(3);
                                } else if (ywblDzdaFamilyApproveInfo.getCountyPublicUser() == null || ywblDzdaFamilyApproveInfo.getCountyPublicUser().equals("")) {
                                    value.setType(5);
                                }
                            }
                        }
                        value.setJsonStr(GsonUtil.ser(myFamily));
                        try {
                            db.save(value);
                            value.setSave(true);
                            myMap.remove(next.getKey());
                            if (myMap != null && myMap.size() > 0) {
                                doSaveData(myMap);
                            } else {
                                adapter.notifyDataSetChanged();
                            }

                        } catch (DbException e) {
                            e.printStackTrace();
                        }
                    }

                }

            }, false);

            apiUtil.excute();

        }


    }
}
