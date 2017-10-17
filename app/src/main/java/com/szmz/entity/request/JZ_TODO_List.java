package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * @author qieyixuan
 * @created at 2017年10月16
 */

public class JZ_TODO_List extends BaseListRequest {

    private String username;
    private String userid;
    private String functionid;

    public JZ_TODO_List(String username, String userid, String functionid,int currentPage) {
        this.username = username;
        this.userid = userid;
        this.functionid = functionid;
        this.CurrentPage = currentPage;

        setMd5Key(Md5Util.getMd5(username+userid+functionid+CurrentPage+PageSize));
    }
}