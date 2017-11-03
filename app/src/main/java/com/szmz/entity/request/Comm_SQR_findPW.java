package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/1 0001上午 10:39
 */

public class Comm_SQR_findPW extends BaseRequest{

    private String mobile;
    private String code;
    private String newPassWord;

    public Comm_SQR_findPW(String mobile, String code, String newPassWord) {
        this.mobile = mobile;
        this.code = code;
        this.newPassWord = newPassWord;

        setMd5Key(Md5Util.getMd5(code+mobile+newPassWord));
    }
}
