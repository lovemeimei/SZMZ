package com.szmz.entity.response;

import com.szmz.entity.IEntity;

/**
 * @author qieyixuan
 * @created at 2017年09月30
 */

public class HD_JG_YCCL extends BaseResponse<HD_JG_YCCL.ResultBean>{

    public class ResultBean implements IEntity{


        /**
         * warningName : 打印频繁
         * bizCategory : 城乡低保
         * warning : 用户：65010201 天山区审核员 操作报告： 第6501022017000007210F0号
         * warningTime : 2017-09-11 16:00:00
         * source : 用户：65010201 天山区审核员 操作报告
         */

        private String warningName;
        private String bizCategory;
        private String warning;
        private String warningTime;
        private String source;

        public String getWarningName() {
            return warningName;
        }

        public void setWarningName(String warningName) {
            this.warningName = warningName;
        }

        public String getBizCategory() {
            return bizCategory;
        }

        public void setBizCategory(String bizCategory) {
            this.bizCategory = bizCategory;
        }

        public String getWarning() {
            return warning;
        }

        public void setWarning(String warning) {
            this.warning = warning;
        }

        public String getWarningTime() {
            return warningTime;
        }

        public void setWarningTime(String warningTime) {
            this.warningTime = warningTime;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }
    }
}
