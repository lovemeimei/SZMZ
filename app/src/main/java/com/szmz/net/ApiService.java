package com.szmz.net;

import com.szmz.entity.request.HD_SearchDB;
import com.szmz.entity.request.ModifyPW;
import com.szmz.entity.request.phoneLoginRequest;
import com.szmz.entity.response.CommResponse;
import com.szmz.entity.response.HD_SearchDB_RES;
import com.szmz.entity.response.phoneLoginR;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author qieyixuan
 * @created at 2016年05月10
 */
public interface ApiService {
    /**************************救助系统*********************************/
    //登录
    @POST("phoneLoginController.do?phoneLogin")
    Call<phoneLoginR>  login(@Body phoneLoginRequest request);
    //修改密码
    @POST("phoneLoginController.do?modifyPassword")
    Call<CommResponse> modifyPW(@Body ModifyPW request);
    /**************************医疗一站式*********************************/

    /**************************核对系统*********************************/
    @POST("appCheck/worker/todo.do?getBatchList")
    Call<HD_SearchDB_RES> test(@Body HD_SearchDB body);
}
