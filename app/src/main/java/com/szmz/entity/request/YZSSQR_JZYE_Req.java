package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/29 0029下午 5:09
 */

public class YZSSQR_JZYE_Req extends BaseListRequest{

    private String CardID;
    private String rescueCategory="-1";
    private String accountCategory="-1";


    public YZSSQR_JZYE_Req(String cardID,int currentPage) {
        CardID = cardID;
        CurrentPage = currentPage;

        setMd5Key(Md5Util.getMd5(cardID+rescueCategory+accountCategory+currentPage+PageSize));
    }
}
