package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/10/24 0024上午 10:48
 */

public class Comm_checkCode_sqr_Req extends BaseRequest{

    private String phone;
    private String code;

    public Comm_checkCode_sqr_Req(String phone, String code) {
        this.phone = phone;
        this.code = code;

        setMd5Key(Md5Util.getMd5(phone+code));
    }
}
