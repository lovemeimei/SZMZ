package com.szmz.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by bz on 2017/10/23.
 */
@Table(name = "YwblDzdaSalvation")
public class YwblDzdaSalvation implements IEntity {
    @Column(name = "id", isId = true)
    private String id;//ID
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
