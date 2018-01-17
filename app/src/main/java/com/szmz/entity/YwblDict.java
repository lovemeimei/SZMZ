package com.szmz.entity;

/**
 * 业务办理模块中的字典数据实体
 * Created by bz on 2018/1/10.
 */

public class YwblDict implements IEntity {
    private String code;
    private String name;

    public YwblDict(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
