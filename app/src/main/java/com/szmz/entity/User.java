package com.szmz.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * @author qieyixuan
 * @created at 2016年04月24
 */
@Table(name = "User")
public class User implements IEntity {

    @Column(name = "accountJZ", isId = true)
    private String accountJZ;
    @Column(name = "accountYZS")
    private String accountYZS;
    @Column(name = "accountHD")
    private String accountHD;
    @Column(name = "userName")
    private String userName;
    @Column(name = "type")
    private int type;
    @Column(name = "pw")
    private String pw;
    @Column(name = "realName")
    private String realName;
    @Column(name = "sex")
    private String sex;
    @Column(name = "adderss")
    private String adderss;
    @Column(name = "idCode")
    private String idCode;
    @Column(name = "idJZ")
    private String idJZ;
    @Column(name = "idYZS")
    private String idYZS;
    @Column(name = "idHD")
    private String idHD;
    @Column(name = "phone")
    private String phone;
    @Column(name = "personId")
    private String personId;
    @Column(name = "email")
    private String email;
    @Column(name = "officePhone")
    private String officePhone;
    @Column(name = "departName")
    private String departName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAccountJZ() {
        return accountJZ;
    }

    public void setAccountJZ(String accountJZ) {
        this.accountJZ = accountJZ;
    }

    public String getAccountYZS() {
        return accountYZS;
    }

    public void setAccountYZS(String accountYZS) {
        this.accountYZS = accountYZS;
    }

    public String getAccountHD() {
        return accountHD;
    }

    public void setAccountHD(String accountHD) {
        this.accountHD = accountHD;
    }


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
