package com.szmz.ywbl.dzda;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.entity.YwblPerson;
import com.szmz.widget.MyLayoutView;

import butterknife.BindView;

/**
 * 家庭基本信息
 */
public class ActYwbl_dzda_jtjbxx extends ActBase {

    @BindView(R.id.xzqhView)
    MyLayoutView xzqhView;
    @BindView(R.id.xianView)
    MyLayoutView xianView;
    @BindView(R.id.xiangView)
    MyLayoutView xiangView;
    @BindView(R.id.cunView)
    MyLayoutView cunView;
    @BindView(R.id.jzlbView)
    MyLayoutView jzlbView;
    @BindView(R.id.jtfljzlbView)
    MyLayoutView jtfljzlbView;
    @BindView(R.id.zyzpyyView)
    MyLayoutView zyzpyyView;
    @BindView(R.id.hzxmView)
    MyLayoutView hzxmView;
    @BindView(R.id.sfzhView)
    MyLayoutView sfzhView;
    @BindView(R.id.sqrqView)
    MyLayoutView sqrqView;
    private YwblPerson person;

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("家庭基本信息");
        person = (YwblPerson) getIntent().getSerializableExtra("YwblPerson");
        xzqhView.doSetContent(person.getXzqh());
        xianView.doSetContent(person.getXian());
        xiangView.doSetContent(person.getXiang());
        cunView.doSetContent(person.getCounty());
        jzlbView.doSetContent(person.getTypeName());
        jtfljzlbView.doSetContent(person.getJtfljzlb());
        zyzpyyView.doSetContent(person.getZyzpyy());
        hzxmView.doSetContent(person.getHzxm());
        sfzhView.doSetContent(person.getSfzh());
        sqrqView.doSetContent(person.getTime());

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ywbl_dzda_jtjbxx;
    }


}
