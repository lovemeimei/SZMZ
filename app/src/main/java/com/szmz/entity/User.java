package com.szmz.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * @author qieyixuan
 * @created at 2016年04月24
 */
@Table(name = "User")
public class User implements IEntity {

    @Column(name = "Id", isId = true)
    private String Id;
    @Column(name = "phone")
    private String phone;
    @Column(name = "idcard")
    private String idcard;
    @Column(name = "realname")
    private String realname;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "sex")
    private int sex;//1男,0女
    @Column(name = "acount")
    private String acount;
    @Column(name = "pw")
    private String pw;
    @Column(name = "orgName")
    private String orgName;//管理组织机构名称
    @Column(name = "orgID")
    private String orgID;//管理组织机构id
    @Column(name = "OwnName")
    private String OwnName;//直属组织机构名称
    @Column(name = "OwnID")
    private String OwnID;//直属组织机构di
    @Column(name = "roles")
    private String roles;//权限
    @Column(name = "type")
    private String type;//管理员1游客2
    @Column(name = "user_type")
    private String user_type;//66部门负责人,67普通员工

    @Column(name = "sys_ORG")
    private String sysORG;//管理的组织机构id,多个用","隔开


    public String getSysORG() {
        return sysORG;
    }

    public void setSysORG(String sysORG) {
        this.sysORG = sysORG;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgID() {
        return orgID;
    }

    public void setOrgID(String orgID) {
        this.orgID = orgID;
    }

    public String getOwnName() {
        return OwnName;
    }

    public void setOwnName(String ownName) {
        OwnName = ownName;
    }

    public String getOwnID() {
        return OwnID;
    }

    public void setOwnID(String ownID) {
        OwnID = ownID;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getAcount() {
        return acount;
    }

    public void setAcount(String acount) {
        this.acount = acount;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

}
