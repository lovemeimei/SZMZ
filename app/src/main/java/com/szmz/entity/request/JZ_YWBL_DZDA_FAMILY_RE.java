package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * Created by bz on 2017/10/23.
 */

public class JZ_YWBL_DZDA_FAMILY_RE extends BaseListRequest {

    private String id;


    public JZ_YWBL_DZDA_FAMILY_RE(String id) {
        this.id = id;
        setMd5Key(Md5Util.getMd5(id + CurrentPage + PageSize));
    }
}