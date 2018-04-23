package com.szmz.ywbl;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.utils.SpatialRelationUtil;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.request.JZ_YWBL_APPSIGN_RE;
import com.szmz.entity.response.CommResponse;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.DateUtil;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;

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
    private String latStr, lngStr;
    private String addressStr;
    private String familyId;
    private String dicId;

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
                    if (addressTv != null) {
                        addressTv.setText("正在获取地址...");
                    }
                    break;
            }
        }
    };

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("签到");
        familyId = getIntent().getStringExtra("familyId");
        dicId = getIntent().getStringExtra("dicId");

        coder = GeoCoder.newInstance();
        coder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
//                    Toast.makeText(ActMap.this, "抱歉，未获取到地址信息", Toast.LENGTH_LONG)
//                            .show();
                    addressStr = "";
                    return;
                }
                if (popView != null) {
                    addressTv = (TextView) popView.findViewById(R.id.addressTv);
                }
                List<PoiInfo> poiList = result.getPoiList();
                if (poiList != null) {
                    if (poiList.size() > 0) {
                        addressStr = result.getAddress() + "(" + poiList.get(0).name + ")";
                        addressTv.setText(addressStr);
                    } else {
                        addressStr = result.getAddress();
                        addressTv.setText(result.getAddress());

                    }

                } else {
                    addressStr = "";
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
            public void onMapStatusChangeStart(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus, int i) {

            }


            @Override
            public void onMapStatusChangeFinish(MapStatus arg0) {
                LatLng ptCenter = mBaiduMap.getMapStatus().target; //获取地图中心点坐标
                if (SpatialRelationUtil.isCircleContainsPoint(ll, radius, ptCenter)) {
                    DecimalFormat df = new DecimalFormat("######0.00000");
                    latStr = df.format(ptCenter.latitude);
                    lngStr = df.format(ptCenter.longitude);
                    double lat = Double.parseDouble(latStr);
                    double lng = Double.parseDouble(lngStr);
                    mInfoWindow = new InfoWindow(getInfoView(), ptCenter, -110);
                    latlngTv.setText(lng + "," + lat);
                    handler.sendEmptyMessage(1);
                    coder.reverseGeoCode(new ReverseGeoCodeOption()
                            .location(ptCenter));
                    mBaiduMap.showInfoWindow(mInfoWindow);
                } else {
                    DecimalFormat df = new DecimalFormat("######0.00000");
                    latStr = df.format(ptCenter.latitude);
                    lngStr = df.format(ptCenter.longitude);
                    double lat = Double.parseDouble(latStr);
                    double lng = Double.parseDouble(lngStr);
                    mInfoWindow = new InfoWindow(getInfoView(), ptCenter, -110);
                    latlngTv.setText(lng + "," + lat);
                    addressStr = "签到位置无效!";
                    addressTv.setText(addressStr);
                    mBaiduMap.showInfoWindow(mInfoWindow);
                }
            }

            @Override
            public void onMapStatusChange(MapStatus arg0) {
                mBaiduMap.hideInfoWindow();
            }
        });

    }

    private LatLng ll;
    private int radius = 500;

    @Override
    protected void receivedLocation(double lng, double lat, BDLocation loc) {
        super.receivedLocation(lng, lat, loc);
        // map view 销毁后不在处理新接收的位置
        if (location == null || mMapView == null) {
            return;
        }

        if (isFirstLoc) {
            ll = new LatLng(location.getLatitude(),
                    location.getLongitude());
            isFirstLoc = false;

            MarkerOptions markerOptions = new MarkerOptions().flat(true).anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory
                    .fromResource(R.drawable.icon_geo)).position(ll).draggable(false).zIndex(9);
            mMoveMarker = (Marker) mBaiduMap.addOverlay(markerOptions);
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.target(ll).zoom(16.5f);
            mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            OverlayOptions ooCircle = new CircleOptions().fillColor(0x22ff0000)
                    .center(ll).stroke(new Stroke(5, 0x22ff0000))
                    .radius(radius);
            mBaiduMap.addOverlay(ooCircle);
            DecimalFormat df = new DecimalFormat("######0.00000");
            latStr = df.format(ll.latitude);
            lngStr = df.format(ll.longitude);
            double lats = Double.parseDouble(latStr);
            double lngs = Double.parseDouble(lngStr);
            mInfoWindow = new InfoWindow(getInfoView(), ll, -110);
            mBaiduMap.showInfoWindow(mInfoWindow);
            latlngTv.setText(lngs + "," + lats);
            handler.sendEmptyMessage(1);
            coder.reverseGeoCode(new ReverseGeoCodeOption()
                    .location(ll));

        } else {
            LatLng ll = new LatLng(location.getLatitude(),
                    location.getLongitude());
            if (doRef) {
                mMoveMarker.setPosition(ll);
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(16.5f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                mInfoWindow = new InfoWindow(getInfoView(), ll, -110);
                mBaiduMap.showInfoWindow(mInfoWindow);
                handler.sendEmptyMessage(1);
                coder.reverseGeoCode(new ReverseGeoCodeOption()
                        .location(ll));
            }
        }

    }

    private View getInfoView() {
        popView = LayoutInflater.from(ActMap.this).inflate(R.layout.act_map_pop, null);
        addressTv = (TextView) popView.findViewById(R.id.addressTv);
        latlngTv = (TextView) popView.findViewById(R.id.latlngTv);
        qdTv = (TextView) popView.findViewById(R.id.qdTv);
        qdTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSign();
            }
        });
        return popView;
    }

    private void doSign() {
        if ("签到位置无效!".equals(addressStr)) {
            doToast("签到位置无效!");
            return;
        }
        JZ_YWBL_APPSIGN_RE request = new JZ_YWBL_APPSIGN_RE(familyId, dicId, getUser().getIdJZ(), addressStr, DateUtil.getCurrentTime3(), lngStr, latStr);
        Call<CommResponse> call = App.getApiProxyJZ().getJZ_AppSign(request);
        ApiUtil<CommResponse> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<CommResponse>() {
            @Override
            public void doSuccess(CommResponse result) {
                doToast("签到成功!");
                finish();
            }


        }, true);

        apiUtil.excute();
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
