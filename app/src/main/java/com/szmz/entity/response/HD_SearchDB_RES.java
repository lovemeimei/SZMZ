package com.szmz.entity.response;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/27 0027下午 4:36
 */

public class HD_SearchDB_RES extends BaseResponse<HD_SearchDB_RES.ResultBean>{


    public static class ResultBean {
        /**
         * batchId : 10000001
         * batchName : 攀枝花城乡低保
         * bizCategory : 城乡低保
         * entrustTime : 2017-09-11
         * createTime : 2017-09-11
         * dealNode : 待审核
         * total : 100
         * handledCount : 90
         */

        private String batchId;
        private String batchName;
        private String bizCategory;
        private String entrustTime;
        private String createTime;
        private String dealNode;
        private int total;
        private int handledCount;

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

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDealNode() {
            return dealNode;
        }

        public void setDealNode(String dealNode) {
            this.dealNode = dealNode;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getHandledCount() {
            return handledCount;
        }

        public void setHandledCount(int handledCount) {
            this.handledCount = handledCount;
        }
    }

}
