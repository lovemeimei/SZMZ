package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * Created by bz on 2017/11/28.
 */

public class JZ_YWBL_RHCC_RE extends BaseListRequest {

    public String familyId;//家庭ID
    public String countySpotCheckTime;//区县入户抽查日期
    public String countySpotCheckUser;//区县入户抽查人员
    public String countySpotCheckResult;//区县入户抽查结果
    public String countySpotCheckChargeUser;//区县入户抽查负责人
    public String coordinate;//地址


    public JZ_YWBL_RHCC_RE(String familyId, String countySpotCheckTime, String countySpotCheckUser,
                           String countySpotCheckResult, String countySpotCheckChargeUser, String coordinate) {
        this.familyId = familyId;
        this.countySpotCheckTime = countySpotCheckTime;
        this.countySpotCheckUser = countySpotCheckUser;
        this.countySpotCheckResult = countySpotCheckResult;
        this.countySpotCheckChargeUser = countySpotCheckChargeUser;
        this.coordinate = coordinate;
        setMd5Key(Md5Util.getMd5(familyId + countySpotCheckTime + countySpotCheckUser + countySpotCheckResult + countySpotCheckChargeUser + coordinate));
    }
}