package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/10/24 0024上午 9:12
 */

public class Register_Req extends BaseRequest{

    private String userName;
    private String realName;
    private String idCard;
    private String password;
    private String mobile;
    private String mobileCode;
    private String email;

    public Register_Req(String userName, String realName, String idCard, String password, String mobile, String mobileCode, String email) {
        this.userName = userName;
        this.realName = realName;
        this.idCard = idCard;
        this.password = password;
        this.mobile = mobile;
        this.mobileCode = mobileCode;
        this.email = email;

        setMd5Key(Md5Util.getMd5(userName+realName+idCard+password+mobile+mobileCode+email));
    }
}
