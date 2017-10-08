package com.szmz.entity.response;

import com.szmz.entity.IEntity;

/**
 * @author qieyixuan
 * @created at 2017年10月08
 */

public class HD_XXTZ extends BaseResponse<HD_XXTZ.ReaultBean>{

    public class ReaultBean implements IEntity{

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
