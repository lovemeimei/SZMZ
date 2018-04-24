package com.szmz.entity;

/**
 * Created by xuan on 2018/3/27.
 */

public class UserSQR_XJ implements IEntity{

    private String Idcard;
    private String Address;
    private String RegTime;
    private String RealName;
    private String Sex;

    public String getIdcard() {
        return Idcard;
    }

    public void setIdcard(String idcard) {
        Idcard = idcard;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getRegTime() {
        return RegTime;
    }

    public void setRegTime(String regTime) {
        RegTime = regTime;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }
}
