package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/22 0022下午 4:39
 */

public class Comm_msg_req extends BaseRequest{

    private String LoginName;
    private String Phone;
    private String IDCard;
    private String UserType;

    public Comm_msg_req(String loginName, String phone, String IDCard, String userType) {

        LoginName = loginName;
        Phone = phone;
        this.IDCard = IDCard;
        UserType = userType;

        setMd5Key(Md5Util.getMd5(loginName+IDCard+phone+userType));
    }
}
