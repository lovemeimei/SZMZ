package com.szmz.ywbl;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.entity.MyNewPhoto;
import com.szmz.widget.ClearEditText;
import com.szmz.widget.GridViewInScrollView;
import com.szmz.widget.ImageGridAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 调查核实页面
 */
public class ActDchs extends ActBase {


    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.dchsqk_ed)
    EditText dchsqkEd;
    @BindView(R.id.dchsjl_ed)
    EditText dchsjlEd;
    @BindView(R.id.bz_ed)
    ClearEditText bzEd;
    @BindView(R.id.mygridview)
    GridViewInScrollView mygridview;
    private ImageGridAdapter adapter;
    private List<MyNewPhoto> paths = new ArrayList<MyNewPhoto>();
    private List<MyNewPhoto> path = new ArrayList<MyNewPhoto>();
    private String imagePath;
    private double lat1 = 0.0;
    private double lng1 = 0.0;
    private String waterword = "";

    @Override
    protected void initUI() {
        super.initUI();
        paths.add(new MyNewPhoto("TakePhoto"));
        mygridview = (GridViewInScrollView) findViewById(R.id.mygridview);
        adapter = new ImageGridAdapter(this, R.layout.grid_image_item,
                10);
        adapter.setImgPaths(paths);
        mygridview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mygridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> av, View v, int p, long arg3) {

                ListAdapter adapter = (ListAdapter) av.getAdapter();
                MyNewPhoto item = (MyNewPhoto) adapter.getItem(p);


            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_dchs;
    }


}
