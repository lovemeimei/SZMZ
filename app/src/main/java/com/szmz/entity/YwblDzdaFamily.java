package com.szmz.entity;

import java.util.List;

/**
 * 家庭信息全部数据
 * Created by bz on 2017/10/23.
 */

public class YwblDzdaFamily implements IEntity {
    /**
     * 家庭基本信息数据
     */
    private String id;
    private String regionname;//行政区划
    private String cityname;//	县(区、市)	string
    private String townname;//乡(街道镇)	string
    private String villagename;//	村居委会 社区	string
    private String salvationType;//	救助类别	string
    private String familySalvationType;//	家庭分类救助类别
    private String poorreason;//主要贫困原因	string
    private String name;//户主姓名
    private String idcard;//	身份证号	string
    private String applydate;//	申请日期	date

    /**
     * 家庭财产信息
     */
    private List<YwblDzdaFamilyProperty> FamilyPropertyInfo;

    /**
     * 家庭资料
     */
    private List<YwblDzdaFamilyMaterial> FamilyMaterialInfo;

    /**
     * 赡养人收入
     */
    private List<YwblDzdaSupportIncome> FamilySupportInfo;

    /**
     * 家庭成员信息
     */
    private List<YwblDzdaFamilyMember> FamilyMemberInfo;


    /**
     * 工作人员近亲备案
     */
    private List<YwblDzdaFamilyShipPer> FamilyShipPerInfo;


    /**
     * 家庭房产信息
     */

    private List<YwblDzdaFamilyFcxx> FamilyFcxx;
    /**
     * 家庭收入信息
     */
    private List<YwblDzdaFamilyIncome> FamilyIncomeInfo;

    /**
     * 审批信息
     *
     * @return
     */
    private List<YwblDzdaFamilyApproveInfo> FamilyApproveInfo;


    public List<YwblDzdaFamilyMember> getFamilyMemberInfo() {
        return FamilyMemberInfo;
    }

    public void setFamilyMemberInfo(List<YwblDzdaFamilyMember> familyMemberInfo) {
        FamilyMemberInfo = familyMemberInfo;
    }

    public List<YwblDzdaFamilyProperty> getFamilyPropertyInfo() {
        return FamilyPropertyInfo;
    }

    public void setFamilyPropertyInfo(List<YwblDzdaFamilyProperty> familyPropertyInfo) {
        FamilyPropertyInfo = familyPropertyInfo;
    }

    public List<YwblDzdaFamilyIncome> getFamilyIncomeInfo() {
        return FamilyIncomeInfo;
    }

    public void setFamilyIncomeInfo(List<YwblDzdaFamilyIncome> familyIncomeInfo) {
        FamilyIncomeInfo = familyIncomeInfo;
    }

    public List<YwblDzdaSupportIncome> getFamilySupportInfo() {
        return FamilySupportInfo;
    }

    public void setFamilySupportInfo(List<YwblDzdaSupportIncome> familySupportInfo) {
        FamilySupportInfo = familySupportInfo;
    }

    public List<YwblDzdaFamilyMaterial> getFamilyMaterialInfo() {
        return FamilyMaterialInfo;
    }

    public void setFamilyMaterialInfo(List<YwblDzdaFamilyMaterial> familyMaterialInfo) {
        FamilyMaterialInfo = familyMaterialInfo;
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<YwblDzdaFamilyShipPer> getFamilyShipPerInfo() {
        return FamilyShipPerInfo;
    }

    public void setFamilyShipPerInfo(List<YwblDzdaFamilyShipPer> familyShipPerInfo) {
        FamilyShipPerInfo = familyShipPerInfo;
    }

    public List<YwblDzdaFamilyFcxx> getFamilyFcxx() {
        return FamilyFcxx;
    }

    public void setFamilyFcxx(List<YwblDzdaFamilyFcxx> familyFcxx) {
        FamilyFcxx = familyFcxx;
    }

    public List<YwblDzdaFamilyApproveInfo> getFamilyApproveInfo() {
        return FamilyApproveInfo;
    }

    public void setFamilyApproveInfo(List<YwblDzdaFamilyApproveInfo> familyApproveInfo) {
        FamilyApproveInfo = familyApproveInfo;
    }
}
