package com.szmz.entity.response;

import com.szmz.entity.IEntity;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/29 0029下午 5:08
 */

public class YZSSQR_JZYE_Res extends BaseResponse<YZSSQR_JZYE_Res.ResultBean>{

    public class ResultBean implements IEntity{

        private String Adminstrative_NAME;
        private String AccountAdminstrative_NAME;
        private String NAME;
        private String PERSON_ASSORT_NAME;
        private String IDCARD;
        private String ANNUAL;
        private String CATEGORY_NAME;
        private String THIS_YEAR_BALANCE;
        private String id;

        public String getTOTAL_EXPENSE() {
            return TOTAL_EXPENSE;
        }

        public void setTOTAL_EXPENSE(String TOTAL_EXPENSE) {
            this.TOTAL_EXPENSE = TOTAL_EXPENSE;
        }

        private String TOTAL_EXPENSE;



        public String getAdminstrative_NAME() {
            return Adminstrative_NAME;
        }

        public void setAdminstrative_NAME(String adminstrative_NAME) {
            Adminstrative_NAME = adminstrative_NAME;
        }

        public String getAccountAdminstrative_NAME() {
            return AccountAdminstrative_NAME;
        }

        public void setAccountAdminstrative_NAME(String accountAdminstrative_NAME) {
            AccountAdminstrative_NAME = accountAdminstrative_NAME;
        }

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }

        public String getPERSON_ASSORT_NAME() {
            return PERSON_ASSORT_NAME;
        }

        public void setPERSON_ASSORT_NAME(String PERSON_ASSORT_NAME) {
            this.PERSON_ASSORT_NAME = PERSON_ASSORT_NAME;
        }

        public String getIDCARD() {
            return IDCARD;
        }

        public void setIDCARD(String IDCARD) {
            this.IDCARD = IDCARD;
        }

        public String getANNUAL() {
            return ANNUAL;
        }

        public void setANNUAL(String ANNUAL) {
            this.ANNUAL = ANNUAL;
        }

        public String getCATEGORY_NAME() {
            return CATEGORY_NAME;
        }

        public void setCATEGORY_NAME(String CATEGORY_NAME) {
            this.CATEGORY_NAME = CATEGORY_NAME;
        }

        public String getTHIS_YEAR_BALANCE() {
            return THIS_YEAR_BALANCE;
        }

        public void setTHIS_YEAR_BALANCE(String THIS_YEAR_BALANCE) {
            this.THIS_YEAR_BALANCE = THIS_YEAR_BALANCE;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
