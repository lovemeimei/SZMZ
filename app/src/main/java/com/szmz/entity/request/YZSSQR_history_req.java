package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/29 0029下午 4:51
 */

public class YZSSQR_history_req extends BaseListRequest{

    private String CardID;

    public YZSSQR_history_req(String cardID,int currentPage) {

        CardID = cardID;
        CurrentPage = currentPage;
        setMd5Key(Md5Util.getMd5(cardID+currentPage+PageSize));

    }
}
