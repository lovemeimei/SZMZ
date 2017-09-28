package com.szmz.entity.response;

import com.szmz.entity.IEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bz on 2017/9/3.
 */

public class BaseResponse<T> implements IEntity{


    public Error Error;
    public List<T> Result=new ArrayList<T>();
    public int TotalNum;
}
