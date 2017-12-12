package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * Created by bz on 2017/12/4.
 */

public class JZ_SQR_JD_RE extends BaseListRequest {

    private String idCard;//


    public JZ_SQR_JD_RE(String idCard, int currentPage) {
        this.idCard = idCard;
        this.CurrentPage = currentPage;
        setMd5Key(Md5Util.getMd5(idCard + CurrentPage + PageSize));
    }
}