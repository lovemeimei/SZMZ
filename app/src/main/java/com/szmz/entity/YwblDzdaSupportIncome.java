package com.szmz.entity;

/**
 * 抚养人列表
 * Created by bz on 2017/10/23.
 */

public class YwblDzdaSupportIncome implements IEntity {
    private String id;//id
    private String sname;//姓名
    private String ssex;//性别
    private String sidcard;//身份证号
    private String relationship;//与户主关系
    private String monthAimoney;//月负担赡养费用
    private String yearAlimony;//年负担赡养费用
    private String needsupportReasonDictId;//无赡扶抚养能力原因
    private String remark;//赡养备注备注


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }

    public String getSidcard() {
        return sidcard;
    }

    public void setSidcard(String sidcard) {
        this.sidcard = sidcard;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getMonthAimoney() {
        return monthAimoney;
    }

    public void setMonthAimoney(String monthAimoney) {
        this.monthAimoney = monthAimoney;
    }

    public String getYearAlimony() {
        return yearAlimony;
    }

    public void setYearAlimony(String yearAlimony) {
        this.yearAlimony = yearAlimony;
    }

    public String getNeedsupportReasonDictId() {
        return needsupportReasonDictId;
    }

    public void setNeedsupportReasonDictId(String needsupportReasonDictId) {
        this.needsupportReasonDictId = needsupportReasonDictId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
