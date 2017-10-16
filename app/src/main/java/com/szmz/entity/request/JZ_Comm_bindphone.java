package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/10/16 0016上午 11:02
 */

public class JZ_Comm_bindphone extends BaseRequest{

    private String LoginName;
    private String PassWord;
    private String phone;
    private String checkCode;

    public JZ_Comm_bindphone(String loginName, String passWord, String phone) {
        LoginName = loginName;
        PassWord = passWord;
        this.phone = phone;
        this.checkCode = "123456";
        setMd5Key(Md5Util.getMd5(LoginName+PassWord+phone+checkCode));
    }
}
