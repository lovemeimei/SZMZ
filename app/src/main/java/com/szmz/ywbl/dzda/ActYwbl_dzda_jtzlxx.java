package com.szmz.ywbl.dzda;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.entity.YwblPerson;
import com.szmz.widget.MyLayoutView;

import butterknife.BindView;

/**
 * 家庭资料信息
 */
public class ActYwbl_dzda_jtzlxx extends ActBase {


    @BindView(R.id.zllbView)
    MyLayoutView zllbView;
    @BindView(R.id.wjView)
    MyLayoutView wjView;
    @BindView(R.id.scjdView)
    MyLayoutView scjdView;
    YwblPerson person;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ywbl_daza_jtzlxx;
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("家庭资料信息");
        person = (YwblPerson) getIntent().getSerializableExtra("YwblPerson");
        zllbView.doSetContent(person.getZllb());
        wjView.doSetContent(person.getWj());
        scjdView.doSetContent(person.getScjd());
    }

}
