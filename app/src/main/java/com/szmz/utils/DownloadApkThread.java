package com.szmz.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.szmz.SystemEnv;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class DownloadApkThread extends Thread {
    private String downloadUrl = null;
    private static final int DOWNLOAD = 1;
    private static final int DOWNLOAD_FINISH = 2;
    private static final int SDK_ERROR = 3;
    private static final int FILETYPE_ERROR = 4;
    private static final int DOWNLOAD_ERROR = 5;
    private static final int CANCEL_DOWNLOAD = 6;

    private boolean cancelUpdate = false;
    private ProgressBar pb;
    private Context act;
    private TextView tvProgress;
    private Dialog dialog;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWNLOAD:
                    if (pb != null) {
                        pb.setEnabled(false);
                        pb.setClickable(false);
                        pb.setProgress(msg.arg1);
                    }
                    if (tvProgress != null) {
                        tvProgress.setText("已下载" + msg.arg1 + "%");
                    }
                    break;
                case DOWNLOAD_FINISH:
                    doToast("下载完成，正在安装");
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                    installApk();
                    SystemEnv.systemOut();
                    break;
                case SDK_ERROR:
                    doToast("无法保存到SD卡中");
                    pb.setProgress(100);
                    tvProgress.setText("");
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                    break;
                case FILETYPE_ERROR:
                    doToast("不是有效的更新包");
                    pb.setProgress(100);
                    tvProgress.setText("");
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                    break;
                case DOWNLOAD_ERROR:
                    doToast("下载失败");
                    pb.setProgress(100);
                    tvProgress.setText("");
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                    break;
                case CANCEL_DOWNLOAD:
                    pb.setProgress(100);
                    tvProgress.setText("");
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                    break;
                default:
                    break;
            }
        }

    };

    private String getFileName(String downloadUrl) {
        try {
            URL url = new URL(downloadUrl);
            String fileName = url.getPath();

            if (fileName == null || fileName.equals("")) {
                return null;
            }

            fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
            fileName = fileName.toLowerCase();
            if (!fileName.endsWith(".apk")) {
                return null;
            }

            return fileName;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 安装APK文件
     */
    private void installApk() {
        try {
            String url = this.downloadUrl;
            String downloadPath = FileUtil.getSDDownloadPath();
            String fileName = getFileName(url);
            File apkFile = new File(downloadPath, fileName);
            Uri downloadUri = Uri.fromFile(apkFile);
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            String type = "application/vnd.android.package-archive";
            intent.setDataAndType(downloadUri, type);
            act.startActivity(intent);
        } catch (Exception e) {
            doToast("下载出错");
            if (dialog != null) {
                dialog.dismiss();
            }
        }

    }

    public DownloadApkThread(Context act, String url, ProgressBar pb,
                             TextView tvProgress, Dialog dialog) {
        this.act = act;
        this.downloadUrl = url;
        this.pb = pb;
        this.tvProgress = tvProgress;
        this.dialog = dialog;
    }

    @Override
    public void run() {
        try {

            String downloadPath = FileUtil.getSDDownloadPath();

            if (downloadPath == null) {
                handler.sendEmptyMessage(SDK_ERROR);
                return;
            }

            File file = new File(downloadPath);

            if (!file.exists()) {
                file.mkdir();
            }

            String fileName = getFileName(downloadUrl);
            if (fileName == null) {
                handler.sendEmptyMessage(FILETYPE_ERROR);
                return;
            }

            File apkFile = new File(downloadPath, fileName);
            if (apkFile.exists()) {
                apkFile.delete();
            }

            if (cancelUpdate) {
                handler.sendEmptyMessage(CANCEL_DOWNLOAD);
                cancelUpdate = false;
                return;
            }

            URL url = new URL(downloadUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();

            int length = conn.getContentLength();

            InputStream is = null;
            FileOutputStream fos = null;

            try {

                is = conn.getInputStream();
                fos = new FileOutputStream(apkFile);

                int count = 0;
                byte buf[] = new byte[1024];

                do {
                    if (cancelUpdate) {
                        handler.sendEmptyMessage(CANCEL_DOWNLOAD);
                        cancelUpdate = false;
                        return;
                    }

                    int numread = is.read(buf);
                    count += numread;

                    int progress = (int) (((float) count / length) * 100);
                    Message msg = new Message();
                    msg.what = DOWNLOAD;
                    msg.arg1 = progress;
                    handler.sendMessage(msg);
                    if (numread <= 0) {
                        handler.sendEmptyMessage(DOWNLOAD_FINISH);
                        break;
                    }

                    fos.write(buf, 0, numread);

                } while (true);

            } finally {
                if (fos != null)
                    fos.close();

                if (is != null)
                    is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            handler.sendEmptyMessage(DOWNLOAD_ERROR);
        }

    }

    private void doToast(String string) {
        Toast.makeText(act, string, Toast.LENGTH_SHORT).show();
    }
}