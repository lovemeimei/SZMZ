package com.szmz.entity;

import java.util.List;

/**
 * Created by bz on 2017/9/21.
 */

public class YwblPerson implements IEntity {
    private int id;
    private String name;
    private String county;
    private String time;
    private String typeName;
    private String xzqh;
    private String xian;
    private String xiang;
    private String jtfljzlb;
    private String zyzpyy;
    private String hzxm;
    private String sfzh;
    private List<YwblJtcy> jtcyList;

    public List<YwblJtcy> getJtcyList() {
        return jtcyList;
    }

    public void setJtcyList(List<YwblJtcy> jtcyList) {
        this.jtcyList = jtcyList;
    }

    public String getXzqh() {
        return xzqh;
    }

    public void setXzqh(String xzqh) {
        this.xzqh = xzqh;
    }

    public String getXian() {
        return xian;
    }

    public void setXian(String xian) {
        this.xian = xian;
    }

    public String getXiang() {
        return xiang;
    }

    public void setXiang(String xiang) {
        this.xiang = xiang;
    }

    public String getJtfljzlb() {
        return jtfljzlb;
    }

    public void setJtfljzlb(String jtfljzlb) {
        this.jtfljzlb = jtfljzlb;
    }

    public String getZyzpyy() {
        return zyzpyy;
    }

    public void setZyzpyy(String zyzpyy) {
        this.zyzpyy = zyzpyy;
    }

    public String getHzxm() {
        return hzxm;
    }

    public void setHzxm(String hzxm) {
        this.hzxm = hzxm;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

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

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
