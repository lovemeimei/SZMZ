package com.szmz.net;

import com.szmz.entity.HD_JG_BGDY_RES;
import com.szmz.entity.request.Comm_SQR_bingphone_Req;
import com.szmz.entity.request.Comm_SQR_findPW;
import com.szmz.entity.request.Comm_SQR_modifyPW_Req;
import com.szmz.entity.request.Comm_checkCode_sqr_Req;
import com.szmz.entity.request.Comm_getCode_Req;
import com.szmz.entity.request.Comm_modifyUserInfoSQR_Req;
import com.szmz.entity.request.JZ_Comm_Req;
import com.szmz.entity.request.JZ_Comm_bindphone;
import com.szmz.entity.request.JZ_Comm_list_Req;
import com.szmz.entity.request.JZ_Comm_modifyInfo;
import com.szmz.entity.request.JZ_Comm_modifyPhone;
import com.szmz.entity.request.JZ_TODO_FuntionTree;
import com.szmz.entity.request.JZ_TODO_List;
import com.szmz.entity.request.JZ_YWBL_DZDA_FAMILY_RE;
import com.szmz.entity.request.JZ_YWBL_DZDA_SALVATION_RE;
import com.szmz.entity.request.LoginSQR_Req;
import com.szmz.entity.request.ModifyPW;
import com.szmz.entity.request.Register_Req;
import com.szmz.entity.request.phoneLoginRequest;
import com.szmz.entity.response.CommResponse;
import com.szmz.entity.response.HD_JG_MGRY;
import com.szmz.entity.response.HD_JG_MGRY2;
import com.szmz.entity.response.HD_JG_YCCL;
import com.szmz.entity.response.HD_JG_YWBL1;
import com.szmz.entity.response.HD_JG_YWBL2;
import com.szmz.entity.response.HD_SQR_GRCX_JDCK_RES;
import com.szmz.entity.response.HD_SearchDB_RES;
import com.szmz.entity.response.HD_TJ_GX;
import com.szmz.entity.response.HD_TJ_HDDX;
import com.szmz.entity.response.HD_TJ_QYRC;
import com.szmz.entity.response.HD_TJ_YWQS;
import com.szmz.entity.response.HD_XXTZ;
import com.szmz.entity.response.HD_XZQH_Response;
import com.szmz.entity.response.HD_dict;
import com.szmz.entity.response.HD_hdzc;
import com.szmz.entity.response.JZ_GetUserInfo;
import com.szmz.entity.response.JZ_MSG_FC_Res;
import com.szmz.entity.response.JZ_MSG_SP_Res;
import com.szmz.entity.response.JZ_Todo_MenuTree;
import com.szmz.entity.response.JZ_Todolist;
import com.szmz.entity.response.JZ_YWBL_DZDA_Family;
import com.szmz.entity.response.JZ_YWBL_DZDA_Salvation;
import com.szmz.entity.response.JZ_YWBL_DZDA_XZQH;
import com.szmz.entity.response.LoginSQR_Res;
import com.szmz.entity.response.phoneLoginR;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


/**
 * @author qieyixuan
 * @created at 2016年05月10
 */
public interface ApiService {
    //统一登录
    @POST("SalvationPlatform/phoneLoginController.do?phoneLogin")
    Call<phoneLoginR> login(@Body phoneLoginRequest request);

    //工作人员绑定手机号
    @POST("SalvationPlatform/phoneLoginController.do?bindingPhone")
    Call<CommResponse> bindPhone(@Body JZ_Comm_bindphone request);

    @POST("SalvationPlatform/phoneLoginController.do?modifyPhone")
    Call<CommResponse> modifyPhone(@Body JZ_Comm_modifyPhone request);

    //修改资料
    @POST("SalvationPlatform/phoneLoginController.do?modifyPassword")
    Call<CommResponse> modifyPW(@Body ModifyPW request);

    //修改密码
    @POST("SalvationPlatform/phoneLoginController.do?modifyPersonalMsg")
    Call<CommResponse> modifyInfo(@Body JZ_Comm_modifyInfo request);

    /**************************救助系统申请人员*********************************/
    //登录申请人员
    @POST("api/Login/phoneLogin")
    Call<LoginSQR_Res> loginSQR(@Body LoginSQR_Req request);

    @POST("api/Register/RegisterUser")
    Call<CommResponse> registerSQR(@Body Register_Req request);

    @POST("api/Register/CheckSmsCode")
    Call<CommResponse> getCheckCodeSQR(@Body Comm_checkCode_sqr_Req request);

    @POST("api/Register/RequestMobileCode")
    Call<CommResponse> getCodeSQR(@Body Comm_getCode_Req request);

    @POST("api/UserCenter/modifyUserInformethon")
    Call<CommResponse> modifyUserInfoSQR(@Body Comm_modifyUserInfoSQR_Req request);

    @POST("api/UserCenter/bindingPhone")
    Call<CommResponse> bindingPhoneSQR(@Body Comm_SQR_bingphone_Req request);

    @POST("api/UserCenter/modifyPassword")
    Call<CommResponse> modifyPWSQR(@Body Comm_SQR_modifyPW_Req request);

    @POST("api/Login/findPassword")
    Call<CommResponse> findPWSQR(@Body Comm_SQR_findPW request);


    /**************************救助系统工作人员*********************************/

    @POST("SocietySalvation/api/appCommonController.do?getUserInfo")
    Call<JZ_GetUserInfo> loginJZ(@Body JZ_Comm_Req request);


    @POST("SocietySalvation/api/appTodolistController.do?appGetTodoFunctionTree")
    Call<JZ_Todo_MenuTree> getJZ_FuntionTree(@Body JZ_TODO_FuntionTree req);

    @POST("SocietySalvation/api/appTodolistController.do?appGetTodolist")
    Call<JZ_Todolist> getJZ_TodoList(@Body JZ_TODO_List req);

    @POST("SocietySalvation/api/appTodolistController.do?appGetApproval")
    Call<JZ_MSG_SP_Res> getJZ_MSG_SP_List(@Body JZ_Comm_list_Req req);

    @POST("SocietySalvation/api/appTodolistController.do?appGetRecheckInfos")
    Call<JZ_MSG_FC_Res> getJZ_MSG_FC_List(@Body JZ_Comm_list_Req req);

    //获取行政区划
    @POST("SocietySalvation/api/appDataqueryController.do?getRegion")
    Call<JZ_YWBL_DZDA_XZQH> getJZ_XZQHList(@Body RequestBody req);

    //获取救助人列表
    @POST("SocietySalvation/api/appDataqueryController.do?getSalvationList")
    Call<JZ_YWBL_DZDA_Salvation> getJZ_SalvationList(@Body JZ_YWBL_DZDA_SALVATION_RE req);

    //获取电子档案所有数据
    @POST("SocietySalvation/api/appDataqueryController?getAllData")
    Call<JZ_YWBL_DZDA_Family> getJZ_GetAllData(@Body JZ_YWBL_DZDA_FAMILY_RE req);


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


    //申请人-个人查询-进度查看
    @POST("appCheck/person/applyProgress.do?getApplyProgressList")
    Call<HD_SQR_GRCX_JDCK_RES> getApplyProgressList(@Body RequestBody body);


    //申请人-个人查询-申请信息查看
    @POST("appCheck/person/applyInfo.do?getApplyInfoList")
    Call<HD_SQR_GRCX_JDCK_RES> getApplyInfoList(@Body RequestBody body);

    //申请人-个人查询-申请信息查看-附件下载
    @POST("appCheck/person/downEnclosure.do?downEnclosure")
    Call<HD_SQR_GRCX_JDCK_RES> getDownEnclosureInfo(@Body RequestBody body);

    //申请人-个人查询-申请信息查看-报告下载
    @POST("appCheck/person/downReport.do?downReport")
    Call<HD_SQR_GRCX_JDCK_RES> getDownReport(@Body RequestBody body);

    //申请人-核对政策-核对政策
    @POST("appCheck/person/applyCheckPolityMaterial.do?getPolityMaterial")
    Call<HD_hdzc> getHDZCList(@Body RequestBody body);

    //申请人-消息通知-申请状态
    @POST("appCheck/person/applyStateTip.do?getApplyStateTipList")
    Call<HD_XXTZ> getXXTZList1(@Body RequestBody body);

    //申请人-消息通知-报告明细
    @POST("appCheck/person/applyReportTip.do?getApplyReportTipList")
    Call<HD_XXTZ> getXXTZList2(@Body RequestBody body);


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

    @POST("appCheck/worker/checkReport.do?getReportStatistics")
    Call<HD_TJ_HDDX> getTJ_HDbgzs(@Body RequestBody body);

    @POST("appCheck/worker/bussinessTrends.do?getBussinessTrendsStatistics")
    Call<HD_TJ_YWQS> getTJ_HDywqs(@Body RequestBody body);

    @POST("appCheck/worker/areaPersonTime.do?getAreaPersonStatistics")
    Call<HD_TJ_QYRC> getTJ_HDqyrc(@Body RequestBody body);

    @POST("appCheck/worker/sharedUnits.do?getSharedUnitsStatistics")
    Call<HD_TJ_GX> getTJ_HDgx(@Body RequestBody body);

    //核对资料
    @POST("appCheck/worker/checkPolicyMaterial.do?getPolicyMaterialList")
    Call<HD_hdzc> getHDZL1(@Body RequestBody body);

    @POST("appCheck/worker/confidentialMaterial.do?getConfidentialMaterial")
    Call<HD_hdzc> getHDZL2(@Body RequestBody body);


    @POST("appCheck/worker/checkBussinessMaterial.do?getBussinessMaterial")
    Call<HD_hdzc> getHDZL3(@Body RequestBody body);

    //消息通知
    @POST("appCheck/worker/todoTip.do?getTodoTipList")
    Call<HD_XXTZ> getHD_XXTZ1(@Body RequestBody body);

    @POST("appCheck/worker/reCheckTip.do?getRecheckTipList")
    Call<HD_XXTZ> getHD_XXTZ2(@Body RequestBody body);

    @POST("appCheck/worker/timeoutTip.do?getTimeoutTipList")
    Call<HD_XXTZ> getHD_XXTZ3(@Body RequestBody body);

    @POST("appCheck/worker/sensitiveTip.do?getSensitiveTipList")
    Call<HD_XXTZ> getHD_XXTZ4(@Body RequestBody body);

    //行政区划
    @POST("appCheck/worker/getOrgArea.do?getOrgArea")
    Call<HD_XZQH_Response> getHD_XZQHList(@Body RequestBody body);


}
