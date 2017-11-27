package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/24 0024下午 2:38
 */

public class YZS_qh_req extends BaseRequest{

    private String userName;

    public YZS_qh_req(String userName) {
        this.userName = userName;

        setMd5Key(Md5Util.getMd5(userName));
    }
}
