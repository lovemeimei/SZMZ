package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/21 0021下午 3:51
 */

public class phoneLoginRequest extends BaseRequest {

    public phoneLoginRequest(String loginName, String passWord) {

        setLoginName(loginName);
        setPassWord(passWord);
        setMd5Key(Md5Util.getMd5(loginName+passWord));
    }
    //phoneLogin&LoginName=admin&PassWord=123456&Md5Key=64E0981DC31E80B98630685D46554D7F
    /**
     * LoginName : ceshi
     * PassWord : 111111
     * Md5Key : 10ACE5C483ED0F82E8C39443419D4C19
     */
    private String LoginName;
    private String PassWord;

    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String loginName) {
        LoginName = loginName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }
}
