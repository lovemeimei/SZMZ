package com.szmz.ywbl.dzda;

import android.content.Intent;
import android.view.View;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.entity.YwblDzdaSalvation;

import butterknife.OnClick;

/**
 * 电子档案操作模块页面
 */
public class ActYwbl_dzda_main extends ActBase {

    private YwblDzdaSalvation person;

    @OnClick({
            R.id.jtjbxxLayout, R.id.jtcyxxLayout, R.id.jtccxxLayout, R.id.jtsrxxLayout, R.id.syrsrLayout, R.id.jtzlxxLayout
    })
    public void doClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.jtjbxxLayout:
                intent = new Intent(this, ActYwbl_dzda_jtjbxx.class);
                intent.putExtra("YwblPerson", person);
                startActivity(intent);
                break;
            case R.id.jtcyxxLayout:
                intent = new Intent(this, ActYwbl_dzda_jtcyxx.class);
                intent.putExtra("YwblPerson", person);
                startActivity(intent);
                break;
            case R.id.jtccxxLayout:
                intent = new Intent(this, ActYwbl_dzda_jtccxx.class);
                intent.putExtra("YwblPerson", person);
                startActivity(intent);
                break;
            case R.id.jtsrxxLayout:
                intent = new Intent(this, ActYwbl_dzda_jtsrxx.class);
                intent.putExtra("YwblPerson", person);
                startActivity(intent);
                break;
            case R.id.syrsrLayout:
                intent = new Intent(this, ActYwbl_dzda_syrsrxx.class);
                intent.putExtra("YwblPerson", person);
                startActivity(intent);
                break;
            case R.id.jtzlxxLayout:
                intent = new Intent(this, ActYwbl_dzda_jtzlxx.class);
                intent.putExtra("YwblPerson", person);
                startActivity(intent);
                break;

        }
    }

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("电子档案");
        person = (YwblDzdaSalvation) getIntent().getSerializableExtra("YwblPerson");

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ywbl_dzda_main;
    }


    private void doGetData(){

    }


}
