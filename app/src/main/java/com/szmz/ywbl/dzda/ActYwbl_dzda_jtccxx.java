package com.szmz.ywbl.dzda;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.entity.YwblPerson;
import com.szmz.widget.MyLayoutView;

import butterknife.BindView;

/**
 * 家庭财产信息
 */
public class ActYwbl_dzda_jtccxx extends ActBase {


    @BindView(R.id.cdView)
    MyLayoutView cdView;
    @BindView(R.id.xyjView)
    MyLayoutView xyjView;
    @BindView(R.id.ktView)
    MyLayoutView ktView;
    @BindView(R.id.dnView)
    MyLayoutView dnView;
    @BindView(R.id.bxView)
    MyLayoutView bxView;
    @BindView(R.id.qtView)
    MyLayoutView qtView;
    @BindView(R.id.zfxzView)
    MyLayoutView zfxzView;
    @BindView(R.id.zfmjView)
    MyLayoutView zfmjView;
    private YwblPerson person;

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("家庭财产信息");
        person = (YwblPerson) getIntent().getSerializableExtra("YwblPerson");
        cdView.doSetContent(person.getCd());
        xyjView.doSetContent(person.getXyj());
        ktView.doSetContent(person.getKt());
        dnView.doSetContent(person.getDn());
        bxView.doSetContent(person.getBx());
        qtView.doSetContent(person.getQt());
        zfmjView.doSetContent(person.getZfmj());
        zfxzView.doSetContent(person.getZfxz());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ywbl_jtccxx;
    }


}
