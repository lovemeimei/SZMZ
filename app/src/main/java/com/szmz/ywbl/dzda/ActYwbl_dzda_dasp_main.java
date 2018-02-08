package com.szmz.ywbl.dzda;

import android.content.Intent;
import android.view.View;

import com.szmz.ActBase;
import com.szmz.R;
import com.szmz.entity.MyNewPhoto;
import com.szmz.entity.YwblDzdaFamily;
import com.szmz.entity.YwblDzdaFamilyApproveInfo;
import com.szmz.entity.YwblDzdaFamilyMaterial;
import com.szmz.ywbl.dzda.dasp.ActDchsInfo;
import com.szmz.ywbl.dzda.dasp.ActMzpyInfo;
import com.szmz.ywbl.dzda.dasp.ActRhccInfo;
import com.szmz.ywbl.dzda.dasp.ActShgsInfo;
import com.szmz.ywbl.dzda.dasp.ActSpgsInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

public class ActYwbl_dzda_dasp_main extends ActBase {
    private YwblDzdaFamily person;
    private YwblDzdaFamilyApproveInfo info;
    private List<YwblDzdaFamilyMaterial> listMaterial;
    private List<MyNewPhoto> listPhoto = new ArrayList();

    @Override
    protected void initUI() {
        super.initUI();
        setLeftVisible(true);
        setTitle("档案审批信息");
        person = (YwblDzdaFamily) getIntent().getSerializableExtra("YwblPerson");
        if (person.getFamilyApproveInfo() != null && person.getFamilyApproveInfo().size() > 0) {
            info = person.getFamilyApproveInfo().get(0);
        }
        listMaterial = person.getFamilyMaterialInfo();

    }

    private List<MyNewPhoto> getPhotoByCode(String code) {
        if (code == null || code.equals("")) {
            return null;
        }
        if (listMaterial != null && listMaterial.size() > 0) {
            for (int i = 0; i < listMaterial.size(); i++) {
                if (code.equals(listMaterial.get(i).getNodeCode())) {
                    listPhoto.add(new MyNewPhoto(listMaterial.get(i).getFileurl()));
                }
            }

        }
        if (listPhoto != null && listPhoto.size() > 0) {
            return listPhoto;
        } else {
            return null;
        }
    }

    @OnClick({
            R.id.dchsLayout, R.id.mzpyLayout, R.id.shgsLayout, R.id.rhccLayout, R.id.spgshiLayout
    })

    public void doClick(View v) {
        if (info == null) {
            doToast("暂无审批数据!");
            return;
        }
        Intent intent;
        List<MyNewPhoto> list;
        switch (v.getId()) {
            case R.id.dchsLayout:
                intent = new Intent(ActYwbl_dzda_dasp_main.this, ActDchsInfo.class);
                intent.putExtra("YwblDzdaFamilyApproveInfo", info);
                list = getPhotoByCode("20203028");
                if (list != null) {
                    intent.putExtra("ListPhoto", (Serializable) list);
                }
                startActivity(intent);
                break;
            case R.id.mzpyLayout:
                intent = new Intent(ActYwbl_dzda_dasp_main.this, ActMzpyInfo.class);
                intent.putExtra("YwblDzdaFamilyApproveInfo", info);
                list = getPhotoByCode("20203029");
                if (list != null) {
                    intent.putExtra("ListPhoto", (Serializable) list);
                }
                startActivity(intent);
                break;
            case R.id.shgsLayout:
                intent = new Intent(ActYwbl_dzda_dasp_main.this, ActShgsInfo.class);
                intent.putExtra("YwblDzdaFamilyApproveInfo", info);
                list = getPhotoByCode("20203030");
                if (list != null) {
                    intent.putExtra("ListPhoto", (Serializable) list);
                }
                startActivity(intent);
                break;
            case R.id.rhccLayout:
                intent = new Intent(ActYwbl_dzda_dasp_main.this, ActRhccInfo.class);
                intent.putExtra("YwblDzdaFamilyApproveInfo", info);
                list = getPhotoByCode("20203031");
                if (list != null) {
                    intent.putExtra("ListPhoto", (Serializable) list);
                }
                startActivity(intent);
                break;
            case R.id.spgshiLayout:
                intent = new Intent(ActYwbl_dzda_dasp_main.this, ActSpgsInfo.class);
                intent.putExtra("YwblDzdaFamilyApproveInfo", info);
                list = getPhotoByCode("20203032");
                if (list != null) {
                    intent.putExtra("ListPhoto", (Serializable) list);
                }
                startActivity(intent);
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ywbl_dzda_dasp_main;
    }
}
