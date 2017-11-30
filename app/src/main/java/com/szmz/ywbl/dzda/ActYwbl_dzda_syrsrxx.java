package com.szmz.ywbl.dzda;

import android.widget.TextView;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.entity.YwblDzdaSupportIncome;
import com.szmz.widget.MyLayoutView;

import butterknife.BindView;

/**
 * 赡养人收入信息
 */
public class ActYwbl_dzda_syrsrxx extends ActBase {


    @BindView(R.id.nameView)
    MyLayoutView nameView;
    @BindView(R.id.xbView)
    MyLayoutView xbView;
    @BindView(R.id.sfzhView)
    MyLayoutView sfzhView;
    @BindView(R.id.yhzgxView)
    MyLayoutView yhzgxView;
    @BindView(R.id.yfdsyfyView)
    MyLayoutView yfdsyfyView;
    @BindView(R.id.nfdsyfyView)
    MyLayoutView nfdsyfyView;
    @BindView(R.id.wsffynlyyView)
    MyLayoutView wsffynlyyView;
    @BindView(R.id.bzTv)
    TextView bzTv;
    private YwblDzdaSupportIncome person;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ywbl_dzda_syrsrxx;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("赡（扶、抚）养人收入信息");
        person = (YwblDzdaSupportIncome) getIntent().getSerializableExtra("YwblDzdaSupportIncome");

        nameView.doSetContent(person.getSname());
        xbView.doSetContent(person.getSsex());
        sfzhView.doSetContent(person.getSidcard());
        yhzgxView.doSetContent(person.getRelationship());
        yfdsyfyView.doSetContent(person.getMonthAimoney());
        nfdsyfyView.doSetContent(person.getYearAlimony());
        wsffynlyyView.doSetContent(person.getNeedsupportReasonDictId());
        bzTv.setText(person.getRemark());
    }

    
}
