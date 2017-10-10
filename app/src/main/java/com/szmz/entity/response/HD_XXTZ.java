package com.szmz.entity.response;

import com.szmz.entity.IEntity;

/**
 * @author qieyixuan
 * @created at 2017年10月08
 */

public class HD_XXTZ extends BaseResponse<HD_XXTZ.ReaultBean> {

    public class ReaultBean implements IEntity {

        /**
         * batchId : 10000001
         * batchName : 攀枝花城乡低保
         * bizCategory : 城乡低保
         * entrustTime : 2017-09-11
         * dealNode : 待审核
         * outTime : 剩余10小时10分
         */

        private String batchId;
        private String batchName;
        private String bizCategory;
        private String entrustTime;
        private String dealNode;
        private String outTime;
        private String applyId;
        private String applyName;
        private String idCardNo;

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

        public String getBatchId() {
            return batchId;
        }

        public void setBatchId(String batchId) {
            this.batchId = batchId;
        }

        public String getBatchName() {
            return batchName;
        }

        public void setBatchName(String batchName) {
            this.batchName = batchName;
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

        public String getDealNode() {
            return dealNode;
        }

        public void setDealNode(String dealNode) {
            this.dealNode = dealNode;
        }

        public String getOutTime() {
            return outTime;
        }

        public void setOutTime(String outTime) {
            this.outTime = outTime;
        }
    }
}
