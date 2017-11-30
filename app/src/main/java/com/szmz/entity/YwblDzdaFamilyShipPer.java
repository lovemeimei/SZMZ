package com.szmz.entity;

/**
 * 工作人员近亲备案
 * Created by bz on 2017/11/23.
 */

public class YwblDzdaFamilyShipPer implements IEntity {
    private String id;
    private String memberName;//工作人员亲属姓名
    private String memberIdcard;//工作人员亲属身份证号
    private String relation;//与工作人员关系

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberIdcard() {
        return memberIdcard;
    }

    public void setMemberIdcard(String memberIdcard) {
        this.memberIdcard = memberIdcard;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
