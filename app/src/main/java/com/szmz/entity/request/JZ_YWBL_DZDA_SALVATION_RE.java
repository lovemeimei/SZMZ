package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * Created by bz on 2017/10/23.
 */

public class JZ_YWBL_DZDA_SALVATION_RE extends BaseListRequest {

    private String regionId;


    public JZ_YWBL_DZDA_SALVATION_RE(String regionId,int CurrentPage) {
        this.regionId = regionId;
        setMd5Key(Md5Util.getMd5(regionId+CurrentPage+PageSize));
    }
}