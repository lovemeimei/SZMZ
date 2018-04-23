package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * Created by bz on 2018/4/12.
 */

public class JZ_YWBL_APPSIGN_RE extends BaseRequest {

    private String familyId;//家庭ID
    private String dicId;//资料分类：
//            20203028:入户调查资料，
//            20203029：民主评议资料，
//            20203030：审核公示资料，
//            20203031：入户抽查资料，
//            20203032：审批公示资料

    private String userId;
    private String address;//地址
    private String signTime;
    private String longitude;
    private String latitude;


    public JZ_YWBL_APPSIGN_RE(String familyId, String dicId, String userId,
                              String address, String signTime, String longitude, String latitude) {
        this.familyId = familyId;
        this.dicId = dicId;
        this.userId = userId;
        this.address = address;
        this.signTime = signTime;
        this.longitude = longitude;
        this.latitude = latitude;

        setMd5Key(Md5Util.getMd5(familyId + dicId + userId + address + signTime + longitude + latitude));
    }
}
