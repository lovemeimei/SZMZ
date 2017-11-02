package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/10/24 0024上午 9:16
 */

public class Comm_getCode_Req extends BaseRequest{
    private String phone;

    public Comm_getCode_Req(String phone) {
        this.phone = phone;
        setMd5Key(Md5Util.getMd5(phone));
    }
}
