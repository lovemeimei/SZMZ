package com.szmz.ywbl;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.MyNewPhoto;
import com.szmz.entity.YwblSaveDataRequest;
import com.szmz.entity.request.JZ_YWBL_ADDDATA_RE;
import com.szmz.entity.request.JZ_YWBL_DCHS_RE;
import com.szmz.entity.request.JZ_YWBL_MZPY_RE;
import com.szmz.entity.request.JZ_YWBL_RHCC_RE;
import com.szmz.entity.request.JZ_YWBL_SHGS_RE;
import com.szmz.entity.request.JZ_YWBL_SPGS_RE;
import com.szmz.entity.response.CommResponse;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.GsonUtil;
import com.szmz.utils.ImageUtil;

import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * 离线数据
 */
public class ActLxsj extends ActBaseList<YwblSaveDataRequest> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_lxsj;
    }

    @Override
    protected void doListItemOnClick(YwblSaveDataRequest item) {
        super.doListItemOnClick(item);
        Intent intent = null;
        switch (item.getType()) {

            case 1:
                intent = new Intent(ActLxsj.this, ActDchs.class);
                break;
            case 2:
                intent = new Intent(ActLxsj.this, ActMzpy.class);
                break;
            case 3:
                intent = new Intent(ActLxsj.this, ActRhcc.class);
                break;
            case 4:
                intent = new Intent(ActLxsj.this, ActShgs.class);
                break;
            case 5:
                intent = new Intent(ActLxsj.this, ActSpgs.class);
                break;
            default:
                intent = new Intent(ActLxsj.this, ActDchs.class);
                break;
        }
        intent.putExtra("YwblSaveDataRequest", item);
        startActivity(intent);

    }

    @Override
    protected void doRefreshView(int p, final YwblSaveDataRequest item, View view) {
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView type = (TextView) view.findViewById(R.id.type);
        TextView submit = (TextView) view.findViewById(R.id.submit);
        name.setText(item.getName());
        TextView delete = (TextView) view.findViewById(R.id.delete);
        switch (item.getType()) {
            case 1:
                type.setText("调查核实");
                break;
            case 2:
                type.setText("民主评议");
                break;
            case 3:
                type.setText("入户抽查");
                break;
            case 4:
                type.setText("审核公示");
                break;
            case 5:
                type.setText("审批公示");
                break;
            default:
                type.setText("调查核实");
                break;

        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                doSubmit(item.getType(), item);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(ActLxsj.this).title("系统提示").content("该条数据并未上传，是否确定删除该条数据？").positiveText("确定")
                        .negativeText("取消").onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                }).onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        try {
                            dbManager.delete(item);
                            doToast("删除成功!");
                        } catch (DbException e) {
                            e.printStackTrace();
                            doToast("删除失败!");
                        }
                        refresh.autoRefresh();
                    }
                }).show();

            }
        });


    }

    private void doSubmit(int type, final YwblSaveDataRequest request) {

        if (type == 1) {
            Call<CommResponse> call = App.getApiProxyJZ().getJZ_AddStreetCheck(GsonUtil.deser(request.getJsonStr(), JZ_YWBL_DCHS_RE.class));
            ApiUtil<CommResponse> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<CommResponse>() {
                @Override
                public void doSuccess(CommResponse result) {
                    List<MyNewPhoto> path = new Gson().fromJson(request.getImageJsonStr(), new TypeToken<List<MyNewPhoto>>() {
                    }.getType());
                    for (MyNewPhoto photo : path) {
                        doUpLoadImage("20203028",
                                request.getId().replace("DCHS", ""), ImageUtil.getThumbImagePath(photo.getFileUrl(), null), request.getAddress());
                    }
                    doToast("上报成功!");
                    try {
                        dbManager.delete(request);
                        refresh.autoRefresh();
                    } catch (DbException e) {
                        e.printStackTrace();
                    }

                }


            }, true);

            apiUtil.excute();
        } else if (type == 2) {
            Call<CommResponse> call = App.getApiProxyJZ().getJZ_AddStreetComment(GsonUtil.deser(request.getJsonStr(), JZ_YWBL_MZPY_RE.class));
            ApiUtil<CommResponse> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<CommResponse>() {
                @Override
                public void doSuccess(CommResponse result) {
                    List<MyNewPhoto> path = new Gson().fromJson(request.getImageJsonStr(), new TypeToken<List<MyNewPhoto>>() {
                    }.getType());
                    for (MyNewPhoto photo : path) {
                        doUpLoadImage("20203029", request.getId().replace("MZPY", ""), ImageUtil.getThumbImagePath(photo.getFileUrl(), null), request.getAddress());
                    }
                    doToast("上报成功!");
                    try {
                        dbManager.delete(request);
                        refresh.autoRefresh();
                    } catch (DbException e) {
                        e.printStackTrace();
                    }

                }


            }, true);

            apiUtil.excute();
        } else if (type == 3) {
            Call<CommResponse> call = App.getApiProxyJZ().getJZ_AddCountySpot(GsonUtil.deser(request.getJsonStr(), JZ_YWBL_RHCC_RE.class));
            ApiUtil<CommResponse> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<CommResponse>() {
                @Override
                public void doSuccess(CommResponse result) {
                    List<MyNewPhoto> path = new Gson().fromJson(request.getImageJsonStr(), new TypeToken<List<MyNewPhoto>>() {
                    }.getType());
                    for (MyNewPhoto photo : path) {
                        doUpLoadImage("20203031", request.getId().replace("RHCC", ""), ImageUtil.getThumbImagePath(photo.getFileUrl(), null), request.getAddress());
                    }
                    doToast("上报成功!");
                    try {
                        dbManager.delete(request);
                        refresh.autoRefresh();
                    } catch (DbException e) {
                        e.printStackTrace();
                    }

                }


            }, true);

            apiUtil.excute();
        } else if (type == 4) {
            Call<CommResponse> call = App.getApiProxyJZ().getJZ_AddStreetPublic(GsonUtil.deser(request.getJsonStr(), JZ_YWBL_SHGS_RE.class));
            ApiUtil<CommResponse> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<CommResponse>() {
                @Override
                public void doSuccess(CommResponse result) {
                    List<MyNewPhoto> path = new Gson().fromJson(request.getImageJsonStr(), new TypeToken<List<MyNewPhoto>>() {
                    }.getType());
                    for (MyNewPhoto photo : path) {
                        doUpLoadImage("20203030", request.getId().replace("SHGS", ""), ImageUtil.getThumbImagePath(photo.getFileUrl(), null), request.getAddress());
                    }
                    doToast("上报成功!");
                    finish();

                }


            }, true);

            apiUtil.excute();
        } else if (type == 5) {
            Call<CommResponse> call = App.getApiProxyJZ().getJZ_AddCountyPublic(GsonUtil.deser(request.getJsonStr(), JZ_YWBL_SPGS_RE.class));
            ApiUtil<CommResponse> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<CommResponse>() {
                @Override
                public void doSuccess(CommResponse result) {
                    List<MyNewPhoto> path = new Gson().fromJson(request.getImageJsonStr(), new TypeToken<List<MyNewPhoto>>() {
                    }.getType());
                    for (MyNewPhoto photo : path) {
                        doUpLoadImage("20203032", request.getId().replace("SPGS", ""), ImageUtil.getThumbImagePath(photo.getFileUrl(), null), request.getAddress());
                    }
                    doToast("上报成功!");
                    finish();

                }


            }, true);

            apiUtil.excute();
        }
    }

    private void doUpLoadImage(String dicid, String familyId, String url, String address) {
        JZ_YWBL_ADDDATA_RE request = new JZ_YWBL_ADDDATA_RE(familyId, dicid, ImageUtil.pictureToBase64(url), address);
        Call<CommResponse> call = App.getApiProxyJZ().getJZ_AddData(request);
        ApiUtil<CommResponse> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<CommResponse>() {
            @Override
            public void doSuccess(CommResponse result) {
                super.doSuccess(result);
                System.out.print("照片上传成功!");
            }
        }, false);
        apiUtil.excute();
    }

    @Override
    protected int getListItemID() {
        return R.layout.ywbl__lxsj_item;
    }

    @Override
    protected void doMore(boolean isMore) {
        refresh.finishRefresh();
        refresh.finishRefreshLoadMore();
        List<YwblSaveDataRequest> result = null;
        try {
            result = dbManager.findAll(YwblSaveDataRequest.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        if (result != null && result.size() > 0) {
            adapter.clearListData();
            adapter.setListData(result);
            adapter.notifyDataSetChanged();
            noDataLayout.setVisibility(View.GONE);

        } else {
            adapter.clearListData();
            adapter.setListData(new ArrayList<YwblSaveDataRequest>());
            adapter.notifyDataSetChanged();
            noDataLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("离线数据");
        refresh.autoRefresh();
    }


}
