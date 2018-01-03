package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2018/1/2 0002下午 3:03
 */

public class JZ_SQR_HistoryDetailReq extends BaseRequest{
    private String id;
    private String userId;

    public JZ_SQR_HistoryDetailReq(String id, String userId) {
        this.id = id;
        this.userId = userId;

        setMd5Key(Md5Util.getMd5(id+userId));
    }
}
