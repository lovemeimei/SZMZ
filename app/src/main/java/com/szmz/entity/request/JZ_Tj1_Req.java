package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/21 0021下午 3:46
 */

public class JZ_Tj1_Req  extends BaseRequest{
    private String regionId;
    private String beginDate;
    private String endDate;
    private int CurrentPage;
    private int PageSize;

    public JZ_Tj1_Req(String regionId,String beginDate,String endDate) {
        this.regionId = regionId;
        this.beginDate = beginDate;
        this.endDate = endDate;
        CurrentPage = 1;
        PageSize = 100;
        setMd5Key(Md5Util.getMd5(regionId+beginDate+endDate+CurrentPage+PageSize));
    }
}
