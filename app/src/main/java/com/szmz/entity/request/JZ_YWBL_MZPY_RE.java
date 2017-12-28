package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * Created by bz on 2017/11/28.
 */

public class JZ_YWBL_MZPY_RE extends BaseListRequest {

    public String familyIds;//家庭ID
    public String streetCommentTime;//民主评议时间
    public String streetCommentResult;//民主评议结果
    public String streetCommentUser;//民主评议人员
    public String streetCommentChargeUser;//民主评议负责人
    public String coordinate;//地址


    public JZ_YWBL_MZPY_RE(String familyIds, String streetCommentTime, String streetCommentResult,
                           String streetCommentChargeUser, String streetCommentUser, String coordinate) {
        this.familyIds = familyIds;
        this.streetCommentTime = streetCommentTime;
        this.streetCommentResult = streetCommentResult;
        this.streetCommentChargeUser = streetCommentChargeUser;
        this.streetCommentUser = streetCommentUser;
        this.coordinate = coordinate;
        setMd5Key(Md5Util.getMd5(familyIds + streetCommentTime + streetCommentResult + streetCommentChargeUser + streetCommentUser + coordinate));
    }
}