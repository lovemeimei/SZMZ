package com.szmz.entity.response;

import com.szmz.entity.IEntity;

/**
 * @author qieyixuan
 * @created at 2017年09月29
 */

public class HD_JG_YWBL2 extends BaseResponse<HD_JG_YWBL2.ResultBean>{
    public class  ResultBean implements IEntity{


        /**
         * applyId : 10000001
         * applyName : 张三
         * idCardNo : 610324
         * sex : 男
         * bizCategory : 城乡低保
         * entrustTime : 2017-09-11
         */

        private String applyId;
        private String applyName;
        private String idCardNo;
        private String sex;
        private String bizCategory;
        private String entrustTime;

        public String getApplyId() {
            return applyId;
        }

        public void setApplyId(String applyId) {
            this.applyId = applyId;
        }

        public String getApplyName() {
            return applyName;
        }

        public void setApplyName(String applyName) {
            this.applyName = applyName;
        }

        public String getIdCardNo() {
            return idCardNo;
        }

        public void setIdCardNo(String idCardNo) {
            this.idCardNo = idCardNo;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBizCategory() {
            return bizCategory;
        }

        public void setBizCategory(String bizCategory) {
            this.bizCategory = bizCategory;
        }

        public String getEntrustTime() {
            return entrustTime;
        }

        public void setEntrustTime(String entrustTime) {
            this.entrustTime = entrustTime;
        }
    }
}
