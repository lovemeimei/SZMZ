package com.szmz.ywbl.dzda;

import android.widget.TextView;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.entity.YwblPerson;
import com.szmz.widget.MyLayoutView;

import butterknife.BindView;

/**
 * 赡养人收入信息
 */
public class ActYwbl_dzda_syrsrxx extends ActBase {


    @BindView(R.id.nameView)
    MyLayoutView nameView;
    @BindView(R.id.jtrkView)
    MyLayoutView jtrkView;
    @BindView(R.id.sfzhView)
    MyLayoutView sfzhView;
    @BindView(R.id.jtsrView)
    MyLayoutView jtsrView;
    @BindView(R.id.yhzgxView)
    MyLayoutView yhzgxView;
    @BindView(R.id.syrsView)
    MyLayoutView syrsView;
    @BindView(R.id.syfyView)
    MyLayoutView syfyView;
    @BindView(R.id.bzTv)
    TextView bzTv;
    private YwblPerson person;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ywbl_dzda_syrsrxx;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("赡（扶、抚）养人收入信息");
        person = (YwblPerson) getIntent().getSerializableExtra("YwblPerson");

        nameView.doSetContent(person.getName());
        jtrkView.doSetContent(person.getJtrk());
        sfzhView.doSetContent(person.getSfzh());
        jtsrView.doSetContent(person.getSrje());
        yhzgxView.doSetContent(person.getYhzgx());
        syrsView.doSetContent(person.getBsyrs());
        syfyView.doSetContent(person.getSyfzc());
        bzTv.setText(person.getSybz());
    }
}