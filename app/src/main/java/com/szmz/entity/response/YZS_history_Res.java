package com.szmz.entity.response;

import com.szmz.entity.IEntity;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/8 0008下午 3:14
 */

public class YZS_history_Res extends BaseResponse<YZS_history_Res.ResultBean>{

    public class ResultBean implements IEntity{

        private String ADD_TIME;
        private String NAME;
        private String SEX_NAME;
        private String CARDID;//列表
        private String IDCARD;//详细
        private String RESCUE_CATEGORY_NAME;
        private String id;
        private String ADDRESS;
        private String TREATMENT_NAME;
        private String DESEASE_NAME;

        private String EXPENSE_MONEY;
        private String MED_REPAYABLE_MONEY;
        private String IN_HOSPITAL_DATE;
        private String LEAVE_HOSPITAL_DATE;


        public String getADD_TIME() {
            return ADD_TIME;
        }

        public void setADD_TIME(String ADD_TIME) {
            this.ADD_TIME = ADD_TIME;
        }

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }

        public String getSEX_NAME() {
            return SEX_NAME;
        }

        public void setSEX_NAME(String SEX_NAME) {
            this.SEX_NAME = SEX_NAME;
        }

        public String getCARDID() {
            return CARDID;
        }

        public void setCARDID(String CARDID) {
            this.CARDID = CARDID;
        }

        public String getIDCARD() {
            return IDCARD;
        }

        public void setIDCARD(String IDCARD) {
            this.IDCARD = IDCARD;
        }

        public String getRESCUE_CATEGORY_NAME() {
            return RESCUE_CATEGORY_NAME;
        }

        public void setRESCUE_CATEGORY_NAME(String RESCUE_CATEGORY_NAME) {
            this.RESCUE_CATEGORY_NAME = RESCUE_CATEGORY_NAME;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getADDRESS() {
            return ADDRESS;
        }

        public void setADDRESS(String ADDRESS) {
            this.ADDRESS = ADDRESS;
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
    }
}
