package com.szmz.ywbl.dzda;

import android.widget.TextView;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.entity.YwblPerson;
import com.szmz.widget.MyLayoutView;

import butterknife.BindView;

/**
 * 家庭收入信息
 */
public class ActYwbl_dzda_jtsrxx extends ActBase {


    @BindView(R.id.srlxView)
    MyLayoutView srlxView;
    @BindView(R.id.srxmView)
    MyLayoutView srxmView;
    @BindView(R.id.srjeView)
    MyLayoutView srjeView;
    @BindView(R.id.slhmjView)
    MyLayoutView slhmjView;
    @BindView(R.id.jldwView)
    MyLayoutView jldwView;
    @BindView(R.id.bzTv)
    TextView bzTv;
    private YwblPerson person;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ywbl_dzda_jtsrxx;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("家庭收入信息");
        person = (YwblPerson) getIntent().getSerializableExtra("YwblPerson");
        srlxView.doSetContent(person.getSrlx());
        srxmView.doSetContent(person.getSrxm());
        srjeView.doSetContent(person.getSrje());
        slhmjView.doSetContent(person.getSlhmj());
        jldwView.doSetContent(person.getJldw());
        bzTv.setText(person.getSrbz());

    }
}
