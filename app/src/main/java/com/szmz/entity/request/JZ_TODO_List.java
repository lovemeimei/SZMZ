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
    private String keyWords;

    public JZ_TODO_List(String username,  String functionid,String keyWords,int currentPage) {
        this.username = username;
        this.userid = "";
        this.functionid = functionid;
        this.keyWords = keyWords;
        this.CurrentPage = currentPage;

        setMd5Key(Md5Util.getMd5(username+userid+functionid+keyWords+CurrentPage+PageSize));
    }
}
