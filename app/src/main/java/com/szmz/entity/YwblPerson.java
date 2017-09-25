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
    private String sfzh;//身份证号
    private List<YwblJtcy> jtcyList;
    private String srlx;//收入类型
    private String srxm;//收入项目
    private String srje;//收入金额
    private String slhmj;//数量或面积
    private String jldw;//计量单位
    private String srbz;//收入备注
    private String jtrk;//家庭人口数
    private String yhzgx;//与户主关系
    private String bsyrs;//被赡养人数
    private String syfzc;//赡养费支出
    private String sybz;//赡养备注
    private String zllb;//资料类别
    private String wj;//文件
    private String scjd;//上传节点

    private String cd;//彩电
    private String xyj;//洗衣机
    private String kt;//空调
    private String dn;//电脑
    private String bx;//冰箱
    private String qt;//其他
    private String zfxz;//住房性质
    private String zfmj;//住房面积

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getXyj() {
        return xyj;
    }

    public void setXyj(String xyj) {
        this.xyj = xyj;
    }

    public String getKt() {
        return kt;
    }

    public void setKt(String kt) {
        this.kt = kt;
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public String getBx() {
        return bx;
    }

    public void setBx(String bx) {
        this.bx = bx;
    }

    public String getQt() {
        return qt;
    }

    public void setQt(String qt) {
        this.qt = qt;
    }

    public String getZfxz() {
        return zfxz;
    }

    public void setZfxz(String zfxz) {
        this.zfxz = zfxz;
    }

    public String getZfmj() {
        return zfmj;
    }

    public void setZfmj(String zfmj) {
        this.zfmj = zfmj;
    }

    public String getZllb() {
        return zllb;
    }

    public void setZllb(String zllb) {
        this.zllb = zllb;
    }

    public String getWj() {
        return wj;
    }

    public void setWj(String wj) {
        this.wj = wj;
    }

    public String getScjd() {
        return scjd;
    }

    public void setScjd(String scjd) {
        this.scjd = scjd;
    }

    public String getJtrk() {
        return jtrk;
    }

    public void setJtrk(String jtrk) {
        this.jtrk = jtrk;
    }

    public String getYhzgx() {
        return yhzgx;
    }

    public void setYhzgx(String yhzgx) {
        this.yhzgx = yhzgx;
    }

    public String getBsyrs() {
        return bsyrs;
    }

    public void setBsyrs(String bsyrs) {
        this.bsyrs = bsyrs;
    }

    public String getSyfzc() {
        return syfzc;
    }

    public void setSyfzc(String syfzc) {
        this.syfzc = syfzc;
    }

    public String getSybz() {
        return sybz;
    }

    public void setSybz(String sybz) {
        this.sybz = sybz;
    }

    public String getSrlx() {
        return srlx;
    }

    public void setSrlx(String srlx) {
        this.srlx = srlx;
    }

    public String getSrxm() {
        return srxm;
    }

    public void setSrxm(String srxm) {
        this.srxm = srxm;
    }

    public String getSrje() {
        return srje;
    }

    public void setSrje(String srje) {
        this.srje = srje;
    }

    public String getSlhmj() {
        return slhmj;
    }

    public void setSlhmj(String slhmj) {
        this.slhmj = slhmj;
    }

    public String getJldw() {
        return jldw;
    }

    public void setJldw(String jldw) {
        this.jldw = jldw;
    }

    public String getSrbz() {
        return srbz;
    }

    public void setSrbz(String srbz) {
        this.srbz = srbz;
    }

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
