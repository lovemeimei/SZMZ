package com.szmz.entity;

/**
 * 家庭信息
 * Created by bz on 2017/10/23.
 */

public class YwblDzdaFamily implements IEntity {
    private String regionname;//	行政区域	string
    private String cityname;//	县(区、市)	string
    private String townname;//乡(街道镇)	string
    private String villagename;//	村居委会 社区	string
    private String salvationType;//	救助类别	string
    private String familySalvationType;//	家庭分类救助类别	string
    private String poorreason;//主要贫困原因	string
    private String name;//户主姓名	string
    private String idcard;//	身份证号	string
    private String applydate;//	申请日期	date

    public String getRegionname() {
        return regionname;
    }

    public void setRegionname(String regionname) {
        this.regionname = regionname;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getTownname() {
        return townname;
    }

    public void setTownname(String townname) {
        this.townname = townname;
    }

    public String getVillagename() {
        return villagename;
    }

    public void setVillagename(String villagename) {
        this.villagename = villagename;
    }

    public String getSalvationType() {
        return salvationType;
    }

    public void setSalvationType(String salvationType) {
        this.salvationType = salvationType;
    }

    public String getFamilySalvationType() {
        return familySalvationType;
    }

    public void setFamilySalvationType(String familySalvationType) {
        this.familySalvationType = familySalvationType;
    }

    public String getPoorreason() {
        return poorreason;
    }

    public void setPoorreason(String poorreason) {
        this.poorreason = poorreason;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getApplydate() {
        return applydate;
    }

    public void setApplydate(String applydate) {
        this.applydate = applydate;
    }
}
