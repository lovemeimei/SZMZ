package com.szmz.net;

import com.szmz.entity.request.phoneLoginRequest;
import com.szmz.entity.response.phoneLoginR;

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


    @POST("phoneLoginController.do?phoneLogin")
    Call<phoneLoginR>  login(@Body phoneLoginRequest request);


    @POST("phoneLoginController.do?phoneLogin")@FormUrlEncoded
    Call<phoneLoginR>  login2(@Field("LoginName") String name,@Field("PassWord") String pw,@Field("Md5Key") String Md5Key);
}
