package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/8 0008下午 3:12
 */

public class YZS_History_Detail_Req extends BaseRequest{
     String ID;

    public YZS_History_Detail_Req(String id) {

        ID = id;
        setMd5Key(Md5Util.getMd5(id));
    }
}
