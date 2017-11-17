package com.szmz.entity;

/**
 * 抚养人列表
 * Created by bz on 2017/10/23.
 */

public class YwblDzdaSupportIncome implements IEntity {
    private String id;//id
    private String name;//姓名
    private String sex;//性别
    private String idcard;//身份证号
    private String population;//	家庭人口
    private String totalincome;//月总收入
    private String relationship;//与户主关系
    private String supportnum;//被赡养人数
    private String output;//赡养费支出（元）
    private String remark;//备注

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getTotalincome() {
        return totalincome;
    }

    public void setTotalincome(String totalincome) {
        this.totalincome = totalincome;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getSupportnum() {
        return supportnum;
    }

    public void setSupportnum(String supportnum) {
        this.supportnum = supportnum;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
