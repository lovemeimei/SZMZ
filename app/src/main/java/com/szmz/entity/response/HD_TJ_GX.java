package com.szmz.entity.response;

/**
 * @author qieyixuan
 * @created at 2017年10月08
 */

public class HD_TJ_GX extends BaseResponse<HD_TJ_GX.ResultBean>{
    public class ResultBean{

        /**
         * shareName : 婚姻
         * count : 100
         */

        private String shareName;
        private int count;

        public String getShareName() {
            return shareName;
        }

        public void setShareName(String shareName) {
            this.shareName = shareName;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
