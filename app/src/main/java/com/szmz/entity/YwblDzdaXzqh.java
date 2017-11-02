package com.szmz.entity;

/**
 * Created by bz on 2017/10/20.
 */

public class YwblDzdaXzqh implements IEntity {
    private String id;
    private String regioncode;//地区编码
    private String regionname;//地区名称
    private String regionlevel;//地区等级

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegioncode() {
        return regioncode;
    }

    public void setRegioncode(String regioncode) {
        this.regioncode = regioncode;
    }

    public String getRegionname() {
        return regionname;
    }

    public void setRegionname(String regionname) {
        this.regionname = regionname;
    }

    public String getRegionlevel() {
        return regionlevel;
    }

    public void setRegionlevel(String regionlevel) {
        this.regionlevel = regionlevel;
    }
}
