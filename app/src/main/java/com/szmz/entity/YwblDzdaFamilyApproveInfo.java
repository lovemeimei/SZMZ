package com.szmz.entity;

/**
 * 审批信息
 * Created by bz on 2017/11/23.
 */

public class YwblDzdaFamilyApproveInfo implements IEntity {


    private String id;//审批ID
    private String familyId;//审批家庭ID
    private String headName;//审批户主姓名
    private String acceptedUserName;//审批受理人
    private String acceptedTime;//审批受理时间
    private String streetCheckTime;//街道调查核实日期
    private String streetCheckInfo;//街道调查核实情况
    private String streetCheckResult;//街道审核结果
    private String streetCheckUser;//街道调查核实人员
    private String streetCheckChargeUser;//街道调查核实负责人

    private String streetCommentTime;//民主评议日期
    private String streetCommentResult;//民主评议结果
    private String streetCommentUser;//民主评议人员
    private String streetCommentChargeUser;//民主评议负责人
    private String streetPublicStartTime;//民主评议公示开始时间
    private String streetPublicEndTime;//民主评议公示截止时间
    private String streetPublicResult;//民主评议公示结果
    private String streetPublicUser;//公示记录人
    private String streetPublicObjectionInfo;//公示异议内容
    private String streetSubmitTime;//街道上报日期
    private String streetSubmitUser;//街道上报人员
    private String streetApproveReason;//街道审核结果原因
    private String streetApproveChargeUser;//街道审核负责人
    private String streetApproveInfo;//街道审核原因

    private String countyCheckMaterialResult;//区县材料审查结果
    private String countyCheckMaterialTime;//区县材料审查日期
    private String countyCheckMaterialUser;//区县材料审查人员

    private String countyApproveUser;//区县审批人员
    private String countyApproveTime;//区县审批决定日期
    private String countyApproveChargeUser;//区县审批负责人
    private String countyApproveInfoContent;//区县审批意见


    private String countySpotCheckUser;//区县入户抽查人员
    private String countySpotCheckTime;//区县入户抽查日期
    private String countySpotCheckResult;//区县入户抽查结果
    private String countySpotCheckChargeUser;//区县抽查负责人
    private String countyPublicStartTime;//区县审批公示起始日期

    private String countyPublicEndTime;//区县审批公示截止日期
    private String countyPublicResult;//区县审批公示结果
    private String countyPublicUser;//区县审批公示记录人员
    private String countyPublicObjectionInfo;//区县审批公示异议内容


    public String getStreetApproveInfo() {
        return streetApproveInfo;
    }

    public void setStreetApproveInfo(String streetApproveInfo) {
        this.streetApproveInfo = streetApproveInfo;
    }

    public String getCountySpotCheckChargeUser() {
        return countySpotCheckChargeUser;
    }

    public void setCountySpotCheckChargeUser(String countySpotCheckChargeUser) {
        this.countySpotCheckChargeUser = countySpotCheckChargeUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFamilyId() {
        return familyId;
    }

    public void setFamilyId(String familyId) {
        this.familyId = familyId;
    }

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public String getAcceptedUserName() {
        return acceptedUserName;
    }

    public void setAcceptedUserName(String acceptedUserName) {
        this.acceptedUserName = acceptedUserName;
    }

    public String getAcceptedTime() {
        return acceptedTime;
    }

    public void setAcceptedTime(String acceptedTime) {
        this.acceptedTime = acceptedTime;
    }

    public String getStreetCheckTime() {
        return streetCheckTime;
    }

    public void setStreetCheckTime(String streetCheckTime) {
        this.streetCheckTime = streetCheckTime;
    }

    public String getStreetCheckInfo() {
        return streetCheckInfo;
    }

    public void setStreetCheckInfo(String streetCheckInfo) {
        this.streetCheckInfo = streetCheckInfo;
    }

    public String getStreetCheckResult() {
        return streetCheckResult;
    }

    public void setStreetCheckResult(String streetCheckResult) {
        this.streetCheckResult = streetCheckResult;
    }

    public String getStreetCheckUser() {
        return streetCheckUser;
    }

    public void setStreetCheckUser(String streetCheckUser) {
        this.streetCheckUser = streetCheckUser;
    }

    public String getStreetCheckChargeUser() {
        return streetCheckChargeUser;
    }

    public void setStreetCheckChargeUser(String streetCheckChargeUser) {
        this.streetCheckChargeUser = streetCheckChargeUser;
    }

    public String getStreetCommentTime() {
        return streetCommentTime;
    }

    public void setStreetCommentTime(String streetCommentTime) {
        this.streetCommentTime = streetCommentTime;
    }

    public String getStreetCommentResult() {
        return streetCommentResult;
    }

    public void setStreetCommentResult(String streetCommentResult) {
        this.streetCommentResult = streetCommentResult;
    }

    public String getStreetCommentUser() {
        return streetCommentUser;
    }

    public void setStreetCommentUser(String streetCommentUser) {
        this.streetCommentUser = streetCommentUser;
    }

    public String getStreetCommentChargeUser() {
        return streetCommentChargeUser;
    }

    public void setStreetCommentChargeUser(String streetCommentChargeUser) {
        this.streetCommentChargeUser = streetCommentChargeUser;
    }

    public String getStreetPublicStartTime() {
        return streetPublicStartTime;
    }

    public void setStreetPublicStartTime(String streetPublicStartTime) {
        this.streetPublicStartTime = streetPublicStartTime;
    }

    public String getStreetPublicEndTime() {
        return streetPublicEndTime;
    }

    public void setStreetPublicEndTime(String streetPublicEndTime) {
        this.streetPublicEndTime = streetPublicEndTime;
    }

    public String getStreetPublicResult() {
        return streetPublicResult;
    }

    public void setStreetPublicResult(String streetPublicResult) {
        this.streetPublicResult = streetPublicResult;
    }

    public String getStreetPublicUser() {
        return streetPublicUser;
    }

    public void setStreetPublicUser(String streetPublicUser) {
        this.streetPublicUser = streetPublicUser;
    }

    public String getStreetPublicObjectionInfo() {
        return streetPublicObjectionInfo;
    }

    public void setStreetPublicObjectionInfo(String streetPublicObjectionInfo) {
        this.streetPublicObjectionInfo = streetPublicObjectionInfo;
    }

    public String getStreetSubmitTime() {
        return streetSubmitTime;
    }

    public void setStreetSubmitTime(String streetSubmitTime) {
        this.streetSubmitTime = streetSubmitTime;
    }

    public String getStreetSubmitUser() {
        return streetSubmitUser;
    }

    public void setStreetSubmitUser(String streetSubmitUser) {
        this.streetSubmitUser = streetSubmitUser;
    }

    public String getStreetApproveReason() {
        return streetApproveReason;
    }

    public void setStreetApproveReason(String streetApproveReason) {
        this.streetApproveReason = streetApproveReason;
    }

    public String getStreetApproveChargeUser() {
        return streetApproveChargeUser;
    }

    public void setStreetApproveChargeUser(String streetApproveChargeUser) {
        this.streetApproveChargeUser = streetApproveChargeUser;
    }

    public String getCountyCheckMaterialResult() {
        return countyCheckMaterialResult;
    }

    public void setCountyCheckMaterialResult(String countyCheckMaterialResult) {
        this.countyCheckMaterialResult = countyCheckMaterialResult;
    }

    public String getCountyCheckMaterialTime() {
        return countyCheckMaterialTime;
    }

    public void setCountyCheckMaterialTime(String countyCheckMaterialTime) {
        this.countyCheckMaterialTime = countyCheckMaterialTime;
    }

    public String getCountyCheckMaterialUser() {
        return countyCheckMaterialUser;
    }

    public void setCountyCheckMaterialUser(String countyCheckMaterialUser) {
        this.countyCheckMaterialUser = countyCheckMaterialUser;
    }

    public String getCountyApproveUser() {
        return countyApproveUser;
    }

    public void setCountyApproveUser(String countyApproveUser) {
        this.countyApproveUser = countyApproveUser;
    }

    public String getCountyApproveTime() {
        return countyApproveTime;
    }

    public void setCountyApproveTime(String countyApproveTime) {
        this.countyApproveTime = countyApproveTime;
    }

    public String getCountyApproveChargeUser() {
        return countyApproveChargeUser;
    }

    public void setCountyApproveChargeUser(String countyApproveChargeUser) {
        this.countyApproveChargeUser = countyApproveChargeUser;
    }

    public String getCountyApproveInfoContent() {
        return countyApproveInfoContent;
    }

    public void setCountyApproveInfoContent(String countyApproveInfoContent) {
        this.countyApproveInfoContent = countyApproveInfoContent;
    }

    public String getCountySpotCheckUser() {
        return countySpotCheckUser;
    }

    public void setCountySpotCheckUser(String countySpotCheckUser) {
        this.countySpotCheckUser = countySpotCheckUser;
    }

    public String getCountySpotCheckTime() {
        return countySpotCheckTime;
    }

    public void setCountySpotCheckTime(String countySpotCheckTime) {
        this.countySpotCheckTime = countySpotCheckTime;
    }

    public String getCountySpotCheckResult() {
        return countySpotCheckResult;
    }

    public void setCountySpotCheckResult(String countySpotCheckResult) {
        this.countySpotCheckResult = countySpotCheckResult;
    }

    public String getCountyPublicStartTime() {
        return countyPublicStartTime;
    }

    public void setCountyPublicStartTime(String countyPublicStartTime) {
        this.countyPublicStartTime = countyPublicStartTime;
    }

    public String getCountyPublicEndTime() {
        return countyPublicEndTime;
    }

    public void setCountyPublicEndTime(String countyPublicEndTime) {
        this.countyPublicEndTime = countyPublicEndTime;
    }

    public String getCountyPublicResult() {
        return countyPublicResult;
    }

    public void setCountyPublicResult(String countyPublicResult) {
        this.countyPublicResult = countyPublicResult;
    }

    public String getCountyPublicUser() {
        return countyPublicUser;
    }

    public void setCountyPublicUser(String countyPublicUser) {
        this.countyPublicUser = countyPublicUser;
    }

    public String getCountyPublicObjectionInfo() {
        return countyPublicObjectionInfo;
    }

    public void setCountyPublicObjectionInfo(String countyPublicObjectionInfo) {
        this.countyPublicObjectionInfo = countyPublicObjectionInfo;
    }
}
