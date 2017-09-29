package com.szmz.entity;

import com.szmz.entity.response.BaseResponse;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/29 0029下午 1:43
 */

public class HD_JG_BGDY_RES extends BaseResponse<HD_JG_BGDY_RES.ResultBean>{

    public class ResultBean implements IEntity{

        /**
         * reportCode : 10000001
         * printTimes : 2
         * bizCategory : 城乡低保
         * printer : 张三
         * createTime : 2017-09-11 16:00:00
         * updateTime : 2017-09-11 16:00:00
         */

        private String reportCode;
        private int printTimes;
        private String bizCategory;
        private String printer;
        private String createTime;
        private String updateTime;

        public String getReportCode() {
            return reportCode;
        }

        public void setReportCode(String reportCode) {
            this.reportCode = reportCode;
        }

        public int getPrintTimes() {
            return printTimes;
        }

        public void setPrintTimes(int printTimes) {
            this.printTimes = printTimes;
        }

        public String getBizCategory() {
            return bizCategory;
        }

        public void setBizCategory(String bizCategory) {
            this.bizCategory = bizCategory;
        }

        public String getPrinter() {
            return printer;
        }

        public void setPrinter(String printer) {
            this.printer = printer;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
}
