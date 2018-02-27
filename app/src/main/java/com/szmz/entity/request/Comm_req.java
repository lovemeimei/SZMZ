package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * Created by bz on 2018/2/27.
 */

public class Comm_req extends BaseRequest {
    String userId;

    public Comm_req(String userId) {
        this.userId = userId;
        setMd5Key(Md5Util.getMd5(userId));
    }
}
