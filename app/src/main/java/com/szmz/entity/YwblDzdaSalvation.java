package com.szmz.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by bz on 2017/10/23.
 */
@Table(name = "YwblDzdaSalvation")
public class YwblDzdaSalvation implements IEntity {
    private String id;
    @Column(name = "familyId", isId = true)
    private String familyId;//ID
    @Column(name = "jsonStr")
    private String jsonStr;
    @Column(name = "disName")
    private String disName;//地区名称
    @Column(name = "name")
    private String name;//姓名
    @Column(name = "address")
    private String address;//地址
    @Column(name = "salvationType")
    private String salvationType;//救助类型
    @Column(name = "famSalvationType")
    private String famSalvationType;//一档
    @Column(name = "spId")
    private String spId;//审批ID
    @Column(name = "type")
    private int type;

    private boolean isSave = false;

    public boolean isSave() {
        return isSave;
    }

    public void setSave(boolean save) {
        isSave = save;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public YwblDzdaSalvation() {

    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    public String getDisName() {
        return disName;
    }

    public void setDisName(String disName) {
        this.disName = disName;
    }

    public String getFamSalvationType() {
        return famSalvationType;
    }

    public void setFamSalvationType(String famSalvationType) {
        this.famSalvationType = famSalvationType;
    }

    public String getFamilyId() {
        return familyId;
    }

    public void setFamilyId(String familyId) {
        this.familyId = familyId;
    }

    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSalvationType() {
        return salvationType;
    }

    public void setSalvationType(String salvationType) {
        this.salvationType = salvationType;
    }
}
