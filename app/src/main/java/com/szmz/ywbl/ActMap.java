package com.szmz.ywbl;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.szmz.R;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;

/**
 * Created by bz on 2018/4/11.
 */

public class ActMap extends ActLocationBase {
    @BindView(R.id.mapView)
    MapView mMapView;
    private BaiduMap mBaiduMap;
    boolean isFirstLoc = true; // 是否首次定位
    private Marker mMoveMarker;
    private boolean doRef = false;
    private View popView;
    private TextView addressTv;
    private TextView latlngTv;
    private TextView qdTv;
    private GeoCoder coder;
    private InfoWindow mInfoWindow;

    @Override
    protected int getLayoutId() {
        return R.layout.act_map;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    addressTv.setText("正在获取地址...");
                    break;
            }
        }
    };

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("签到");
        popView = getLayoutInflater().inflate(R.layout.act_map_pop, null);
        addressTv = (TextView) popView.findViewById(R.id.addressTv);
        latlngTv = (TextView) popView.findViewById(R.id.latlngTv);
        qdTv = (TextView) popView.findViewById(R.id.qdTv);
        qdTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doToast("签到成功!");
            }
        });
        coder = GeoCoder.newInstance();
        coder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    Toast.makeText(ActMap.this, "抱歉，未获取到地址信息", Toast.LENGTH_LONG)
                            .show();
                    return;
                }
                List<PoiInfo> poiList = result.getPoiList();
                if (poiList != null) {
                    if (poiList.size() > 0) {
                        addressTv.setText(poiList.get(0).name);
                    } else {
                        addressTv.setText(result.getAddressDetail().district + result.getAddressDetail().street + result.getAddressDetail().streetNumber);

                    }

                } else {
                    addressTv.setText("未获取到地址信息");
                }


            }
        });
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        MapStatus mMapStatus = new MapStatus.Builder().zoom(18).build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory
                .newMapStatus(mMapStatus);
        mBaiduMap.setMapStatus(mMapStatusUpdate);
        mBaiduMap.setMyLocationEnabled(false);
        mBaiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {

            @Override
            public void onMapStatusChangeStart(MapStatus arg0) {

            }

            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus, int i) {
                mBaiduMap.hideInfoWindow();
            }

            @Override
            public void onMapStatusChangeFinish(MapStatus arg0) {
                if (!isFirstLoc) {
                    LatLng ptCenter = mBaiduMap.getMapStatus().target; //获取地图中心点坐标
                    DecimalFormat df = new DecimalFormat("######0.00000");
                    double lat = Double.parseDouble(df.format(ptCenter.latitude));
                    double lng = Double.parseDouble(df.format(ptCenter.longitude));
                    latlngTv.setText(lng + "," + lat);
                    mInfoWindow = new InfoWindow(popView, ptCenter, -110);
                    // 反Geo搜索
                    handler.sendEmptyMessage(1);
                    coder.reverseGeoCode(new ReverseGeoCodeOption()
                            .location(ptCenter));
                    mBaiduMap.showInfoWindow(mInfoWindow);
                }
            }

            @Override
            public void onMapStatusChange(MapStatus arg0) {

            }
        });

    }

    @Override
    protected void receivedLocation(double lng, double lat, BDLocation loc) {
        super.receivedLocation(lng, lat, loc);
        // map view 销毁后不在处理新接收的位置
        if (location == null || mMapView == null) {
            return;
        }
        LatLng ll = new LatLng(location.getLatitude(),
                location.getLongitude());
        if (isFirstLoc) {
            isFirstLoc = false;
            MarkerOptions markerOptions = new MarkerOptions().flat(true).anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory
                    .fromResource(R.drawable.icon_geo)).position(ll).draggable(false).zIndex(9);
            mMoveMarker = (Marker) mBaiduMap.addOverlay(markerOptions);
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.target(ll).zoom(18.0f);
            mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            DecimalFormat df = new DecimalFormat("######0.00000");
            double lats = Double.parseDouble(df.format(ll.latitude));
            double lngs = Double.parseDouble(df.format(ll.longitude));
            latlngTv.setText(lngs + "," + lats);
            mInfoWindow = new InfoWindow(popView, ll, -110);
            handler.sendEmptyMessage(1);
            coder.reverseGeoCode(new ReverseGeoCodeOption()
                    .location(ll));
            mBaiduMap.showInfoWindow(mInfoWindow);
        } else {
            if (doRef) {
                mMoveMarker.setPosition(ll);
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                mInfoWindow = new InfoWindow(popView, ll, -110);
                mBaiduMap.showInfoWindow(mInfoWindow);
                handler.sendEmptyMessage(1);
                coder.reverseGeoCode(new ReverseGeoCodeOption()
                        .location(ll));
            }
        }

    }


    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }
}
