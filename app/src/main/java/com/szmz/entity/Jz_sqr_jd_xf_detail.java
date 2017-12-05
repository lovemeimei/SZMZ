package com.szmz.entity;

/**
 * Created by bz on 2017/12/4.
 */

public class Jz_sqr_jd_xf_detail implements IEntity {
    private String id;//id
    private String visitor;//	信访人
    private String idcard;//	身份证号
    private String phone;//	联系方式
    private String address;//	地址
    private String vdate;//	信访时间
    private String vmode;//信访方式
    private String vcause;//信访原因
    private String bstype;//所属业务类型
    private String hdate;//	来访办理时间
    private String handleInfo;//	办理情况
    private String hstatus;//办理状态
    private String tdate;//转办时间
    private String tinfo;//转办说明
    private String tstatus;//转办状态

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVisitor() {
        return visitor;
    }

    public void setVisitor(String visitor) {
        this.visitor = visitor;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVdate() {
        return vdate;
    }

    public void setVdate(String vdate) {
        this.vdate = vdate;
    }

    public String getVmode() {
        return vmode;
    }

    public void setVmode(String vmode) {
        this.vmode = vmode;
    }

    public String getVcause() {
        return vcause;
    }

    public void setVcause(String vcause) {
        this.vcause = vcause;
    }

    public String getBstype() {
        return bstype;
    }

    public void setBstype(String bstype) {
        this.bstype = bstype;
    }

    public String getHdate() {
        return hdate;
    }

    public void setHdate(String hdate) {
        this.hdate = hdate;
    }

    public String getHandleInfo() {
        return handleInfo;
    }

    public void setHandleInfo(String handleInfo) {
        this.handleInfo = handleInfo;
    }

    public String getHstatus() {
        return hstatus;
    }

    public void setHstatus(String hstatus) {
        this.hstatus = hstatus;
    }

    public String getTdate() {
        return tdate;
    }

    public void setTdate(String tdate) {
        this.tdate = tdate;
    }

    public String getTinfo() {
        return tinfo;
    }

    public void setTinfo(String tinfo) {
        this.tinfo = tinfo;
    }

    public String getTstatus() {
        return tstatus;
    }

    public void setTstatus(String tstatus) {
        this.tstatus = tstatus;
    }
}
