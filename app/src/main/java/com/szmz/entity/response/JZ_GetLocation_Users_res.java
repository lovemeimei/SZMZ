package com.szmz.entity.response;

import com.szmz.entity.IEntity;

public class JZ_GetLocation_Users_res extends BaseResponse<JZ_GetLocation_Users_res.ResultBean>{

    public class ResultBean implements IEntity{

        /**
         * checkName : ywsq
         * realName : 燕窝社区
         * checkUserId : 8a8a80236006b253016006c9fa7e000e
         */

        private String checkName;
        private String realName;
        private String checkUserId;

        public String getCheckName() {
            return checkName;
        }

        public void setCheckName(String checkName) {
            this.checkName = checkName;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getCheckUserId() {
            return checkUserId;
        }

        public void setCheckUserId(String checkUserId) {
            this.checkUserId = checkUserId;
        }
    }
}
