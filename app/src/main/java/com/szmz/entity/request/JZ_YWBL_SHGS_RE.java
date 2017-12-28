package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * Created by bz on 2017/11/28.
 */

public class JZ_YWBL_SHGS_RE extends BaseListRequest {

    public String familyIds;//家庭ID
    public String streetPublicStartTime;//街道公示起始日期
    public String streetPublicEndTime;//街道公示截止日期
    public String streetPublicResult;//街道公示结果
    public String streetPublicUser;//街道公示记录人员
    public String streetPublicObjectionInfo;//街道公示异议内容
    public String streetApproveInfo;//街道审核意见
    public String streetApproveReason;//街道审核意见原因
    public String streetApproveChargeUser;//街道审核负责人
    public String coordinate;//地址

    public JZ_YWBL_SHGS_RE(String familyIds, String streetPublicStartTime, String streetPublicEndTime,
                           String streetPublicResult, String streetPublicUser, String streetPublicObjectionInfo,
                           String streetApproveInfo, String streetApproveReason, String streetApproveChargeUser, String coordinate) {
        this.familyIds = familyIds;
        this.streetPublicStartTime = streetPublicStartTime;
        this.streetPublicEndTime = streetPublicEndTime;
        this.streetPublicResult = streetPublicResult;
        this.streetPublicUser = streetPublicUser;
        this.streetPublicObjectionInfo = streetPublicObjectionInfo;
        this.streetApproveInfo = streetApproveInfo;
        this.streetApproveReason = streetApproveReason;
        this.streetApproveChargeUser = streetApproveChargeUser;

        this.coordinate = coordinate;
        setMd5Key(Md5Util.getMd5(familyIds + streetPublicStartTime + streetPublicEndTime +
                streetPublicResult + streetPublicUser + streetPublicObjectionInfo + streetApproveInfo + streetApproveReason +
                streetApproveChargeUser + coordinate));
    }
}
