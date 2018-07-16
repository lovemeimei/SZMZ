package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

public class JZ_GetLocation_Users_req extends BaseRequest{

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public JZ_GetLocation_Users_req(String userId) {
        this.userId = userId;
        setMd5Key(Md5Util.getMd5(userId));
    }


}
