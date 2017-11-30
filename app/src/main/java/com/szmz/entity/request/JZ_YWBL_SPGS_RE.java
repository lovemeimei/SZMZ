package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * Created by bz on 2017/11/28.
 */

public class JZ_YWBL_SPGS_RE extends BaseListRequest {

    private String familyIds;//家庭ID
    private String countyPublicStartTime;//区县入户抽查日期
    private String countyPublicEndTime;//区县入户抽查人员
    private String countyPublicResult;//区县入户抽查结果
    private String countyPublicUser;//区县入户抽查负责人
    private String countyPublicObjectionInfo;
    private String coordinate;//地址


    public JZ_YWBL_SPGS_RE(String familyIds, String countyPublicStartTime, String countyPublicEndTime,
                           String countyPublicResult, String countyPublicUser, String countyPublicObjectionInfo, String coordinate) {
        this.familyIds = familyIds;
        this.countyPublicStartTime = countyPublicStartTime;
        this.countyPublicEndTime = countyPublicEndTime;
        this.countyPublicResult = countyPublicResult;
        this.countyPublicUser = countyPublicUser;
        this.countyPublicObjectionInfo = countyPublicObjectionInfo;
        this.coordinate = coordinate;
        setMd5Key(Md5Util.getMd5(familyIds + countyPublicStartTime + countyPublicEndTime + countyPublicResult + countyPublicUser + countyPublicObjectionInfo + coordinate));
    }
}