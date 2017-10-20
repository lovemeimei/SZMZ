package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/10/18 0018上午 10:09
 */

public class JZ_Comm_list_Req extends BaseListRequest{

    private String username;
    private String userid;

    public JZ_Comm_list_Req(String username, String userid,int cpager) {
        this.username = username;
        this.userid = userid;
        this.CurrentPage =cpager;

        setMd5Key(Md5Util.getMd5(username+userid+CurrentPage+PageSize));
    }
}
