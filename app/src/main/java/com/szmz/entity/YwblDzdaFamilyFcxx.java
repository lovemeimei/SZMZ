package com.szmz.entity;

/**
 * 家庭房产信息
 * Created by bz on 2017/10/23.
 */

public class YwblDzdaFamilyFcxx implements IEntity {
    private String address;//	房屋地址
    private String housesize;//房屋面积
    private String ecount;//房屋套数
    private String propertyName;//房屋性质
    private String type;//房屋类型

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHousesize() {
        return housesize;
    }

    public void setHousesize(String housesize) {
        this.housesize = housesize;
    }

    public String getEcount() {
        return ecount;
    }

    public void setEcount(String ecount) {
        this.ecount = ecount;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
