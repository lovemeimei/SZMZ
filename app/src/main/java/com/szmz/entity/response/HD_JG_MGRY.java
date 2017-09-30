package com.szmz.entity.response;

import com.szmz.entity.IEntity;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/30 0030上午 10:07
 */

public class HD_JG_MGRY extends BaseResponse<HD_JG_MGRY.ResultBean>{

    public class ResultBean implements IEntity{

        /**
         * batchId : 100000001
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
