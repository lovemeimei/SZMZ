package com.szmz.entity;

/**
 * Created by bz on 2018/3/22.
 */

public class AppInfo implements IEntity {

    private String bxternalVersion;//	对外显示版本号
    private int internalVersion;//	对内展示版本
    private String description;//	升级内容
    private String updatetime;//	升级时间
    private String appurl;//	app文件路径

    public String getBxternalVersion() {
        return bxternalVersion;
    }

    public void setBxternalVersion(String bxternalVersion) {
        this.bxternalVersion = bxternalVersion;
    }

    public int getInternalVersion() {
        return internalVersion;
    }

    public void setInternalVersion(int internalVersion) {
        this.internalVersion = internalVersion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getAppurl() {
        return appurl;
    }

    public void setAppurl(String appurl) {
        this.appurl = appurl;
    }
}
