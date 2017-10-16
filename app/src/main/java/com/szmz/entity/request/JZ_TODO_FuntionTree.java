package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * @author qieyixuan
 * @created at 2017年10月16
 */

public class JZ_TODO_FuntionTree extends BaseRequest{
    private String username;
    private String userid;

    public JZ_TODO_FuntionTree(String username, String userid) {
        this.username = username;
        this.userid = userid;
        setMd5Key(Md5Util.getMd5(username+userid));
    }
}
