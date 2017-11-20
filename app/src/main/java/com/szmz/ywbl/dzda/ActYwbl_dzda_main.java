package com.szmz.ywbl.dzda;

import android.content.Intent;
import android.view.View;

import com.szmz.ActBase;
import com.szmz.App;
import com.szmz.R;
import com.szmz.entity.YwblDzdaFamily;
import com.szmz.entity.YwblDzdaSalvation;
import com.szmz.entity.request.JZ_YWBL_DZDA_FAMILY_RE;
import com.szmz.entity.response.JZ_YWBL_DZDA_Family;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.GsonUtil;

import java.util.List;

import butterknife.OnClick;
import retrofit2.Call;

/**
 * 电子档案操作模块页面
 */
public class ActYwbl_dzda_main extends ActBase {

    private YwblDzdaSalvation person;
    private YwblDzdaFamily myFamily;

    @OnClick({
            R.id.jtjbxxLayout, R.id.jtcyxxLayout, R.id.jtccxxLayout, R.id.jtsrxxLayout, R.id.syrsrLayout, R.id.jtzlxxLayout
    })
    public void doClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.jtjbxxLayout:
                intent = new Intent(this, ActYwbl_dzda_jtjbxx.class);
                intent.putExtra("YwblPerson", myFamily);
                startActivity(intent);
                break;
            case R.id.jtcyxxLayout:
                intent = new Intent(this, ActYwbl_dzda_jtcyxx.class);
                intent.putExtra("YwblPerson", myFamily);
                startActivity(intent);
                break;
            case R.id.jtccxxLayout:
                intent = new Intent(this, ActYwbl_dzda_jtccxx.class);
                intent.putExtra("YwblPerson", myFamily);
                startActivity(intent);
                break;
            case R.id.jtsrxxLayout:
                intent = new Intent(this, ActYwbl_dzda_jtsrxx.class);
                intent.putExtra("YwblPerson", myFamily);
                startActivity(intent);
                break;
            case R.id.syrsrLayout:
                intent = new Intent(this, ActYwbl_dzda_syrsrxx.class);
                intent.putExtra("YwblPerson", myFamily);
                startActivity(intent);
                break;
            case R.id.jtzlxxLayout:
                intent = new Intent(this, ActYwbl_dzda_jtzlxx.class);
                intent.putExtra("YwblPerson", myFamily);
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
        if (person != null) {
            if (isOnline) {
                doGetData(person.getId() + "");
            } else {
                myFamily = GsonUtil.deser(person.getJsonStr(), YwblDzdaFamily.class);
            }
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ywbl_dzda_main;
    }


    private void doGetData(String id) {
        JZ_YWBL_DZDA_FAMILY_RE request = new JZ_YWBL_DZDA_FAMILY_RE(id);
        Call<JZ_YWBL_DZDA_Family> call = App.getApiProxyJZ().getJZ_GetAllData(request);
        ApiUtil<JZ_YWBL_DZDA_Family> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<JZ_YWBL_DZDA_Family>() {
            @Override
            public void doSuccess(JZ_YWBL_DZDA_Family result) {
                List<YwblDzdaFamily> family = result.Result;
                if (family != null && family.size() > 0) {
                    myFamily = family.get(0);
                }

            }

        }, true);

        apiUtil.excute();
    }


}
