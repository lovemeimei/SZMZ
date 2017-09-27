package com.szmz.entity.request;

/**
 * @author qieyixuan
 * @created at 2016年05月17
 */
public class BaseListRequest extends BaseRequest {

    public static final int size = 20;

    public int pageSize = size;
    public int currentPage = 1;
    public boolean IS_PAGE = true;

}
