package com.szmz.entity;

/**
 * Created by bz on 2017/9/27.
 */

public class HdxtGrcxInfo implements IEntity {
    private String pcmc;//批次名称
    private String ywlx;//业务类型
    private String wtsj;//委托时间
    private String hzsj;//汇总时间
    private String cjsj;//创建时间
    private String zts;//总条数
    private String yclts;//已处理条数
    private String blsj;//办理时间
    private String thyy;//退回原因

    public String getHzsj() {
        return hzsj;
    }

    public void setHzsj(String hzsj) {
        this.hzsj = hzsj;
    }

    public String getYclts() {
        return yclts;
    }

    public void setYclts(String yclts) {
        this.yclts = yclts;
    }

    public String getThyy() {
        return thyy;
    }

    public void setThyy(String thyy) {
        this.thyy = thyy;
    }

    public String getPcmc() {
        return pcmc;
    }

    public void setPcmc(String pcmc) {
        this.pcmc = pcmc;
    }

    public String getYwlx() {
        return ywlx;
    }

    public void setYwlx(String ywlx) {
        this.ywlx = ywlx;
    }

    public String getWtsj() {
        return wtsj;
    }

    public void setWtsj(String wtsj) {
        this.wtsj = wtsj;
    }

    public String getCjsj() {
        return cjsj;
    }

    public void setCjsj(String cjsj) {
        this.cjsj = cjsj;
    }

    public String getZts() {
        return zts;
    }

    public void setZts(String zts) {
        this.zts = zts;
    }

    public String getBlsj() {
        return blsj;
    }

    public void setBlsj(String blsj) {
        this.blsj = blsj;
    }
}
