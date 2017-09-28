package com.szmz.entity.request;

/**
 * @author qieyixuan
 * @created at 2016年05月17
 */
public class BaseListRequest extends BaseRequest {

    public static final int size = 20;

    public int PageSize = size;
    public int CurrentPage = 1;

}
