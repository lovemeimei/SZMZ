package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/29 0029下午 3:18
 */

public class YZS_SQR_jdlist_req extends BaseListRequest{

    private String CardID;
    private String ApplicationNo;

    public YZS_SQR_jdlist_req(String cardID,String ApplicationNo,int currentPage) {

        CardID = cardID;
       this.ApplicationNo = ApplicationNo;
        this.CurrentPage = currentPage;

        setMd5Key(Md5Util.getMd5(cardID+ApplicationNo+currentPage+PageSize));
    }
}
