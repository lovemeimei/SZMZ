package com.szmz.entity;

import java.util.List;

/**
 * 家庭成员信息
 * Created by bz on 2017/10/23.
 */

public class YwblDzdaFamilyMember implements IEntity {

    private String id;//id
    private String pidcard;//身份证号
    private String pname;//姓名
    private String psex;//性别
    private String pbirthday;//	出生日期
    private String prelationship;//	与户主关系
    private String pissafe;//是否是保障对象
    private String personSalvationType;//	人员分类救助类型
    private String pincome;//月收入
    private String pnation;//民族
    private String pismarry;//婚姻状况
    private List<YwblDzdaMemberDeformityInfo> PerDeformityInfo;//成员残疾信息
    private List<YwblDzdaMemberIncomeInfo> PerIncomeInfo;//成员收入信息
    private List<YwblDzdaMemberInsureInfo> PerInsureInfo;//成员参保信息
    private List<YwblDzdaMemberSalaryInfo> PerSalaryInfo;//成员待遇信息

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPidcard() {
        return pidcard;
    }

    public void setPidcard(String pidcard) {
        this.pidcard = pidcard;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPsex() {
        return psex;
    }

    public void setPsex(String psex) {
        this.psex = psex;
    }

    public String getPbirthday() {
        return pbirthday;
    }

    public void setPbirthday(String pbirthday) {
        this.pbirthday = pbirthday;
    }

    public String getPrelationship() {
        return prelationship;
    }

    public void setPrelationship(String prelationship) {
        this.prelationship = prelationship;
    }

    public String getPissafe() {
        return pissafe;
    }

    public void setPissafe(String pissafe) {
        this.pissafe = pissafe;
    }

    public String getPersonSalvationType() {
        return personSalvationType;
    }

    public void setPersonSalvationType(String personSalvationType) {
        this.personSalvationType = personSalvationType;
    }

    public String getPincome() {
        return pincome;
    }

    public void setPincome(String pincome) {
        this.pincome = pincome;
    }

    public String getPnation() {
        return pnation;
    }

    public void setPnation(String pnation) {
        this.pnation = pnation;
    }

    public String getPismarry() {
        return pismarry;
    }

    public void setPismarry(String pismarry) {
        this.pismarry = pismarry;
    }

    public List<YwblDzdaMemberDeformityInfo> getPerDeformityInfo() {
        return PerDeformityInfo;
    }

    public void setPerDeformityInfo(List<YwblDzdaMemberDeformityInfo> perDeformityInfo) {
        PerDeformityInfo = perDeformityInfo;
    }

    public List<YwblDzdaMemberIncomeInfo> getPerIncomeInfo() {
        return PerIncomeInfo;
    }

    public void setPerIncomeInfo(List<YwblDzdaMemberIncomeInfo> perIncomeInfo) {
        PerIncomeInfo = perIncomeInfo;
    }

    public List<YwblDzdaMemberInsureInfo> getPerInsureInfo() {
        return PerInsureInfo;
    }

    public void setPerInsureInfo(List<YwblDzdaMemberInsureInfo> perInsureInfo) {
        PerInsureInfo = perInsureInfo;
    }

    public List<YwblDzdaMemberSalaryInfo> getPerSalaryInfo() {
        return PerSalaryInfo;
    }

    public void setPerSalaryInfo(List<YwblDzdaMemberSalaryInfo> perSalaryInfo) {
        PerSalaryInfo = perSalaryInfo;
    }
}
