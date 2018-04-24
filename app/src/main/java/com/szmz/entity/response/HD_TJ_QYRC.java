package com.szmz.entity.response;

/**
 * @author qieyixuan
 * @created at 2017年10月08
 */

public class HD_TJ_QYRC extends BaseResponse<HD_TJ_QYRC.ResultBean>{

    public class ResultBean{

        /**
         * areaName : 攀枝花
         * percent : 100
         */

        private String areaName;
        private double percent;

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public double getPercent() {
            return percent;
        }

        public void setPercent(int percent) {
            this.percent = percent;
        }
    }
}
