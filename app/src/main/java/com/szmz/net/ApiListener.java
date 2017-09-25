package com.szmz.net;

/**
 * @author qieyixuan
 * @created at 2016年05月12
 */
public interface ApiListener<T> {

    void doSuccess(T result);

    void doBusinessError(String msg);

    void doServerError();

    void doAfter();

    void doCancel();

    void netError();
}
