package com.szmz.entity.response;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/24 0024下午 3:51
 */

public class YZS_TJ1_Res extends BaseResponse<YZS_TJ1_Res.ResultBean> {

    public class ResultBean {

        private String rescueType;
        private String FAMILY_RESCUE_NAME;
        private String FAMILY_NUM;

        public String getRescueType() {
            return rescueType;
        }

        public void setRescueType(String rescueType) {
            this.rescueType = rescueType;
        }

        public String getFAMILY_RESCUE_NAME() {
            return FAMILY_RESCUE_NAME;
        }

        public void setFAMILY_RESCUE_NAME(String FAMILY_RESCUE_NAME) {
            this.FAMILY_RESCUE_NAME = FAMILY_RESCUE_NAME;
        }

        public String getFAMILY_NUM() {
            return FAMILY_NUM;
        }

        public void setFAMILY_NUM(String FAMILY_NUM) {
            this.FAMILY_NUM = FAMILY_NUM;
        }
    }
}
