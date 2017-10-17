package com.szmz.entity;

/**
 * @author qieyixuan
 * @created at 2017年10月14
 */

public class UserSQR implements IEntity{
    private static final long serialVersionUID = -4927183302050331159L;

    private String ID;
    private String LOGINNAME;
    private int SEX;
    private String IDCARD;
    private String REALNAME;
    private String MOBILE;
    private String EMAIL;
    private String ADDRESS;
    private String PASSWORD;
    private int USERTYPE;
    private int STATUS;
    private int INBLACK;
    private String REGTIME;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getLOGINNAME() {
        return LOGINNAME;
    }

    public void setLOGINNAME(String LOGINNAME) {
        this.LOGINNAME = LOGINNAME;
    }

    public int getSEX() {
        return SEX;
    }

    public void setSEX(int SEX) {
        this.SEX = SEX;
    }

    public String getIDCARD() {
        return IDCARD;
    }

    public void setIDCARD(String IDCARD) {
        this.IDCARD = IDCARD;
    }

    public String getREALNAME() {
        return REALNAME;
    }

    public void setREALNAME(String REALNAME) {
        this.REALNAME = REALNAME;
    }

    public String getMOBILE() {
        return MOBILE;
    }

    public void setMOBILE(String MOBILE) {
        this.MOBILE = MOBILE;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public int getUSERTYPE() {
        return USERTYPE;
    }

    public void setUSERTYPE(int USERTYPE) {
        this.USERTYPE = USERTYPE;
    }

    public int getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(int STATUS) {
        this.STATUS = STATUS;
    }

    public int getINBLACK() {
        return INBLACK;
    }

    public void setINBLACK(int INBLACK) {
        this.INBLACK = INBLACK;
    }

    public String getREGTIME() {
        return REGTIME;
    }

    public void setREGTIME(String REGTIME) {
        this.REGTIME = REGTIME;
    }
}
