package com.szmz.ahdxt.asqr;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.HdxtGrcxInfo;
import com.szmz.entity.response.HD_SQR_GRCX_JDCK_RES;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.FileUtil;
import com.szmz.utils.Md5Util;
import com.szmz.ywbl.ActBaseList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

public class ActGrcx_SQXXCK_List extends ActBaseList<HdxtGrcxInfo> {

    BroadcastReceiver receiver;

    @Override
    public void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("个人申请");
        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                long reference = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                for (HdxtGrcxInfo item : adapter.getListData()) {
                    if (item.getReference() == reference) {
                        item.setDownLoading(false);
                    }
                }

            }
        };
        registerReceiver(receiver, filter);
        refresh.setLoadMore(false);
        refresh.autoRefresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_grcx__sqxxck;
    }

    @Override
    protected void doRefreshView(int p, final HdxtGrcxInfo item, View view) {
        TextView nameTv = (TextView) view.findViewById(R.id.nameTv);
        TextView timeTv = (TextView) view.findViewById(R.id.timeTv);
        TextView typeTv = (TextView) view.findViewById(R.id.typeTv);
        if (item.isDownLoading()) {
            nameTv.setText(item.getApplyName() + "(下载中)");
        } else {
            nameTv.setText(item.getApplyName());
        }
        timeTv.setText(item.getEntrustTime());
        typeTv.setText(item.getBizCategory());
        TextView button1 = (TextView) view.findViewById(R.id.button1);
        TextView button2 = (TextView) view.findViewById(R.id.button2);
        TextView button3 = (TextView) view.findViewById(R.id.button3);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActGrcx_SQXXCK_List.this, ActGrcx_SQXXCK_Detail.class);
                intent.putExtra("HdxtGrcxInfo", item);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doGetDownLoadInfo(item, 0);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doGetDownLoadInfo(item, 1);
            }
        });
    }


    @Override
    protected int getListItemID() {
        return R.layout.hdxt_grcx_sqr_grxxck_list_item;
    }

    @Override
    protected void doMore(boolean isMore) {

        if (isMore) {
            CurrentPage++;
        } else {
            CurrentPage = 1;
        }
        doGetData();

    }

    private void doGetData() {
        String params = getParams("340223199412235063", CurrentPage);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), params.getBytes());
        Call<HD_SQR_GRCX_JDCK_RES> call = App.getApiProxy().getApplyInfoList(requestBody);
        ApiUtil<HD_SQR_GRCX_JDCK_RES> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<HD_SQR_GRCX_JDCK_RES>() {
            @Override
            public void doSuccess(HD_SQR_GRCX_JDCK_RES response) {
                List<HdxtGrcxInfo> result = response.Result;
                if (result != null && result.size() > 0) {
                    if (CurrentPage == 1) {
                        adapter.clearListData();
                    }

                    adapter.setListData(result);
                    adapter.notifyDataSetChanged();
                    noDataLayout.setVisibility(View.GONE);
                    if (isHasNextPage(CurrentPage, PageSize, 20)) {
                        refresh.setLoadMore(true);
                    } else {
                        refresh.setLoadMore(false);
                    }
                } else {
                    adapter.clearListData();
                    adapter.setListData(new ArrayList<HdxtGrcxInfo>());
                    adapter.notifyDataSetChanged();
                    noDataLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void doAfter() {
                super.doAfter();
                refresh.finishRefresh();
                refresh.finishRefreshLoadMore();
            }
        }, false);

        apiUtil.excute();
    }

    private String getParams(String idCardNo, int currentPage) {
        String md5key = Md5Util.getMd5(idCardNo + currentPage + "20");
        StringBuilder sb = new StringBuilder();
        sb.append("idCardNo=");
        sb.append(idCardNo);
        sb.append("&");
        sb.append("CurrentPage=");
        sb.append(currentPage);
        sb.append("&");
        sb.append("PageSize=20&");
        sb.append("Md5Key=");
        sb.append(md5key);
        return sb.toString();
    }


    private void doGetDownLoadInfo(final HdxtGrcxInfo info, final int type) {

        String md5key = Md5Util.getMd5(info.getIdCardNo() + info.getBatchId() + info.getApplyId());
        StringBuilder sb = new StringBuilder();
        sb.append("idCardNo=");
        sb.append(info.getIdCardNo());
        sb.append("&");
        sb.append("batchId=");
        sb.append(info.getBatchId());
        sb.append("&");
        sb.append("applyId=");
        sb.append(info.getApplyId());
        sb.append("&");
        sb.append("Md5Key=");
        sb.append(md5key);
        String params = sb.toString();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), params.getBytes());
        if (type == 0) {

            Call<HD_SQR_GRCX_JDCK_RES> call = App.getApiProxy().getDownEnclosureInfo(requestBody);
            ApiUtil<HD_SQR_GRCX_JDCK_RES> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<HD_SQR_GRCX_JDCK_RES>() {
                @Override
                public void doSuccess(HD_SQR_GRCX_JDCK_RES response) {
                    List<HdxtGrcxInfo> result = response.Result;
                    if (result != null && result.size() > 0) {
                        HdxtGrcxInfo hdxtGrcxInfo = result.get(0);
                        String fileName = info.getApplyName() + "附件" + getExt(hdxtGrcxInfo.getFilePath());
                        if (new File(FileUtil.getSDDownloadPath() + fileName).exists()) {
                            FileUtil.openFile(ActGrcx_SQXXCK_List.this, FileUtil.getSDDownloadPath() + fileName);

                        } else {

                            doShowDialog(hdxtGrcxInfo, fileName, hdxtGrcxInfo.getFilePath());
                        }

                    }
                }
            }, true);
            apiUtil.excute();
        } else {
            Call<HD_SQR_GRCX_JDCK_RES> call = App.getApiProxy().getDownReport(requestBody);
            ApiUtil<HD_SQR_GRCX_JDCK_RES> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<HD_SQR_GRCX_JDCK_RES>() {
                @Override
                public void doSuccess(HD_SQR_GRCX_JDCK_RES response) {
                    List<HdxtGrcxInfo> result = response.Result;
                    if (result != null && result.size() > 0) {
                        HdxtGrcxInfo hdxtGrcxInfo = result.get(0);
                        String fileName = info.getApplyName() + "报告" + getExt(hdxtGrcxInfo.getFilePath());
                        if (new File(FileUtil.getSDDownloadPath() + fileName).exists()) {
                            FileUtil.openFile(ActGrcx_SQXXCK_List.this, FileUtil.getSDDownloadPath() + fileName);
                        } else {
                            doShowDialog(hdxtGrcxInfo, fileName, hdxtGrcxInfo.getFilePath());
                        }
                    }
                }
            }, true);
            apiUtil.excute();
        }

    }

    private void doShowDialog(final HdxtGrcxInfo hdzc, final String fileName, final String url) {

        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title("系统提示")
                .content("是否确定下载此文件？")
                .positiveText("确定").negativeText("取消")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                        //设置在什么网络情况下进行下载
//                        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
                        //设置通知栏标题
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
                        request.setTitle(fileName);
                        request.setDescription(fileName + "正在下载");
                        request.setAllowedOverRoaming(false);
                        //设置文件存放目录
                        request.setDestinationInExternalFilesDir(ActGrcx_SQXXCK_List.this, FileUtil.getSDDownloadPath(), fileName);
                        String serviceString = Context.DOWNLOAD_SERVICE;
                        DownloadManager downloadManager;
                        downloadManager = (DownloadManager) getSystemService(serviceString);
                        long reference = downloadManager.enqueue(request);
                        hdzc.setReference(reference);
                        adapter.notifyDataSetChanged();
                    }
                })
                .show();
    }


}
