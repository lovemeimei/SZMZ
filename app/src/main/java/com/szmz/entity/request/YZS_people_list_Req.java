package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/24 0024下午 2:21
 */

public class YZS_people_list_Req extends BaseListRequest{

    private String userName;
    private String AreaCode;
    private String searchStr;

    public YZS_people_list_Req(String userName, String areaCode,String searchStr,int currentPage) {
        this.userName = userName;
        AreaCode = areaCode;
        this.searchStr =searchStr;
        this.CurrentPage = currentPage;
        setMd5Key(Md5Util.getMd5(userName+areaCode+searchStr+currentPage+PageSize));
    }
}
