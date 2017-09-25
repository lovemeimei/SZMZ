package com.szmz.entity.request;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/25 0025下午 3:21
 */

public class ModifyPW extends BaseRequest{


    public ModifyPW(String loginName, String passWord, String newPassWord) {
        LoginName = loginName;
        PassWord = passWord;
        this.newPassWord = newPassWord;

        setMd5Key(loginName+passWord+newPassWord);
    }

    public String LoginName;
    public String PassWord;
    public String newPassWord;
}
