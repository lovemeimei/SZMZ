package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * Created by bz on 2017/11/28.
 */

public class JZ_YWBL_RHCC_RE extends BaseListRequest {

    private String familyIds;//家庭ID
    private String countySpotCheckTime;//区县入户抽查日期
    private String countySpotCheckUser;//区县入户抽查人员
    private String countySpotCheckResult;//区县入户抽查结果
    private String countySpotCheckChargeUser;//区县入户抽查负责人
    private String coordinate;//地址


    public JZ_YWBL_RHCC_RE(String familyIds, String countySpotCheckTime, String countySpotCheckUser,
                           String countySpotCheckResult, String countySpotCheckChargeUser, String coordinate) {
        this.familyIds = familyIds;
        this.countySpotCheckTime = countySpotCheckTime;
        this.countySpotCheckUser = countySpotCheckUser;
        this.countySpotCheckResult = countySpotCheckResult;
        this.countySpotCheckChargeUser = countySpotCheckChargeUser;
        this.coordinate = coordinate;
        setMd5Key(Md5Util.getMd5(familyIds + countySpotCheckTime + countySpotCheckUser + countySpotCheckResult + countySpotCheckChargeUser + coordinate));
    }
}