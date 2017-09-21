package com.szmz.entity.response;

import com.szmz.entity.IEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bz on 2017/9/3.
 */

public class BaseResponse<T> implements IEntity{

    public Error error;
    public List<T> result=new ArrayList<T>();
    public  String totalNum;
}
