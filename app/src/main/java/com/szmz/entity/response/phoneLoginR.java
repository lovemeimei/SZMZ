package com.szmz.entity.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/21 0021下午 3:25
 */

public class phoneLoginR extends BaseResponse<phoneLoginR.ResultBean>{

    public static class ResultBean {
        /**
         * systemMsg : {"realName":null,"account":"sysadmin","systemID":"8a8a800b5e029817015e02c9106d0010"}
         * personal : {"orgCode":null,"realName":"管理员","mobilePhone":null,"emaile":null,"departName":null,"officePhone":null}
         */

        private List<SystemMsgBean> systemMsg;
        private PersonalBean personal;

        public List<SystemMsgBean>  getSystemMsg() {
            return systemMsg;
        }

        public void setSystemMsg(List<SystemMsgBean>
                                         systemMsg) {
            this.systemMsg = systemMsg;
        }

        public PersonalBean getPersonal() {
            return personal;
        }

        public void setPersonal(PersonalBean personal) {
            this.personal = personal;
        }

        public static class SystemMsgBean {
            /**
             * realName : null
             * account : sysadmin
             * systemID : 8a8a800b5e029817015e02c9106d0010
             */

            private String realName;
            private String account;
            private String systemID;
            private String identification;

            public String getIdentification() {
                return identification;
            }

            public void setIdentification(String identification) {
                this.identification = identification;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

            public String getSystemID() {
                return systemID;
            }

            public void setSystemID(String systemID) {
                this.systemID = systemID;
            }
        }

        public static class PersonalBean {
            /**
             * orgCode : null
             * realName : 管理员
             * mobilePhone : null
             * emaile : null
             * departName : null
             * officePhone : null
             */

            private String orgCode;
            private String realName;
            private String mobilePhone;
            private String emaile;
            private String departName;
            private String officePhone;
            private String personalId;

            public String getPersonalId() {
                return personalId;
            }

            public void setPersonalId(String personalId) {
                this.personalId = personalId;
            }

            public String getOrgCode() {
                return orgCode;
            }

            public void setOrgCode(String orgCode) {
                this.orgCode = orgCode;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }

            public String getMobilePhone() {
                return mobilePhone;
            }

            public void setMobilePhone(String mobilePhone) {
                this.mobilePhone = mobilePhone;
            }

            public String getEmaile() {
                return emaile;
            }

            public void setEmaile(String emaile) {
                this.emaile = emaile;
            }

            public String getDepartName() {
                return departName;
            }

            public void setDepartName(String departName) {
                this.departName = departName;
            }

            public String getOfficePhone() {
                return officePhone;
            }

            public void setOfficePhone(String officePhone) {
                this.officePhone = officePhone;
            }
        }
    }
}