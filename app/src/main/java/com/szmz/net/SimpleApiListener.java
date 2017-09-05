package com.szmz.net;


import com.szmz.entity.response.BaseResponse;
import com.szmz.utils.UIUtil;

/**
 * @author qieyixuan
 * @created at 2016年05月12
 */
public class SimpleApiListener<T extends BaseResponse> implements ApiListener<T>{

    @Override
    public void doSuccess(T result) {

        UIUtil.doToast("操作成功");
    }

    @Override
    public void doBusinessError(String msg) {
        UIUtil.doToast(msg == null ? "" : msg);
    }

    @Override
    public void doServerError() {
        UIUtil.doToast("服务器发生错误");
    }

    @Override
    public void doAfter() {

    }

    @Override
    public void doCancel() {
        UIUtil.doToast("操作已取消");
    }

    @Override
    public void netError() {
        UIUtil.doToast("网络异常");
    }

}
