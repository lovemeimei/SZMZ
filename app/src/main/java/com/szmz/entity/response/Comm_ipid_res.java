package com.szmz.entity.response;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/22 0022下午 3:02
 */

public class Comm_ipid_res extends BaseResponse<Comm_ipid_res.ResultBean>{

    public class ResultBean{
        private String ip;
        private String result;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }
}
