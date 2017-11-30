package com.szmz.ywbl.dzda.dasp;

import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.entity.YwblDzdaFamilyApproveInfo;
import com.szmz.widget.ClearEditText;
import com.szmz.widget.GridViewInScrollView;

import butterknife.BindView;

public class ActRhccInfo extends ActBase {

    @BindView(R.id.sqrTv)
    TextView sqrTv;
    @BindView(R.id.sqrLayout)
    LinearLayout sqrLayout;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.timeLayout)
    LinearLayout timeLayout;
    @BindView(R.id.dcrTv)
    EditText dcrTv;
    @BindView(R.id.dcrLayout)
    LinearLayout dcrLayout;
    @BindView(R.id.fzrTv)
    EditText fzrTv;
    @BindView(R.id.fzrLayout)
    LinearLayout fzrLayout;
    @BindView(R.id.rhccjgEd)
    EditText rhccjgEd;
    @BindView(R.id.ed_wxms)
    ClearEditText edWxms;
    @BindView(R.id.mygridview)
    GridViewInScrollView mygridview;
    @BindView(R.id.bg)
    ImageView bg;
    @BindView(R.id.img)
    PhotoView img;
    @BindView(R.id.parent)
    FrameLayout parent;
    private YwblDzdaFamilyApproveInfo info;

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("入户抽查");
        info = (YwblDzdaFamilyApproveInfo) getIntent().getSerializableExtra("YwblDzdaFamilyApproveInfo");

        sqrTv.setText(info.getHeadName());
        fzrTv.setText(info.getCountySpotCheckChargeUser());
        dcrTv.setText(info.getCountySpotCheckUser());
        time.setText(info.getCountySpotCheckTime());
        rhccjgEd.setText(info.getCountySpotCheckResult());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_rhcc_info;
    }


}
