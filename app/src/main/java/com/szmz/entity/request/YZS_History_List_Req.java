package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/8 0008下午 3:12
 */

public class YZS_History_List_Req extends BaseListRequest{
    private String userName;
    private String beginTime="";
    private String endTime="";

    public YZS_History_List_Req(String userName, int currentPage) {
        this.userName = userName;
        this.CurrentPage = currentPage;

        setMd5Key(Md5Util.getMd5(userName+currentPage+PageSize));
    }
}
