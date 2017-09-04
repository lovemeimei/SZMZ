package com.szmz.net;

import com.szmz.entity.response.BaseResponse;
import com.szmz.utils.ToastManager;

import java.net.ConnectException;
import java.net.SocketException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author qieyixuan
 * @created at 2016年05月12
 */
public class MCallBack<T extends BaseResponse> implements Callback<T> {


    @Override
    public void onResponse(Call<T> call, Response<T> response) {

        doAfter();

        if (response != null && response.body() != null) {
            T result = response.body();

            if (result.getErrorCode() == 0) {

                doSuccess(result);

            } else {

                doBusinessError(result.getErrorMessage());

            }

        } else {
            doServerError();
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

        //网络超时异常
        //网络异常
        doAfter();
        if (t == null) {
            netError();
            return;
        }
        if (t.getMessage() == null) {
            netError();
            return;
        }
        if (t.getMessage().equals("Canceled") || t.getMessage().equals("Socket closed")) {
            doCancel();
        } else if (t.getMessage().contains("Failed to connect")) {
            netError();
        } else if (t.getMessage().contains("failed to connect")) {
            //链接服务器超时
            netError();
        } else if (t.getCause() instanceof ConnectException) {
            netError();
        } else if (t.getCause() instanceof SocketException) {
            netError();
        } else {
            doServerError();
        }
        t.printStackTrace();

    }


    protected void doSuccess(T result) {
    }

    protected void doBusinessError(String msg) {

        ToastManager.showShort(msg == null ? "" : msg);
    }

    protected void doServerError() {

    }

    protected void doAfter() {

    }

    protected void doCancel() {

    }

    protected void netError() {

    }
}
