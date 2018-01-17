package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * Created by bz on 2017/11/28.
 */

public class JZ_YWBL_SPGS_RE extends BaseListRequest {

    public String familyIds;//家庭ID
    public String countyPublicStartTime;//区县入户抽查日期
    public String countyPublicEndTime;//区县入户抽查人员
    public String countyPublicResult;//区县入户抽查结果
    public String countyPublicUser;//区县入户抽查负责人
    public String countyPublicObjectionInfo;
    public String coordinate;//地址
    public String countyApproveTime;
    public String countyApproveUser;
    public String countyApproveChargeUser;


    public JZ_YWBL_SPGS_RE(String familyIds, String countyPublicStartTime, String countyPublicEndTime,
                           String countyPublicResult, String countyPublicUser,
                           String countyPublicObjectionInfo, String coordinate, String countyApproveTime, String countyApproveUser, String countyApproveChargeUser) {
        this.familyIds = familyIds;
        this.countyPublicStartTime = countyPublicStartTime;
        this.countyPublicEndTime = countyPublicEndTime;
        this.countyPublicResult = countyPublicResult;
        this.countyPublicUser = countyPublicUser;
        this.countyPublicObjectionInfo = countyPublicObjectionInfo;
        this.coordinate = coordinate;
        this.countyApproveTime = countyApproveTime;
        this.countyApproveUser = countyApproveUser;
        this.countyApproveChargeUser = countyApproveChargeUser;
        setMd5Key(Md5Util.getMd5(familyIds + countyPublicStartTime + countyPublicEndTime + countyPublicResult + countyPublicUser +
                countyPublicObjectionInfo + countyApproveTime + countyApproveUser + countyApproveChargeUser + coordinate));
    }
}