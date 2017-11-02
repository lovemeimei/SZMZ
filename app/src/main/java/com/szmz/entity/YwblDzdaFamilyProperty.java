package com.szmz.entity;

/**
 * 家庭财产信息
 * Created by bz on 2017/10/23.
 */

public class YwblDzdaFamilyProperty implements IEntity {
    private String propertyType;//财产类型
    private String count;//数量
    private String value;//价值
    private String housetype;//	房屋性质
    private String housesize;//房屋面积

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getHousetype() {
        return housetype;
    }

    public void setHousetype(String housetype) {
        this.housetype = housetype;
    }

    public String getHousesize() {
        return housesize;
    }

    public void setHousesize(String housesize) {
        this.housesize = housesize;
    }
}
