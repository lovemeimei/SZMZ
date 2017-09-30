package com.szmz.net;

import com.szmz.entity.HD_JG_BGDY_RES;
import com.szmz.entity.request.HD_SearchDB;
import com.szmz.entity.request.JZ_Comm_Req;
import com.szmz.entity.request.ModifyPW;
import com.szmz.entity.request.phoneLoginRequest;
import com.szmz.entity.response.CommResponse;
import com.szmz.entity.response.HD_JG_MGRY;
import com.szmz.entity.response.HD_JG_MGRY2;
import com.szmz.entity.response.HD_JG_YCCL;
import com.szmz.entity.response.HD_JG_YWBL1;
import com.szmz.entity.response.HD_JG_YWBL2;
import com.szmz.entity.response.HD_SearchDB_RES;
import com.szmz.entity.response.HD_TJ_HDDX;
import com.szmz.entity.response.HD_dict;
import com.szmz.entity.response.phoneLoginR;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author qieyixuan
 * @created at 2016年05月10
 */
public interface ApiService {
    //统一登录
    @POST("phoneLoginController.do?phoneLogin")
    Call<phoneLoginR>  login(@Body phoneLoginRequest request);
    /**************************救助系统*********************************/
    @POST("api/appCommonController.do?getUserInfo")
    Call<CommResponse>  loginJZ(@Body JZ_Comm_Req request);
    //修改密码
    @POST("phoneLoginController.do?modifyPassword")
    Call<CommResponse> modifyPW(@Body ModifyPW request);
    /**************************医疗一站式*********************************/

    /**************************核对系统*********************************/
    //字典业务类型
    @POST("appCheck/worker/getDict.do?getDictList")
    Call<HD_dict> getDictYWLX(@Body RequestBody body);

    //我的待办
    @POST("appCheck/worker/todo.do?getBatchList")
    Call<HD_SearchDB_RES> getDBlist3(@Body RequestBody body);
    //我的已办
    @POST("appCheck/worker/done.do?getBatchList")
    Call<HD_SearchDB_RES> getYBlist(@Body RequestBody body);
    //我的退回
    @POST("appCheck/worker/back.do?getBatchList")
    Call<HD_SearchDB_RES> getTHlist(@Body RequestBody body);
    //终止退回
    @POST("appCheck/worker/endBack.do?getBatchList")
    Call<HD_SearchDB_RES> getZZTHlist(@Body RequestBody body);
    //监管 报告打印监管
    @POST("appCheck/worker/print.do?getReportPrintList")
    Call<HD_JG_BGDY_RES> getJG_BGDYlist(@Body RequestBody body);
    //	查询业务办理监管主页信息
    @POST("appCheck/worker/bussiness.do?getBussinessBatchList")
    Call<HD_JG_YWBL1> getJG_ywblList1(@Body RequestBody body);
    //查询批次对应申请信息
    @POST("appCheck/worker/applyBussiness.do?getApplyBussinessList")
    Call<HD_JG_YWBL2> getJG_ywblList2(@Body RequestBody body);

    //监管查询异常
    @POST("appCheck/worker/operation.do?getOperationList")
    Call<HD_JG_YCCL> getJG_ycclList(@Body RequestBody body);

    //监管敏感人员
    @POST("appCheck/worker/sensitive.do?getSensitiveBatchList")
    Call<HD_JG_MGRY> getJG_MGRY_List(@Body RequestBody body);
    @POST("appCheck/worker/sensitiveApply.do?getSensitiveApplyList")
    Call<HD_JG_MGRY2> getJG_MGRY_List2(@Body RequestBody body);
    @POST("appCheck/worker/senitiveAudit.do?auditApply")
    Call<CommResponse> getJG_MGRY_SH(@Body RequestBody body);

    //统计分析
    @POST("appCheck/worker/checkApply.do?getApplyStatistics")
    Call<HD_TJ_HDDX> getTJ_HDDX(@Body RequestBody body);
}
