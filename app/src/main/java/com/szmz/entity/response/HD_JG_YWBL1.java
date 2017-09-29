package com.szmz.entity.response;

import com.szmz.entity.IEntity;

/**
 * @author qieyixuan
 * @created at 2017年09月29
 */

public class HD_JG_YWBL1 extends BaseResponse<HD_JG_YWBL1.ResultBean>{
    public class  ResultBean implements IEntity{


        /**
         * batchId : 10000001
         * batchName : 攀枝花城乡低保
         * bizCategory : 城乡低保
         * entrustTime : 2017-09-11
         * createTime : 2017-09-11 16:00:00
         */

        private String batchId;
        private String batchName;
        private String bizCategory;
        private String entrustTime;
        private String createTime;

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
    }
}
