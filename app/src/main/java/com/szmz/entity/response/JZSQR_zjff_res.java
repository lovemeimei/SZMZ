package com.szmz.entity.response;

import com.szmz.entity.IEntity;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/12/1 0001下午 3:09
 */

public class JZSQR_zjff_res extends BaseResponse<JZSQR_zjff_res.ResultBean>{

    public class ResultBean implements IEntity{


        /**
         * grantMode : 手工发放
         * rescueType : 未知数值
         * typeTag : 否
         * ensureMoney : 650
         * grantDate : 2018年1月
         * personNum : 1
         * reason : 资金未到账
         * grantType : 正常发放
         * grantStatus : 已发放
         */

        private String grantMode;
        private String rescueType;
        private String typeTag;
        private int ensureMoney;
        private String grantDate;
        private int personNum;
        private String reason;
        private String grantType;
        private String grantStatus;

        public String getGrantMode() {
            return grantMode;
        }

        public void setGrantMode(String grantMode) {
            this.grantMode = grantMode;
        }

        public String getRescueType() {
            return rescueType;
        }

        public void setRescueType(String rescueType) {
            this.rescueType = rescueType;
        }

        public String getTypeTag() {
            return typeTag;
        }

        public void setTypeTag(String typeTag) {
            this.typeTag = typeTag;
        }

        public int getEnsureMoney() {
            return ensureMoney;
        }

        public void setEnsureMoney(int ensureMoney) {
            this.ensureMoney = ensureMoney;
        }

        public String getGrantDate() {
            return grantDate;
        }

        public void setGrantDate(String grantDate) {
            this.grantDate = grantDate;
        }

        public int getPersonNum() {
            return personNum;
        }

        public void setPersonNum(int personNum) {
            this.personNum = personNum;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getGrantType() {
            return grantType;
        }

        public void setGrantType(String grantType) {
            this.grantType = grantType;
        }

        public String getGrantStatus() {
            return grantStatus;
        }

        public void setGrantStatus(String grantStatus) {
            this.grantStatus = grantStatus;
        }
    }
}
