package com.szmz.user.check;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.szmz.ActBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.request.JZ_GetLocation_Detail_req;
import com.szmz.entity.response.JZ_GetLocatinDetail_res;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.DateUtil;
import com.szmz.ywbl.ActMap;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;

public class ActLocationDeatai extends ActBase {

    @BindView(R.id.mapView)
    MapView mMapView;

    BaiduMap mBaiduMap;
    String id;
    String time;
    private InfoWindow mInfoWindow;


    List<JZ_GetLocatinDetail_res.ResultBean>  items = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.act_location_deatai;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setTitle("定位信息");
        setLeftVisible(true);
        id = getIntent().getStringExtra("id");
        time = getIntent().getStringExtra("time");

        initMap();
        loadInfo();
    }

    void loadInfo(){
        if (time==null)
            time="";


        final JZ_GetLocation_Detail_req req = new JZ_GetLocation_Detail_req(id,time);


        Call<JZ_GetLocatinDetail_res> call = App.getApiProxyJZ().getPositionInfo(req);

        ApiUtil<JZ_GetLocatinDetail_res> apiUtil = new ApiUtil<>(context,call,new SimpleApiListener<JZ_GetLocatinDetail_res>(){
            @Override
            public void doAfter() {
                super.doAfter();
            }

            @Override
            public void doSuccess(JZ_GetLocatinDetail_res result) {

                items = result.Result;
                drawMark();
            }
        },true);

        apiUtil.excute();
    }


    void initMap(){
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        MapStatus mMapStatus = new MapStatus.Builder().zoom(18).build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory
                .newMapStatus(mMapStatus);
        mBaiduMap.setMapStatus(mMapStatusUpdate);
        mBaiduMap.setMyLocationEnabled(false);

        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                JZ_GetLocatinDetail_res.ResultBean item =(JZ_GetLocatinDetail_res.ResultBean) marker.getExtraInfo().getSerializable("item");
                String mtime = DateUtil.getInstance().format(item.getSignTime());
                //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
                InfoWindow mInfoWindow = new InfoWindow(getInfoView("姓名："+item.getName(),"时间："+mtime,"位置："+item.getAddress()), marker.getPosition(), -100);
                //显示InfoWindow
                mBaiduMap.showInfoWindow(mInfoWindow);

                return true;
            }
        });
        mBaiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {

                mBaiduMap.hideInfoWindow();
            }

            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus, int i) {

            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {


            }
        });
    }

    void drawMark(){

        if (items==null || items.size()==0){
            return;
        }
        //创建OverlayOptions的集合

        BitmapDescriptor marker = BitmapDescriptorFactory
                .fromResource(R.drawable.markers);

        int i=0;
        for (JZ_GetLocatinDetail_res.ResultBean item :items){



            LatLng point = new LatLng(Double.valueOf(item.getLatitude()),Double.valueOf(item.getLongitude()));
            OverlayOptions option =  new MarkerOptions()
                    .position(point)
                    .icon(marker)
                    .animateType(MarkerOptions.MarkerAnimateType.grow);


            Marker marker2 = (Marker) mBaiduMap.addOverlay(option);
            Bundle bundle  = new Bundle();
            bundle.putSerializable("item",item);
            marker2.setExtraInfo(bundle);


            if (i==0){
                MapStatus mMapStatus = new MapStatus.Builder().target(point).build();
                MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory
                        .newMapStatus(mMapStatus);
                mBaiduMap.setMapStatus(mMapStatusUpdate);


                String mtime = DateUtil.getInstance().format(item.getSignTime());
                //绘制文字
                //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
                InfoWindow mInfoWindow = new InfoWindow(getInfoView("姓名："+item.getName(),"时间："+mtime,"位置："+item.getAddress()), point, -100);
                //显示InfoWindow
                mBaiduMap.showInfoWindow(mInfoWindow);
            }
            i++;
        }


    }


    private View getInfoView(String str1,String str2,String str3) {
       View popView = LayoutInflater.from(this).inflate(R.layout.act_map_pop2, null);
        TextView view1 = (TextView) popView.findViewById(R.id.tv_pop_1);
        TextView view2 = (TextView) popView.findViewById(R.id.tv_pop_2);
        TextView view3 = (TextView) popView.findViewById(R.id.tv_pop_3);

        view1.setText(str1);
        view2.setText(str2);
        view3.setText(str3);
        return popView;
    }
}
