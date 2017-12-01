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

public class ActMzpyInfo extends ActBase {

    @BindView(R.id.sqrTv)
    TextView sqrTv;
    @BindView(R.id.sqrLayout)
    LinearLayout sqrLayout;
    @BindView(R.id.pysjTv)
    TextView pysjTv;
    @BindView(R.id.timeLayout)
    LinearLayout timeLayout;
    @BindView(R.id.fzrTv)
    EditText fzrTv;
    @BindView(R.id.jlrTv)
    EditText jlrTv;
    @BindView(R.id.pyjlEd)
    EditText pyjlEd;
    @BindView(R.id.ed_bz)
    ClearEditText edBz;
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
        setTitle("民主评议");
        info = (YwblDzdaFamilyApproveInfo) getIntent().getSerializableExtra("YwblDzdaFamilyApproveInfo");

        sqrTv.setText(info.getHeadName());
        fzrTv.setText(info.getStreetCommentChargeUser());
        jlrTv.setText(info.getStreetCommentUser());
        pysjTv.setText(info.getStreetCommentTime());
        pyjlEd.setText(info.getStreetCommentResult());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_mzpy_info;
    }


}