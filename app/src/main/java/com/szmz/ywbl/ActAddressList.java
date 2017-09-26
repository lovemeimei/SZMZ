package com.szmz.ywbl;

import android.widget.ListView;

import com.baidu.mapapi.map.MapView;
import com.szmz.R;

import butterknife.BindView;

public class ActAddressList extends ActLocationBase {


    @BindView(R.id.mapView)
    MapView mapView;
    @BindView(R.id.listView)
    ListView listView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_address_list;
    }

    @Override
    protected void initUI() {
        super.initUI();
    }


}
