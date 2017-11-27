package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/24 0024下午 3:45
 */

public class YZS_TJ1_Req extends BaseRequest{

    private String areaCode;
    private String beginTime;
    private String endTime;

    public YZS_TJ1_Req(String areaCode, String beginTime, String endTime) {

        this.areaCode = areaCode;
        this.beginTime = beginTime;
        this.endTime = endTime;

        setMd5Key(Md5Util.getMd5(areaCode+beginTime+endTime));
    }
}


