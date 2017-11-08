package com.szmz.ywbl.dzda;

import android.view.View;

import com.szmz.R;
import com.szmz.entity.YwblDzdaFamilyIncome;
import com.szmz.entity.YwblPerson;
import com.szmz.ywbl.ActBaseList;

/**
 * 家庭收入信息
 */
public class ActYwbl_dzda_jtsrxx extends ActBaseList<YwblDzdaFamilyIncome> {


    private YwblPerson person;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ywbl_dzda_jtsrxx;
    }

    @Override
    protected void doRefreshView(int p, YwblDzdaFamilyIncome item, View view) {

    }

    @Override
    protected int getListItemID() {
        return 0;
    }

    @Override
    protected void doMore(boolean isMore) {

    }

    @Override
    public void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("家庭收入信息");
        person = (YwblPerson) getIntent().getSerializableExtra("YwblPerson");


    }
}
