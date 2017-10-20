package com.szmz.entity.response;

import com.szmz.entity.IEntity;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/10/18 0018上午 10:21
 */

public class JZ_MSG_FC_Res extends BaseResponse<JZ_MSG_FC_Res.ResultBean>{

    public class ResultBean implements IEntity{
        private String username;
        private String useridcard;
        private String remark;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUseridcard() {
            return useridcard;
        }

        public void setUseridcard(String useridcard) {
            this.useridcard = useridcard;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
