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

public class ActShgsInfo extends ActBase {


    @BindView(R.id.sqrTv)
    TextView sqrTv;
    @BindView(R.id.sqrLayout)
    LinearLayout sqrLayout;
    @BindView(R.id.timeStartTv)
    TextView timeStartTv;
    @BindView(R.id.timeStartLayout)
    LinearLayout timeStartLayout;
    @BindView(R.id.timeEndTv)
    TextView timeEndTv;
    @BindView(R.id.timeEndLayout)
    LinearLayout timeEndLayout;
    @BindView(R.id.gsjlrEd)
    EditText gsjlrEd;
    @BindView(R.id.shfzrEd)
    EditText shfzrEd;
    @BindView(R.id.gsjgEd)
    EditText gsjgEd;
    @BindView(R.id.gsyynrEd)
    EditText gsyynrEd;
    @BindView(R.id.shyjEd)
    EditText shyjEd;
    @BindView(R.id.shyjyyEd)
    EditText shyjyyEd;
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
        setTitle("审核公示");
        info = (YwblDzdaFamilyApproveInfo) getIntent().getSerializableExtra("YwblDzdaFamilyApproveInfo");

        sqrTv.setText(info.getHeadName());
        timeStartTv.setText(info.getStreetPublicStartTime());
        timeEndTv.setText(info.getStreetPublicEndTime());
        gsjlrEd.setText(info.getStreetPublicUser());
        shfzrEd.setText(info.getStreetApproveChargeUser());
        gsjgEd.setText(info.getStreetPublicResult());
        gsyynrEd.setText(info.getStreetPublicObjectionInfo());
        shyjEd.setText(info.getStreetApproveInfo());
        shyjyyEd.setText(info.getStreetApproveReason());


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_shgs_info;
    }

}
