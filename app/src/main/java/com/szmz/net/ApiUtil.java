package com.szmz.net;

import android.content.Context;
import android.content.DialogInterface;

import com.afollestad.materialdialogs.MaterialDialog;
import com.orhanobut.logger.Logger;
import com.szmz.entity.response.BaseResponse;

import retrofit2.Call;

/**
 * @author qieyixuan
 * @created at 2016年05月12
 */
public class ApiUtil<T extends BaseResponse> {

    private Call call;
    private Context context;
    MaterialDialog dialog = null;
    ApiListener listener = null;
    private boolean isSHowDialog = true;

    public ApiUtil(Context context, Call tCall, ApiListener listener, boolean isShowDialog) {
        this.call = tCall;
        this.context = context;
        this.listener = listener;
        this.isSHowDialog = isShowDialog;
    }

    public void excute() {

        doBefore();
        call.enqueue(new MCallBack<T>() {

            @Override
            protected void doSuccess(T result) {
                super.doSuccess(result);

                listener.doSuccess(result);

            }

            @Override
            protected void doAfter() {
                super.doAfter();
                if (isSHowDialog && dialog != null && dialog.isShowing()) {

                    try {
                        dialog.dismiss();
                    } catch (IllegalArgumentException e) {
                        Logger.e("dissmiss报错");
                    }
                }

                listener.doAfter();
            }

            @Override
            protected void doBusinessError(String msg) {
                listener.doBusinessError(msg);
            }

            @Override
            protected void doServerError() {
                super.doServerError();
                listener.doServerError();
            }

            @Override
            protected void doCancel() {
                super.doCancel();
                listener.doCancel();
            }

            @Override
            protected void netError() {
                super.netError();
                listener.netError();
            }
        });
    }


    private void doBefore() {

        if (isSHowDialog) {
            dialog = new MaterialDialog.Builder(context)
                    .content("请稍后···")
                    .progress(true, 100)
                    .cancelable(true)
                    .canceledOnTouchOutside(false)
                    .cancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            if (call != null) {
                                call.cancel();
                            }
                        }
                    })
                    .build();
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    call.cancel();
                }
            });
            dialog.show();
        }
    }

}
