package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/12/1 0001下午 3:05
 */

public class JZSQR_historyDetail_Req extends BaseRequest{

    private String id;
    private String userId;

    public JZSQR_historyDetail_Req(String id, String userId) {
        this.id = id;
        this.userId = userId;

        setMd5Key(Md5Util.getMd5(id+userId));
    }
}
