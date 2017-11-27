package com.szmz.entity.response;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/24 0024下午 3:53
 */

public class YZS_tj2_Res extends BaseResponse<YZS_tj2_Res.ResultBean>{

    public class ResultBean{

        private String INPATIENT_TYPE_NAME;
        private String REAL_RESCUE_MONEY;
        private String Num;

        public String getINPATIENT_TYPE_NAME() {
            return INPATIENT_TYPE_NAME;
        }

        public void setINPATIENT_TYPE_NAME(String INPATIENT_TYPE_NAME) {
            this.INPATIENT_TYPE_NAME = INPATIENT_TYPE_NAME;
        }

        public String getREAL_RESCUE_MONEY() {
            return REAL_RESCUE_MONEY;
        }

        public void setREAL_RESCUE_MONEY(String REAL_RESCUE_MONEY) {
            this.REAL_RESCUE_MONEY = REAL_RESCUE_MONEY;
        }

        public String getNum() {
            return Num;
        }

        public void setNum(String num) {
            Num = num;
        }
    }
}
