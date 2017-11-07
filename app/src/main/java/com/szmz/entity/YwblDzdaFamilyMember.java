package com.szmz.entity;

/**
 * 家庭成员信息
 * Created by bz on 2017/10/23.
 */

public class YwblDzdaFamilyMember implements IEntity {

    private String id;//id
    private String idcard;//身份证号
    private String name;//姓名
    private String sex;//性别
    private String birthday;//	出生日期
    private String relationship;//	与户主关系
    private String issafe;//是否是保障对象
    private String personSalvationType;//	人员分类救助类型
    private String income;//月收入
    private String nation;//民族
    private String ismarry;//婚姻状况


    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getIssafe() {
        return issafe;
    }

    public void setIssafe(String issafe) {
        this.issafe = issafe;
    }

    public String getPersonSalvationType() {
        return personSalvationType;
    }

    public void setPersonSalvationType(String personSalvationType) {
        this.personSalvationType = personSalvationType;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getIsmarry() {
        return ismarry;
    }

    public void setIsmarry(String ismarry) {
        this.ismarry = ismarry;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }
}
