package com.szmz.net;

import com.szmz.entity.HD_JG_BGDY_RES;
import com.szmz.entity.request.BaseRequest;
import com.szmz.entity.request.Comm_SQR_bingphone_Req;
import com.szmz.entity.request.Comm_SQR_findPW;
import com.szmz.entity.request.Comm_SQR_modifyPW_Req;
import com.szmz.entity.request.Comm_checkCode_sqr_Req;
import com.szmz.entity.request.Comm_getCode_Req;
import com.szmz.entity.request.Comm_ipid_req;
import com.szmz.entity.request.Comm_modifyUserInfoSQR_Req;
import com.szmz.entity.request.Comm_msg_req;
import com.szmz.entity.request.JZSQR_zjfflist_req;
import com.szmz.entity.request.JZ_Comm_Req;
import com.szmz.entity.request.JZ_Comm_bindphone;
import com.szmz.entity.request.JZ_Comm_list_Req;
import com.szmz.entity.request.JZ_Comm_modifyInfo;
import com.szmz.entity.request.JZ_Comm_modifyPhone;
import com.szmz.entity.request.JZ_DC_req;
import com.szmz.entity.request.JZ_Login_Code_Req;
import com.szmz.entity.request.JZ_SQR_HistoryDetailReq;
import com.szmz.entity.request.JZ_SQR_JD_DETAIL_RE;
import com.szmz.entity.request.JZ_SQR_JD_RE;
import com.szmz.entity.request.JZ_SQR_JD_XF_DETAIL_RE;
import com.szmz.entity.request.JZ_SQR_historyList_req;
import com.szmz.entity.request.JZ_Scan_QZ_Req;
import com.szmz.entity.request.JZ_Search_workerDetail_Req;
import com.szmz.entity.request.JZ_Search_worker_Req;
import com.szmz.entity.request.JZ_TODO_FuntionTree;
import com.szmz.entity.request.JZ_TODO_List;
import com.szmz.entity.request.JZ_Tj1_Req;
import com.szmz.entity.request.JZ_YWBL_ADDDATA_RE;
import com.szmz.entity.request.JZ_YWBL_DCHS_RE;
import com.szmz.entity.request.JZ_YWBL_DZDA_FAMILY_RE;
import com.szmz.entity.request.JZ_YWBL_DZDA_SALVATION_RE;
import com.szmz.entity.request.JZ_YWBL_DZDA_XZQH_RE;
import com.szmz.entity.request.JZ_YWBL_MZPY_RE;
import com.szmz.entity.request.JZ_YWBL_RHCC_RE;
import com.szmz.entity.request.JZ_YWBL_SHGS_RE;
import com.szmz.entity.request.JZ_YWBL_SPGS_RE;
import com.szmz.entity.request.LoginSQR_Req;
import com.szmz.entity.request.ModifyPW;
import com.szmz.entity.request.Register_Req;
import com.szmz.entity.request.YZSSQR_HomeList_Req;
import com.szmz.entity.request.YZSSQR_JD_Detail_Req;
import com.szmz.entity.request.YZSSQR_JZYE_Req;
import com.szmz.entity.request.YZSSQR_history_req;
import com.szmz.entity.request.YZS_History_Detail_Req;
import com.szmz.entity.request.YZS_History_List_Req;
import com.szmz.entity.request.YZS_SQR_jdlist_req;
import com.szmz.entity.request.YZS_TJ1_Req;
import com.szmz.entity.request.YZS_people_list_Req;
import com.szmz.entity.request.YZS_qh_req;
import com.szmz.entity.request.YZS_todoList_Req;
import com.szmz.entity.request.phoneLoginRequest;
import com.szmz.entity.response.CommResponse;
import com.szmz.entity.response.Comm_ipid_res;
import com.szmz.entity.response.Comm_msg_Res;
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
import com.szmz.entity.response.JZSQR_zjff_res;
import com.szmz.entity.response.JZ_Comm_JZLX_RES;
import com.szmz.entity.response.JZ_DC_Res;
import com.szmz.entity.response.JZ_GetUserInfo;
import com.szmz.entity.response.JZ_MSG_FC_Res;
import com.szmz.entity.response.JZ_MSG_SP_Res;
import com.szmz.entity.response.JZ_SQR_JD_DETAIL_RES;
import com.szmz.entity.response.JZ_SQR_JD_RES;
import com.szmz.entity.response.JZ_SQR_JD_XF_DETAIL_RES;
import com.szmz.entity.response.JZ_SQR_histroy_res;
import com.szmz.entity.response.JZ_Search_worker_Res;
import com.szmz.entity.response.JZ_Todo_MenuTree;
import com.szmz.entity.response.JZ_Todolist;
import com.szmz.entity.response.JZ_YWBL_DZDA_Family;
import com.szmz.entity.response.JZ_YWBL_DZDA_FamilyIncome;
import com.szmz.entity.response.JZ_YWBL_DZDA_FamilyMaterial;
import com.szmz.entity.response.JZ_YWBL_DZDA_FamilyMember;
import com.szmz.entity.response.JZ_YWBL_DZDA_FamilyProperty;
import com.szmz.entity.response.JZ_YWBL_DZDA_Salvation;
import com.szmz.entity.response.JZ_YWBL_DZDA_SupportIncome;
import com.szmz.entity.response.JZ_YWBL_DZDA_XZQH;
import com.szmz.entity.response.JZ_tj1;
import com.szmz.entity.response.LoginSQR_Res;
import com.szmz.entity.response.YZSSQR_HomeList_Res;
import com.szmz.entity.response.YZSSQR_JZYE_Res;
import com.szmz.entity.response.YZSSQR_jd_Res;
import com.szmz.entity.response.YZS_TJ1_Res;
import com.szmz.entity.response.YZS_TJ3_Res;
import com.szmz.entity.response.YZS_history_Res;
import com.szmz.entity.response.YZS_people_Res;
import com.szmz.entity.response.YZS_qh_res;
import com.szmz.entity.response.YZS_tj2_Res;
import com.szmz.entity.response.YZS_todoList_Res;
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
    //扫码登录救助
    @POST("SocietySalvation/loginController.do?appQuest")
    Call<CommResponse> loginCode(@Body JZ_Login_Code_Req request);

    //扫码签章
    @POST("SocietySalvation/bsprFamTempController.do?scanSeal")
    Call<CommResponse> scanQZ(@Body JZ_Scan_QZ_Req request);

    //统一登录
    @POST("SalvationPlatform/phoneLoginController.do?phoneLogin")
    Call<phoneLoginR> login(@Body phoneLoginRequest request);

    // http://222.222.49.34:8057/SalvationPlatform/phoneLoginController.do?getSystemIdentifyAll
    @POST("SalvationPlatform/phoneLoginController.do?getSystemIdentifyAll")
    Call<Comm_ipid_res> getIPSID(@Body Comm_ipid_req body);

    //    http://222.222.49.34:9095/SocietySalvation/api/appMessageController.do?appNotelist&LoginName=孔以丹&Phone=13233333333&IDCard=41022319730502258X&UserType=1&Md5Key=E393C8C925F5C0435D61A0ED232BE8B2
    //获取新消息
    @POST("SocietySalvation/api/appMessageController.do?appNotelist")
    Call<Comm_msg_Res> getMsg(@Body Comm_msg_req body);

    //工作人员绑定手机号
    @POST("SalvationPlatform/phoneLoginController.do?bindingPhone")
    Call<CommResponse> bindPhone(@Body JZ_Comm_bindphone request);

    @POST("SalvationPlatform/phoneLoginController.do?modifyPhone")
    Call<CommResponse> modifyPhone(@Body JZ_Comm_modifyPhone request);

    //修改密码
    @POST("SalvationPlatform/phoneLoginController.do?modifyPassword")
    Call<CommResponse> modifyPW(@Body ModifyPW request);

    //修改资料
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



    @POST("SocietySalvation/api/appDataqueryController.do?getHistoryListByUser")
    Call<JZ_SQR_histroy_res> JZSQR_historyList(@Body JZ_SQR_historyList_req request);
    @POST("SocietySalvation/api/appDataqueryController.do?getHistoryInfoByUser")
    Call<JZ_SQR_histroy_res> JZSQR_historyDetail(@Body JZ_SQR_HistoryDetailReq request);

    @POST("SocietySalvation/api/appDataqueryController.do?getGrantMoneyList")
    Call<JZSQR_zjff_res> JZSQR_zjff(@Body JZSQR_zjfflist_req request);
    /**************************救助系统工作人员*********************************/
    //得到救助类型列表
    @POST("SocietySalvation/api/appDataqueryController.do?getSalvationType")
    Call<JZ_Comm_JZLX_RES> getJZLX(@Body BaseRequest request);

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


    @POST("SocietySalvation/api/appDataqueryController.do?getFamilyInfo")
    Call<JZ_YWBL_DZDA_Family> getJZ_Family(@Body JZ_YWBL_DZDA_FAMILY_RE req);

    @POST("SocietySalvation/api/appDataqueryController?getFamilyproperty")
    Call<JZ_YWBL_DZDA_FamilyProperty> getJZ_Property(@Body JZ_YWBL_DZDA_FAMILY_RE req);

    @POST("SocietySalvation/api/appDataqueryController?getFamilymember")
    Call<JZ_YWBL_DZDA_FamilyMember> getJZ_FamilyMember(@Body JZ_YWBL_DZDA_FAMILY_RE req);

    @POST("SocietySalvation/api/appDataqueryController?getFamilyincome")
    Call<JZ_YWBL_DZDA_FamilyIncome> getJZ_FamilyIncome(@Body JZ_YWBL_DZDA_FAMILY_RE req);


    @POST("SocietySalvation/api/appDataqueryController?getFamilymaterialList")
    Call<JZ_YWBL_DZDA_FamilyMaterial> getJZ_FamilyMaterial(@Body JZ_YWBL_DZDA_FAMILY_RE req);

    @POST("SocietySalvation/api/appDataqueryController?getFamilymaterialList")
    Call<JZ_YWBL_DZDA_SupportIncome> getJZ_SupportIncome(@Body JZ_YWBL_DZDA_FAMILY_RE req);


    //救助信息查询
    @POST("SocietySalvation/api/appDataqueryController.do?getHistoryList")
    Call<JZ_Search_worker_Res> getJZ_SearchList(@Body JZ_Search_worker_Req req);

    //救助信息查询详细
    @POST("SocietySalvation/api/appDataqueryController.do?getHistoryInfo")
    Call<JZ_Search_worker_Res> getJZ_SearchList_Detail(@Body JZ_Search_workerDetail_Req req);

    //获取行政区划
    @POST("SocietySalvation/api/appDataqueryController.do?getRegion")
    Call<JZ_YWBL_DZDA_XZQH> getJZ_XZQHList(@Body JZ_YWBL_DZDA_XZQH_RE req);

    //救助对象信息-获取救助人列表
    @POST("SocietySalvation/api/appDataqueryController.do?getSalvationList")
    Call<JZ_YWBL_DZDA_Salvation> getJZ_SalvationList(@Body JZ_YWBL_DZDA_SALVATION_RE req);

    //救助对象信息-获取电子档案所有数据
    @POST("SocietySalvation/api/appDataqueryController.do?getAllData")
    Call<JZ_YWBL_DZDA_Family> getJZ_GetAllData(@Body JZ_YWBL_DZDA_FAMILY_RE req);

    //获取救助人列表
    @POST("SocietySalvation/api/appTempDataqueryController.do?getSalvationListTemp")
    Call<JZ_YWBL_DZDA_Salvation> getJZ_SalvationTempList(@Body JZ_YWBL_DZDA_SALVATION_RE req);

    //获取电子档案所有数据
    @POST("SocietySalvation/api/appTempDataqueryController.do?getAllTempData")
    Call<JZ_YWBL_DZDA_Family> getJZ_GetAllDataTemp(@Body JZ_YWBL_DZDA_FAMILY_RE req);

    //调查核实上报
    @POST("SocietySalvation/api/appCommentController.do?appAddStreetCheck")
    Call<CommResponse> getJZ_AddStreetCheck(@Body JZ_YWBL_DCHS_RE req);

    //民主评议
    @POST("SocietySalvation/api/appCommentController.do?appAddStreetComment")
    Call<CommResponse> getJZ_AddStreetComment(@Body JZ_YWBL_MZPY_RE req);

    //审核公示
    @POST("SocietySalvation/api/appCommentController.do?appAddStreetPublic")
    Call<CommResponse> getJZ_AddStreetPublic(@Body JZ_YWBL_SHGS_RE req);

    //入户抽查
    @POST("SocietySalvation/api/appCommentController.do?appAddCountySpot")
    Call<CommResponse> getJZ_AddCountySpot(@Body JZ_YWBL_RHCC_RE req);

    //审批公示
    @POST("SocietySalvation/api/appCommentController.do?appAddCountyPublic")
    Call<CommResponse> getJZ_AddCountyPublic(@Body JZ_YWBL_SPGS_RE req);

    //图片上传接口
    @POST("SocietySalvation/api/appCommentController.do?appAddData")
    Call<CommResponse> getJZ_AddData(@Body JZ_YWBL_ADDDATA_RE req);


    //申请人获取申请进度列表
    @POST("SocietySalvation/api/appTempDataqueryController.do?getApplicationList")
    Call<JZ_SQR_JD_RES> get_JZ_SQR_JD_LIST(@Body JZ_SQR_JD_RE req);

    //申请人获取申请进度详细
    @POST("SocietySalvation/api/appTempDataqueryController.do?getApplicationInfo")
    Call<JZ_SQR_JD_DETAIL_RES> get_JZ_SQR_JD_DETAIL(@Body JZ_SQR_JD_DETAIL_RE req);

    //申请人获取信访详细
    @POST("SocietySalvation/api/appDataqueryController.do?getVisitInfo")
    Call<JZ_SQR_JD_XF_DETAIL_RES> get_JZ_SQR_JD_XF_DETAIL(@Body JZ_SQR_JD_XF_DETAIL_RE req);


    //http://222.222.49.34:9095/SocietySalvation/api/appDataqueryController?getSalvationPernum
    @POST("SocietySalvation/api/appDataqueryController.do?getSalvationPernum")
    Call<JZ_tj1> getJZ_tj1(@Body JZ_Tj1_Req body);


    /**************************医疗一站式*********************************/
    @POST("SocietySalvation/api/appDataqueryController.do?getSalvationPernumByDisId")
    Call<JZ_tj1> getJZ_tj3(@Body JZ_Tj1_Req body);

    //    http://222.222.49.34:9095/SocietySalvation/api/appBusinessSuperviseController.do?appBusinessProgress
    @POST("SocietySalvation/api/appBusinessSuperviseController.do?appBusinessProgress")
    Call<JZ_DC_Res> getJZ_dc(@Body JZ_DC_req body);


    /**************************医疗一站式工作人员*********************************/

    //工作人员消息查询http://222.222.49.34: 8088/ActionControler/AppTodolist.ashx
    @POST("ActionControler/AppTodolist.ashx")
    Call<YZS_todoList_Res> getYZS_TodoList(@Body YZS_todoList_Req req);

    //http://222.222.49.34:8088/ActionControler/AppSalvationHistoryList.ashx
    @POST("ActionControler/AppSalvationHistoryList.ashx")
    Call<YZS_history_Res> getYZS_History_list(@Body YZS_History_List_Req req);

    @POST("ActionControler/appSalvationHistoryInfo.ashx")
    Call<YZS_history_Res> getYZS_History_detail(@Body YZS_History_Detail_Req req);

    @POST("ActionControler/appSalvationList.ashx")
    Call<YZS_people_Res> getYZS_people_list(@Body YZS_people_list_Req req);

    @POST("ActionControler/appSalvationInfo.ashx")
    Call<YZS_people_Res> getYZS_people_detail(@Body YZS_History_Detail_Req req);

    //
    @POST("ActionControler/AppRegionInfo.ashx")
    Call<YZS_qh_res> getYZS_xzqh(@Body YZS_qh_req req);

    //统计分析
    @POST("ActionControler/AppRescueStaticByFamily.ashx")
    Call<YZS_TJ1_Res> getYZS_tj1(@Body YZS_TJ1_Req req);

    @POST("ActionControler/AppInHospitalRescueByMoneyAndNum.ashx")
    Call<YZS_tj2_Res> getYZS_tj2(@Body YZS_TJ1_Req req);

    @POST("ActionControler/AppRescueMoneyByTime.ashx")
    Call<YZS_TJ3_Res> getYZS_tj3(@Body YZS_TJ1_Req req);


    /**************************医疗一站式申请人*********************************/
    @POST("ActionControler/AppMessageList.ashx")
    Call<YZSSQR_HomeList_Res> getYZS_homelist_SQR(@Body YZSSQR_HomeList_Req req);


    //进度查询
    @POST("ActionControler/AppSalvationProgressList.ashx")
    Call<YZSSQR_jd_Res> getYZS_jdlist_SQR(@Body YZS_SQR_jdlist_req req);
    @POST("ActionControler/AppSalvationProgressInfo.ashx")
    Call<YZSSQR_jd_Res> getYZS_jdDetail_SQR(@Body YZSSQR_JD_Detail_Req req);

    @POST("ActionControler/AppSpareSalvageMoney.ashx")
    Call<YZSSQR_JZYE_Res> getYZS_jzye(@Body YZSSQR_JZYE_Req req);


    @POST("ActionControler/AppUserSalvationHistoryList.ashx")
    Call<YZS_history_Res> getYZS_History_list_SQR(@Body YZSSQR_history_req req);

    @POST("ActionControler/appSalvationHistoryInfo.ashx")
    Call<YZS_history_Res> getYZS_History_detail_SQR(@Body YZS_History_Detail_Req req);

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
