package com.szmz.ywbl.dzda;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.entity.YwblDzdaFamily;
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
    private YwblDzdaFamily person;

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("家庭基本信息");
        person = (YwblDzdaFamily) getIntent().getSerializableExtra("YwblPerson");
        xzqhView.doSetContent(person.getRegionname());
        xianView.doSetContent(person.getCityname());
        xiangView.doSetContent(person.getTownname());
        cunView.doSetContent(person.getVillagename());
        jzlbView.doSetContent(person.getSalvationType());
        jtfljzlbView.doSetContent(person.getFamilySalvationType());
        zyzpyyView.doSetContent(person.getPoorreason());
        hzxmView.doSetContent(person.getName());
        sfzhView.doSetContent(person.getIdcard());
        sqrqView.doSetContent(person.getApplydate());

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ywbl_dzda_jtjbxx;
    }


}
