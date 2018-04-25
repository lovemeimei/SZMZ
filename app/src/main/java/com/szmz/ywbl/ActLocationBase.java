package com.szmz.ywbl;

import android.net.Uri;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.orhanobut.logger.Logger;
import com.szmz.App;
import com.szmz.SystemEnv;
import com.szmz.utils.FileUtil;

import java.io.File;
import java.util.UUID;

public abstract class ActLocationBase extends ActTakePhotoBase {

    protected LocationService locationService;
    protected double lng = 0.0;
    protected double lat = 0.0;
    protected BDLocation location;

    @Override
    protected void initUI() {
        super.initUI();
        locationService = App.getLocationService();
        locationService.registerListener(mListener);

    }

    @Override
    protected void onResume() {
        super.onResume();
        locationService.start();
        locationService.refreshLocation();
    }

    private BDLocationListener mListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation loc) {
            if (loc == null) {
                return;
            }
            if (loc.getLongitude() < 0.1 || loc.getLatitude() < 0.1) {
                return;
            }
            location = loc;
            lng = loc.getLongitude();
            lat = loc.getLatitude();
            receivedLocation(loc.getLongitude(), loc.getLatitude(), loc);

        }

        public void onConnectHotSpotMessage(String s, int i) {
        }
    };

    protected void receivedLocation(double lng, double lat, BDLocation loc) {
        Logger.d("获取到经纬度：纬度-" + lat + "  经度-" + lng);
    }

    public void refreshLocation() {
        locationService.refreshLocation();
    }

    /**
     * 拍照
     */
    public void takePhoto() {
        if (location == null) {
            doToast("未获取到GPS信息!");
            refreshLocation();
            return;
        } else {
            if (location.getLatitude() < 0.1 || location.getLongitude() < 0.1) {
                doToast("未获取到GPS信息!");
                refreshLocation();
                return;
            }
        }

        String path = FileUtil.getSDImagePath();

        if (path == null) {
            doToast("请插入存储卡");
            return;
        }

        String strImgPath = path
                + UUID.randomUUID().toString().replaceAll("-", "") + ".jpg";
        Log.v("TakePic", strImgPath);
        SystemEnv.saveImagePath(strImgPath);
        File out = new File(strImgPath);
        Uri uri = Uri.fromFile(out);
        getTakePhoto().onPickFromCapture(uri);

    }
}
