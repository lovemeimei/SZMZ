package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/12/6 0006下午 4:58
 */

public class YZSSQR_HomeList_Req extends BaseListRequest
{
    private String CardID;

    public YZSSQR_HomeList_Req(String cardID,int currentPage) {
        CardID = cardID;
        CurrentPage =currentPage;
        setMd5Key(Md5Util.getMd5(cardID+currentPage+PageSize));
    }
}
