package com.szmz.entity.response;

import com.szmz.entity.IEntity;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/29 0029下午 3:21
 */

public class YZSSQR_jd_Res extends BaseResponse<YZSSQR_jd_Res.ResultBean>{

    public class ResultBean implements IEntity{
        private String NAME;
        private String type;
        private String typeName;
        private String FLOW_RESULT;
        private String FLOW_RESULT_NAME;
        private String ID;
        private String ApplicationNo;
        private String approvalOpinion;
        private String ADD_TIME;

        private String sexName;

        private String TREATMENT_NAME;
        private String DESEASE_NAME;

        public String getINSURE_CATEGORY() {
            return INSURE_CATEGORY;
        }

        public void setINSURE_CATEGORY(String INSURE_CATEGORY) {
            this.INSURE_CATEGORY = INSURE_CATEGORY;
        }

        private String INSURE_CATEGORY;

        private String EXPENSE_MONEY;
        private String MED_REPAYABLE_MONEY;
        private String IN_HOSPITAL_DATE;
        private String LEAVE_HOSPITAL_DATE;

        private String INPATIENT_TYPE_NAME;
        private String TREATMENT_LEAVEL;
        private String COMPENCATE_MONEY;
        private String START_PAY_MONEY;
        private String DISEASE_PAY_MONEY;
        private String SALVATION_MONEY;
        private String RESCUE_PERCENT;
        private String REAL_RESCUE_MONEY;
        private String SELF_PAY_MONEY;
        private String REMARK;

        public String getSexName() {
            return sexName;
        }

        public void setSexName(String sexName) {
            this.sexName = sexName;
        }

        public String getTREATMENT_NAME() {
            return TREATMENT_NAME;
        }

        public void setTREATMENT_NAME(String TREATMENT_NAME) {
            this.TREATMENT_NAME = TREATMENT_NAME;
        }

        public String getDESEASE_NAME() {
            return DESEASE_NAME;
        }

        public void setDESEASE_NAME(String DESEASE_NAME) {
            this.DESEASE_NAME = DESEASE_NAME;
        }

        public String getEXPENSE_MONEY() {
            return EXPENSE_MONEY;
        }

        public void setEXPENSE_MONEY(String EXPENSE_MONEY) {
            this.EXPENSE_MONEY = EXPENSE_MONEY;
        }

        public String getMED_REPAYABLE_MONEY() {
            return MED_REPAYABLE_MONEY;
        }

        public void setMED_REPAYABLE_MONEY(String MED_REPAYABLE_MONEY) {
            this.MED_REPAYABLE_MONEY = MED_REPAYABLE_MONEY;
        }

        public String getIN_HOSPITAL_DATE() {
            return IN_HOSPITAL_DATE;
        }

        public void setIN_HOSPITAL_DATE(String IN_HOSPITAL_DATE) {
            this.IN_HOSPITAL_DATE = IN_HOSPITAL_DATE;
        }

        public String getLEAVE_HOSPITAL_DATE() {
            return LEAVE_HOSPITAL_DATE;
        }

        public void setLEAVE_HOSPITAL_DATE(String LEAVE_HOSPITAL_DATE) {
            this.LEAVE_HOSPITAL_DATE = LEAVE_HOSPITAL_DATE;
        }

        public String getINPATIENT_TYPE_NAME() {
            return INPATIENT_TYPE_NAME;
        }

        public void setINPATIENT_TYPE_NAME(String INPATIENT_TYPE_NAME) {
            this.INPATIENT_TYPE_NAME = INPATIENT_TYPE_NAME;
        }

        public String getTREATMENT_LEAVEL() {
            return TREATMENT_LEAVEL;
        }

        public void setTREATMENT_LEAVEL(String TREATMENT_LEAVEL) {
            this.TREATMENT_LEAVEL = TREATMENT_LEAVEL;
        }

        public String getCOMPENCATE_MONEY() {
            return COMPENCATE_MONEY;
        }

        public void setCOMPENCATE_MONEY(String COMPENCATE_MONEY) {
            this.COMPENCATE_MONEY = COMPENCATE_MONEY;
        }

        public String getSTART_PAY_MONEY() {
            return START_PAY_MONEY;
        }

        public void setSTART_PAY_MONEY(String START_PAY_MONEY) {
            this.START_PAY_MONEY = START_PAY_MONEY;
        }

        public String getDISEASE_PAY_MONEY() {
            return DISEASE_PAY_MONEY;
        }

        public void setDISEASE_PAY_MONEY(String DISEASE_PAY_MONEY) {
            this.DISEASE_PAY_MONEY = DISEASE_PAY_MONEY;
        }

        public String getSALVATION_MONEY() {
            return SALVATION_MONEY;
        }

        public void setSALVATION_MONEY(String SALVATION_MONEY) {
            this.SALVATION_MONEY = SALVATION_MONEY;
        }

        public String getRESCUE_PERCENT() {
            return RESCUE_PERCENT;
        }

        public void setRESCUE_PERCENT(String RESCUE_PERCENT) {
            this.RESCUE_PERCENT = RESCUE_PERCENT;
        }

        public String getREAL_RESCUE_MONEY() {
            return REAL_RESCUE_MONEY;
        }

        public void setREAL_RESCUE_MONEY(String REAL_RESCUE_MONEY) {
            this.REAL_RESCUE_MONEY = REAL_RESCUE_MONEY;
        }

        public String getSELF_PAY_MONEY() {
            return SELF_PAY_MONEY;
        }

        public void setSELF_PAY_MONEY(String SELF_PAY_MONEY) {
            this.SELF_PAY_MONEY = SELF_PAY_MONEY;
        }

        public String getREMARK() {
            return REMARK;
        }

        public void setREMARK(String REMARK) {
            this.REMARK = REMARK;
        }

        public String getADD_TIME() {
            return ADD_TIME;
        }

        public void setADD_TIME(String ADD_TIME) {
            this.ADD_TIME = ADD_TIME;
        }

        public String getApprovalOpinion() {
            return approvalOpinion;
        }

        public void setApprovalOpinion(String approvalOpinion) {
            this.approvalOpinion = approvalOpinion;
        }

        public String getApplicationNo() {
            return ApplicationNo;
        }

        public void setApplicationNo(String applicationNo) {
            ApplicationNo = applicationNo;
        }

        private String CardID;
        private String currentNode;
        private String allNode;

        public String getCardID() {
            return CardID;
        }

        public void setCardID(String cardID) {
            CardID = cardID;
        }

        public String getCurrentNode() {
            return currentNode;
        }

        public void setCurrentNode(String currentNode) {
            this.currentNode = currentNode;
        }

        public String getAllNode() {
            return allNode;
        }

        public void setAllNode(String allNode) {
            this.allNode = allNode;
        }

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getFLOW_RESULT() {
            return FLOW_RESULT;
        }

        public void setFLOW_RESULT(String FLOW_RESULT) {
            this.FLOW_RESULT = FLOW_RESULT;
        }

        public String getFLOW_RESULT_NAME() {
            return FLOW_RESULT_NAME;
        }

        public void setFLOW_RESULT_NAME(String FLOW_RESULT_NAME) {
            this.FLOW_RESULT_NAME = FLOW_RESULT_NAME;
        }

        public String getId() {
            return ID;
        }

        public void setId(String id) {
            this.ID = id;
        }


    }

}


