package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/23 0023上午 9:44
 */

public class JZ_DC_req extends BaseRequest{
    private String userId;
    private String beginDate;
    private String endDate;

    public JZ_DC_req(String userId, String beginDate, String endDate) {
        this.userId = userId;
        this.beginDate = beginDate;
        this.endDate = endDate;

        setMd5Key(Md5Util.getMd5(userId+beginDate+endDate));
    }
}
