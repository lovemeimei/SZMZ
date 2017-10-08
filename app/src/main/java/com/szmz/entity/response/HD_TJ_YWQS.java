package com.szmz.entity.response;

/**
 * @author qieyixuan
 * @created at 2017年10月08
 */

public class HD_TJ_YWQS extends BaseResponse<HD_TJ_YWQS.ResultBean>{

    public class ResultBean{


        /**
         * directionTime : 2017-09
         * total : 1000
         * percent : 90.2
         */

        private String directionTime;
        private int total;
        private double percent;

        public String getDirectionTime() {
            return directionTime;
        }

        public void setDirectionTime(String directionTime) {
            this.directionTime = directionTime;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public double getPercent() {
            return percent;
        }

        public void setPercent(double percent) {
            this.percent = percent;
        }
    }
}
