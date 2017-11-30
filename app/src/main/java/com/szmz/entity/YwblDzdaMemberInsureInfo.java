package com.szmz.entity;

/**
 * Created by bz on 2017/11/23.
 */

public class YwblDzdaMemberInsureInfo implements IEntity {
    private String insureId;//成员参保Id
    private String insureCategoryName;//	成员参保类型
    private String insureMoney;//	成员参保金额
    private String insureTime;//成员参保时间

    public String getInsureId() {
        return insureId;
    }

    public void setInsureId(String insureId) {
        this.insureId = insureId;
    }

    public String getInsureCategoryName() {
        return insureCategoryName;
    }

    public void setInsureCategoryName(String insureCategoryName) {
        this.insureCategoryName = insureCategoryName;
    }

    public String getInsureMoney() {
        return insureMoney;
    }

    public void setInsureMoney(String insureMoney) {
        this.insureMoney = insureMoney;
    }

    public String getInsureTime() {
        return insureTime;
    }

    public void setInsureTime(String insureTime) {
        this.insureTime = insureTime;
    }
}
