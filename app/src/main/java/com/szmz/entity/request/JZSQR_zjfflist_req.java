package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/12/1 0001下午 3:07
 */

public class JZSQR_zjfflist_req extends BaseListRequest{

    private String IDCard;

    public JZSQR_zjfflist_req(String IDCard,int currentPage) {
        this.IDCard = IDCard;
        this.CurrentPage = currentPage;

        setMd5Key(Md5Util.getMd5(IDCard+CurrentPage+PageSize));
    }
}
