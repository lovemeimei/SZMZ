package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * Created by bz on 2017/12/4.
 */

public class JZ_SQR_JD_RE extends BaseListRequest {

    private String typeCode;//
    private String keyWords;//


    public JZ_SQR_JD_RE(String typeCode, String keyWords, int currentPage) {
        this.typeCode = typeCode;
        this.keyWords = keyWords;
        this.CurrentPage = currentPage;
        setMd5Key(Md5Util.getMd5(typeCode + keyWords + CurrentPage + PageSize));
    }
}