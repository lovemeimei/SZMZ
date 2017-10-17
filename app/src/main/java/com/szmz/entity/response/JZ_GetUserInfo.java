package com.szmz.entity.response;

import com.szmz.entity.IEntity;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/10/17 0017上午 10:34
 */

public class JZ_GetUserInfo extends BaseResponse<JZ_GetUserInfo.ResultBean>{

    public class ResultBean implements IEntity{

        /**
         * id : 8a8ab0b246dc81120146dc8181950052
         * departName : 四川省民政厅
         * address : 实施
         * realName : 管理员
         */

        private String id;
        private String departName;
        private String address;
        private String realName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDepartName() {
            return departName;
        }

        public void setDepartName(String departName) {
            this.departName = departName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }
    }
}
