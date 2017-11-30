package com.szmz.entity;

/**
 * 家庭财产信息
 * Created by bz on 2017/10/23.
 */

public class YwblDzdaFamilyProperty implements IEntity {
    private String propertyType;//财产类型
    private String count;//数量
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

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

}
