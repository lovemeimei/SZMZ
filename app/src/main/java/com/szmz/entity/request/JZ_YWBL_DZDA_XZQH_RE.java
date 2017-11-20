package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * Created by bz on 2017/10/20.
 */

public class JZ_YWBL_DZDA_XZQH_RE extends BaseListRequest {
    private String userId;


    public JZ_YWBL_DZDA_XZQH_RE(String userId) {
        this.userId = userId;
        setMd5Key(Md5Util.getMd5(userId));
    }
}
