package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * Created by bz on 2017/10/23.
 */

public class JZ_YWBL_DZDA_SALVATION_RE extends BaseListRequest {

    private String userId;
    private String regionId;
    private String keyWords;
    private String type = "";
    //20203028: 查询可入户调查的人员，
    //20203029：查询可民主评议的人员，
    //20203030：查看可审核公示的人员，
    //20203031：查看可入户抽查的人员，
    //20203032：查看可审批公示的人员


    public JZ_YWBL_DZDA_SALVATION_RE(String userId, String type, String regionId, String keyWords, int CurrentPage, boolean isFromJZXX) {
        this.userId = userId;
        this.regionId = regionId;
        this.keyWords = keyWords;
        this.CurrentPage = CurrentPage;
        if (!isFromJZXX) {
            this.type = type;
            setMd5Key(Md5Util.getMd5(userId + regionId + keyWords + type + CurrentPage + PageSize));
        } else {
            setMd5Key(Md5Util.getMd5(userId + regionId + keyWords + CurrentPage + PageSize));
        }
    }

}