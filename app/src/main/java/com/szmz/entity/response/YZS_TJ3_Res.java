package com.szmz.entity.response;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/24 0024下午 3:56
 */

public class YZS_TJ3_Res extends BaseResponse<YZS_TJ3_Res.ResultBean>{

    public class ResultBean{
        private String type;
        private String money;

        public ResultBean(String type, String money) {
            this.type = type;
            this.money = money;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }
}
