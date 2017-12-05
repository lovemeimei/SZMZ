package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * Created by bz on 2017/12/4.
 */

public class JZ_SQR_JD_XF_DETAIL_RE extends BaseRequest {

    private String code;//

    public JZ_SQR_JD_XF_DETAIL_RE(String code) {
        this.code = code;
        setMd5Key(Md5Util.getMd5(code));
    }
}