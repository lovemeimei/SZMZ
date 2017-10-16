package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/28 0028下午 3:44
 */

public class JZ_Comm_Req extends BaseRequest{

    private String username;

    public JZ_Comm_Req(String username) {

        setUsername(username);

        setMd5Key(Md5Util.getMd5(username));
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
