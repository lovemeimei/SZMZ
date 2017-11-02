package com.szmz.entity;

/**
 * Created by bz on 2017/10/23.
 */

public class YwblDzdaSalvation implements IEntity {
    private int id;//ID
    private String name;//姓名
    private String address;//地址
    private String salvationType;//救助类型

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSalvationType() {
        return salvationType;
    }

    public void setSalvationType(String salvationType) {
        this.salvationType = salvationType;
    }
}
