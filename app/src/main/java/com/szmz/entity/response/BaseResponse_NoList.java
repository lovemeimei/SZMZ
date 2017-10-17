package com.szmz.entity.response;

import com.szmz.entity.IEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bz on 2017/9/3.
 */

public class BaseResponse_NoList<T> implements IEntity{


    public Error Error;
    public T Result;
    public int TotalNum;
}
