package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * Created by bz on 2017/10/23.
 */

public class JZ_YWBL_DZDA_SALVATION_RE extends BaseListRequest {

    private String regionId;
    private String keyWords;


    public JZ_YWBL_DZDA_SALVATION_RE(String regionId, String keyWords, int CurrentPage) {
        this.regionId = regionId;
        this.keyWords = keyWords;
        setMd5Key(Md5Util.getMd5(regionId + keyWords + CurrentPage + PageSize));
    }
}