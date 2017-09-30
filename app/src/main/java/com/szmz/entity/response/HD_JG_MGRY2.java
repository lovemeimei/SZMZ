package com.szmz.entity.response;

import com.szmz.entity.IEntity;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/30 0030上午 10:08
 */

public class HD_JG_MGRY2 extends BaseResponse<HD_JG_MGRY2.ResultBean>{

    public class ResultBean implements IEntity{

        /**
         * batchId : 100000001
         * applyId : 100000001000
         * applyName : 张三
         * sex : 男
         * idCardNo : 610324
         * bizCategory : 城乡低保
         * entrustTime : 2017-09-11
         */

        private String batchId;
        private String applyId;
        private String applyName;
        private String sex;
        private String idCardNo;
        private String bizCategory;
        private String entrustTime;

        public String getBatchId() {
            return batchId;
        }

        public void setBatchId(String batchId) {
            this.batchId = batchId;
        }

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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getIdCardNo() {
            return idCardNo;
        }

        public void setIdCardNo(String idCardNo) {
            this.idCardNo = idCardNo;
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
