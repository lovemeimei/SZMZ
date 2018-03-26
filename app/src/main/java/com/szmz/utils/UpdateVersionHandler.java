
package com.szmz.utils;

import android.app.Activity;
import android.app.Dialog;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.szmz.App;
import com.szmz.SystemEnv;
import com.szmz.entity.AppInfo;
import com.szmz.entity.request.BaseRequest;
import com.szmz.entity.response.JZ_APPInfo_Res;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;

import java.util.List;

import retrofit2.Call;


public class UpdateVersionHandler {


    private Activity context;

    public UpdateVersionHandler(Activity context) {
        this.context = context;
    }

    public void doUpdate(final boolean isShow) {
        Call<JZ_APPInfo_Res> call = App.getApiProxyJZ().getJZ_APPInfo(new BaseRequest());

        ApiUtil<JZ_APPInfo_Res> apiUtil = new ApiUtil<>(context, call, new SimpleApiListener<JZ_APPInfo_Res>() {

            @Override
            public void doAfter() {
                super.doAfter();
            }

            @Override
            public void doSuccess(JZ_APPInfo_Res result) {

                List<AppInfo> result1 = result.Result;
                if (result1 != null && result1.size() > 0) {
                    final AppInfo appInfo = result1.get(0);
                    if (appInfo == null || appInfo.getAppurl() == null) {
                        if (isShow) {
                            doToast("没有可更新版本");
                        }
//                        return;
                    }
                    if (SystemEnv.getVersionCode() - appInfo.getInternalVersion() >= 0) {
                        if (isShow) {
                            doToast("当前版本已是最新");
                        }
//                        return;
                    }
                    UIUtil.showUpdateDialog(context, appInfo.getDescription(), new UIUtil.ClickListener() {
                        @Override
                        public void closeClick() {
                            SystemEnv.systemOut();
                        }

                        @Override
                        public void updateClick(ProgressBar pb, TextView tvProgress, Dialog dialog) {

                            DownloadApkThread thread = new DownloadApkThread(
                                    context, appInfo.getAppurl(), pb,
                                    tvProgress, dialog);
                            thread.start();
                        }
                    });


                } else {
                    if (isShow) {
                        doToast("没有可更新版本");
                    }
                }
            }


        }, isShow);
        apiUtil.excute();
    }


    private void doToast(String string) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
    }

}
