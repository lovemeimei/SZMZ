package com.szmz.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * @author qieyixuan
 * @created at 2016年04月24
 */
public class User implements IEntity {

    private String userName;
    private String pw;
    private String realName;
    private String sex;
    private String adderss;
    private String idCode;
    private String idJZ;
    private String idYZS;
    private String idHD;

    public String getIdJZ() {
        return idJZ;
    }

    public void setIdJZ(String idJZ) {
        this.idJZ = idJZ;
    }

    public String getIdYZS() {
        return idYZS;
    }

    public void setIdYZS(String idYZS) {
        this.idYZS = idYZS;
    }

    public String getIdHD() {
        return idHD;
    }

    public void setIdHD(String idHD) {
        this.idHD = idHD;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAdderss() {
        return adderss;
    }

    public void setAdderss(String adderss) {
        this.adderss = adderss;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
