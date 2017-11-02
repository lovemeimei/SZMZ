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
