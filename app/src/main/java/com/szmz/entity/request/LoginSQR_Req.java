package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * @author qieyixuan
 * @created at 2017年10月14
 */

public class LoginSQR_Req extends BaseRequest {

    private String LoginName;
    private String PassWord;

    public LoginSQR_Req(String user,String pw) {

        this.LoginName = user;
        this.PassWord = pw;

        this.setMd5Key(Md5Util.getMd5(user+pw));
    }
}
