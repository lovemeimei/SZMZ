package com.szmz.entity.request;

import com.szmz.R;
import com.szmz.utils.Md5Util;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/10/24 0024上午 10:43
 */

public class Comm_modifyUserInfoSQR_Req extends BaseRequest{
//Sex 1男 0女
    private String LoginName;
    private String PassWord;
    private String RealName;
    private String Sex;
    private String idCard;
    private String email;
    private String address;

    public Comm_modifyUserInfoSQR_Req(String loginName, String passWord, String realName, String sex, String idCard, String email, String address) {
        LoginName = loginName;
        PassWord = passWord;
        RealName = realName;
        Sex = sex;
        this.idCard = idCard;
        this.email = email;
        this.address = address;

        setMd5Key(Md5Util.getMd5(LoginName+PassWord+ RealName+Sex+idCard+email+address));
    }
}
