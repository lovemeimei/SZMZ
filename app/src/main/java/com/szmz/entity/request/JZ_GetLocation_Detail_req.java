package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

public class JZ_GetLocation_Detail_req extends BaseRequest{


    public JZ_GetLocation_Detail_req(String userId, String signTime) {
        this.userId = userId;
        this.signTime = signTime;

        setMd5Key(Md5Util.getMd5(userId+signTime));
    }

    private String userId;
    private String signTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

}
