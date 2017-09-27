package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/27 0027下午 4:15
 */

public class HD_SearchDB extends BaseListRequest{


    public HD_SearchDB(String userId) {
        this.userId = userId;

        setMd5Key(Md5Util.getMd5(userId+currentPage+pageSize));
    }

    /**
     * userId : 1
     * md5Key : 10ACE5C483ED0F82E8C39443419D4C19
     */

    private String userId;
    private String md5Key;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMd5Key() {
        return md5Key;
    }

    public void setMd5Key(String md5Key) {
        this.md5Key = md5Key;
    }
}
