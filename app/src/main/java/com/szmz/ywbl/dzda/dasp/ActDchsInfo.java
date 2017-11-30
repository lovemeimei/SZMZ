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

public class ActDchsInfo extends ActBase {


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
    @BindView(R.id.dchsqk_ed)
    EditText dchsqkEd;
    @BindView(R.id.dchsjl_ed)
    EditText dchsjlEd;
    @BindView(R.id.bz_ed)
    ClearEditText bzEd;
    @BindView(R.id.mygridview)
    GridViewInScrollView mygridview;
    @BindView(R.id.mainLayout)
    LinearLayout mainLayout;
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
        info = (YwblDzdaFamilyApproveInfo) getIntent().getSerializableExtra("YwblDzdaFamilyApproveInfo");
        setLeftVisible(true);
        setTitle("调查核实");
        sqrTv.setText(info.getHeadName());
        time.setText(info.getStreetCheckTime());
        dcrTv.setText(info.getStreetCheckUser());
        fzrTv.setText(info.getStreetCheckChargeUser());
        dchsqkEd.setText(info.getStreetCheckInfo());
        dchsjlEd.setText(info.getStreetCheckResult());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_dchs_info;
    }


}
