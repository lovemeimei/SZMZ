package com.szmz.entity.response;

import com.szmz.entity.IEntity;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/8 0008下午 3:14
 */

public class YZS_people_Res extends BaseResponse<YZS_people_Res.ResultBean>{

    public class ResultBean implements IEntity{

        private String REGIONALISM_NAME;
        private String NAME;
        private String IDCARD;//列表
        private String APPLY_DATE;
        private String ID;
        private String ADDRESS;
        private String ADD_TIME;
        private String CARDID;//详细

        private String RESCUE_CATEGORY_NAME;
        private String EXPENSE_MONEY;
        private String MED_REPAYABLE_MONEY;

        public String getREGIONALISM_NAME() {
            return REGIONALISM_NAME;
        }

        public void setREGIONALISM_NAME(String REGIONALISM_NAME) {
            this.REGIONALISM_NAME = REGIONALISM_NAME;
        }

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }

        public String getIDCARD() {
            return IDCARD;
        }

        public void setIDCARD(String IDCARD) {
            this.IDCARD = IDCARD;
        }

        public String getAPPLY_DATE() {
            return APPLY_DATE;
        }

        public void setAPPLY_DATE(String APPLY_DATE) {
            this.APPLY_DATE = APPLY_DATE;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getADDRESS() {
            return ADDRESS;
        }

        public void setADDRESS(String ADDRESS) {
            this.ADDRESS = ADDRESS;
        }

        public String getADD_TIME() {
            return ADD_TIME;
        }

        public void setADD_TIME(String ADD_TIME) {
            this.ADD_TIME = ADD_TIME;
        }

        public String getCARDID() {
            return CARDID;
        }

        public void setCARDID(String CARDID) {
            this.CARDID = CARDID;
        }

        public String getRESCUE_CATEGORY_NAME() {
            return RESCUE_CATEGORY_NAME;
        }

        public void setRESCUE_CATEGORY_NAME(String RESCUE_CATEGORY_NAME) {
            this.RESCUE_CATEGORY_NAME = RESCUE_CATEGORY_NAME;
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
    }
}
