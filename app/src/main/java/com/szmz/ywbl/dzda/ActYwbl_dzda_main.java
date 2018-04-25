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
    private boolean isFromJZXX = false;
    private boolean isFromLocal = false;

    @OnClick({
            R.id.jtjbxxLayout, R.id.jtcyxxLayout, R.id.jtccxxLayout, R.id.jtfcxxLayout, R.id.syrsrLayout, R.id.jtzlxxLayout, R.id.gzryjqbaLayout, R.id.daspxxLayout
    })
    public void doClick(View v) {
        if (myFamily == null) {
            doToast("未获取到家庭详细信息!");
            return;
        }
        Intent intent;
        switch (v.getId()) {
            case R.id.jtjbxxLayout://家庭基本信息
                intent = new Intent(this, ActYwbl_dzda_jtjbxx.class);
                intent.putExtra("YwblPerson", myFamily);
                startActivity(intent);
                break;
            case R.id.jtcyxxLayout://家庭成员信息
                intent = new Intent(this, ActYwbl_dzda_jtcyxx.class);
                intent.putExtra("YwblPerson", myFamily);
                startActivity(intent);
                break;
            case R.id.jtccxxLayout://家庭财产信息
                intent = new Intent(this, ActYwbl_dzda_jtccxx.class);
                intent.putExtra("YwblPerson", myFamily);
                startActivity(intent);
                break;
            case R.id.jtfcxxLayout://家庭房产信息fc
                intent = new Intent(this, ActYwbl_dzda_jtfcxx.class);
                intent.putExtra("YwblPerson", myFamily);
                startActivity(intent);
                break;
            case R.id.syrsrLayout://赡养人信息
                intent = new Intent(this, ActYwbl_dzda_syrsrxx_List.class);
                intent.putExtra("YwblPerson", myFamily);
                startActivity(intent);
                break;
            case R.id.jtzlxxLayout://家庭资料信息
                intent = new Intent(this, ActYwbl_dzda_jtzlxx.class);
                intent.putExtra("YwblPerson", myFamily);
                startActivity(intent);
                break;
            case R.id.gzryjqbaLayout://工作人员近亲备案
                intent = new Intent(this, ActYwbl_dzda_gzryjqba.class);
                intent.putExtra("YwblPerson", myFamily);
                startActivity(intent);
                break;
            case R.id.daspxxLayout://档案审批信息
                intent = new Intent(this, ActYwbl_dzda_dasp_main.class);
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
        isFromLocal = getIntent().getBooleanExtra("isFromLocal", false);
        isFromJZXX = getIntent().getBooleanExtra("isFromJZXX", false);
        person = (YwblDzdaSalvation) getIntent().getSerializableExtra("YwblPerson");
        if (person != null) {
            if (isOnline) {
                if (!isFromLocal) {
                    if (isFromJZXX) {
                        doGetData(person.getId() + "");
                    } else {
                        doGetData(person.getFamilyId() + "");
                    }
                } else {
                    myFamily = GsonUtil.deser(person.getJsonStr(), YwblDzdaFamily.class);
                }

//                doGetData("f6a4a2883bef4411a6913140616c7c62");
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
        Call<JZ_YWBL_DZDA_Family> call;
        if (isFromJZXX) {
            call = App.getApiProxyJZ().getJZ_GetAllData(request);
        } else {
            call = App.getApiProxyJZ().getJZ_GetAllDataTemp(request);
        }

        ApiUtil<JZ_YWBL_DZDA_Family> apiUtil = new ApiUtil<>(this, call, new SimpleApiListener<JZ_YWBL_DZDA_Family>() {
            @Override
            public void doSuccess(JZ_YWBL_DZDA_Family result) {
                List<YwblDzdaFamily> family = result.Result;
                if (family != null && family.size() > 0) {
                    myFamily = family.get(0);
                } else {
                    doToast("未获取到详细信息!");
                }

            }

        }, true);

        apiUtil.excute();
    }


}
