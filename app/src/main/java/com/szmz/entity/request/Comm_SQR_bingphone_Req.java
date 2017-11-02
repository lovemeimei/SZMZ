package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/10/24 0024上午 10:50
 */

public class Comm_SQR_bingphone_Req extends BaseRequest {
    private String LoginName;
    private String PassWord;
    private String phone;

    public Comm_SQR_bingphone_Req(String loginName, String passWord, String phone) {
        LoginName = loginName;
        PassWord = passWord;
        this.phone = phone;

        setMd5Key(Md5Util.getMd5(loginName+passWord+phone));
    }
}
