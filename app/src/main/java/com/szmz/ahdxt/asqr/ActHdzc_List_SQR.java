package com.szmz.ahdxt.asqr;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.response.HD_hdzc;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.FileUtil;
import com.szmz.utils.Md5Util;
import com.szmz.utils.downloadmanager.core.DownloadManagerPro;
import com.szmz.utils.downloadmanager.core.enums.TaskStates;
import com.szmz.utils.downloadmanager.report.listener.DownloadManagerListener;
import com.szmz.ywbl.ActBaseList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

public class ActHdzc_List_SQR extends ActBaseList<HD_hdzc.ResultBean> {

    DownloadManagerPro dm;

    @Override
    public void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("核对政策");
        dm = new DownloadManagerPro(this.getApplicationContext());
        dm.init(FileUtil.getSDDownloadPath(), 12, new DownloadManagerListener() {
            @Override
            public void OnDownloadStarted(long taskId) {

            }

            @Override
            public void OnDownloadPaused(long taskId) {

            }

            @Override
            public void onDownloadProcess(long taskId, double percent, long downloadedLength) {

            }

            @Override
            public void OnDownloadFinished(long taskId) {

            }

            @Override
            public void OnDownloadRebuildStart(long taskId) {

            }

            @Override
            public void OnDownloadRebuildFinished(long taskId) {

            }

            @Override
            public void OnDownloadCompleted(long taskId) {

            }

            @Override
            public void connectionLost(long taskId) {

            }
        });

        refresh.autoRefresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_hdzc__list;
    }

    @Override
    protected void doListItemOnClick(HD_hdzc.ResultBean item) {
        super.doListItemOnClick(item);
        String fileName = item.getFileTitle() + getExt(item.getFilePath());
        if (new File(FileUtil.getSDDownloadPath() + fileName).exists()) {
            FileUtil.openFile(ActHdzc_List_SQR.this, FileUtil.getSDDownloadPath() + fileName);

        } else {
            doShowDialog(fileName, item.getFilePath());
        }
    }

    private void doShowDialog(final String fileName, final String url) {
        final int token = dm.addTask(fileName, url, false, false);
        if (dm.singleDownloadStatus(token).state != TaskStates.READY) {
            return;
        }
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title("系统提示")
                .content("是否确定下载此文件？")
                .positiveText("确定").negativeText("取消")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        try {
                            dm.startDownload(token);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .show();
    }

    @Override
    protected void doRefreshView(int p, HD_hdzc.ResultBean item, View view) {
        TextView nameTv = (TextView) view.findViewById(R.id.nameTv);
        nameTv.setText(item.getFileTitle());

    }

    @Override
    protected int getListItemID() {
        return R.layout.hdxt_zlgl_list_item;
    }

    @Override
    protected void doMore(boolean isMore) {
        String params = "";
        if (isMore) {
            CurrentPage++;
        } else {
            CurrentPage = 1;
        }
        params = getParams("340223199412235063", CurrentPage);

        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), params.getBytes());
        Call<HD_hdzc> call = App.getApiProxy().getHDZCList(requestBody);
        ApiUtil<HD_hdzc> apiUtil = new ApiUtil<HD_hdzc>(this, call, new SimpleApiListener<HD_hdzc>() {
            @Override
            public void doSuccess(HD_hdzc result) {
                List<HD_hdzc.ResultBean> list = result.Result;

                if (list != null && list.size() > 0) {

                    noDataLayout.setVisibility(View.GONE);
                    if (CurrentPage == 1) {
                        adapter.clearListData();
                    }
                    adapter.setListData(list);
                    adapter.notifyDataSetChanged();

                } else {
                    adapter.clearListData();
                    adapter.setListData(new ArrayList<HD_hdzc.ResultBean>());
                    adapter.notifyDataSetChanged();
                    noDataLayout.setVisibility(View.VISIBLE);
                }
                if (isHasNextPage(CurrentPage, PageSize, result.TotalNum)) {
                    refresh.setLoadMore(true);
                } else {
                    refresh.setLoadMore(false);
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
}