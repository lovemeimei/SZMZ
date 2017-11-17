package com.szmz.entity.request;

import com.szmz.entity.IEntity;
import com.szmz.utils.Md5Util;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/7 0007下午 2:38
 */

public class JZ_Search_worker_Req extends BaseListRequest{

    private String userId;
    private String beginDate;
    private String endDate;
    private String typeCode;

    public JZ_Search_worker_Req(String userId, String beginDate, String endDate, String typeCode,int CurrentPage) {
        this.userId = userId;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.typeCode = typeCode;
        this.CurrentPage = CurrentPage;

        setMd5Key(Md5Util.getMd5(userId+beginDate+endDate+typeCode+CurrentPage+PageSize));
    }
}
