package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/12/1 0001下午 2:57
 */

public class JZ_SQR_historyList_req extends BaseListRequest{

    private String idCard;

    public JZ_SQR_historyList_req(String idCard,int currentPage) {
        this.idCard = idCard;
        CurrentPage = currentPage;

        setMd5Key(Md5Util.getMd5(idCard+currentPage+PageSize));

    }
}
