package com.szmz.entity.response;

import java.util.List;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/25 0025下午 4:32
 */

public class LoginResponse {

    /**
     * result : [{"systemMsg":{"realName":null,"account":"admin","systemID":"8a8a800b5e029817015e02c96bba0012"}},{"personal":{"orgCode":null,"realName":"管理员","mobilePhone":null,"emaile":null,"departName":null,"officePhone":null}}]
     * error : {"ErrorMessage":"登录成功","ErrorCode":"0"}
     * totalNum : 1
     */

    private ErrorBean error;
    private String totalNum;
    private List<ResultBean> result;

    public ErrorBean getError() {
        return error;
    }

    public void setError(ErrorBean error) {
        this.error = error;
    }

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ErrorBean {
        /**
         * ErrorMessage : 登录成功
         * ErrorCode : 0
         */

        private String ErrorMessage;
        private String ErrorCode;

        public String getErrorMessage() {
            return ErrorMessage;
        }

        public void setErrorMessage(String ErrorMessage) {
            this.ErrorMessage = ErrorMessage;
        }

        public String getErrorCode() {
            return ErrorCode;
        }

        public void setErrorCode(String ErrorCode) {
            this.ErrorCode = ErrorCode;
        }
    }

    public static class ResultBean {
        /**
         * systemMsg : {"realName":null,"account":"admin","systemID":"8a8a800b5e029817015e02c96bba0012"}
         * personal : {"orgCode":null,"realName":"管理员","mobilePhone":null,"emaile":null,"departName":null,"officePhone":null}
         */

        private SystemMsgBean systemMsg;
        private PersonalBean personal;

        public SystemMsgBean getSystemMsg() {
            return systemMsg;
        }

        public void setSystemMsg(SystemMsgBean systemMsg) {
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
             * account : admin
             * systemID : 8a8a800b5e029817015e02c96bba0012
             */

            private Object realName;
            private String account;
            private String systemID;

            public Object getRealName() {
                return realName;
            }

            public void setRealName(Object realName) {
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

            private Object orgCode;
            private String realName;
            private Object mobilePhone;
            private Object emaile;
            private Object departName;
            private Object officePhone;

            public Object getOrgCode() {
                return orgCode;
            }

            public void setOrgCode(Object orgCode) {
                this.orgCode = orgCode;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }

            public Object getMobilePhone() {
                return mobilePhone;
            }

            public void setMobilePhone(Object mobilePhone) {
                this.mobilePhone = mobilePhone;
            }

            public Object getEmaile() {
                return emaile;
            }

            public void setEmaile(Object emaile) {
                this.emaile = emaile;
            }

            public Object getDepartName() {
                return departName;
            }

            public void setDepartName(Object departName) {
                this.departName = departName;
            }

            public Object getOfficePhone() {
                return officePhone;
            }

            public void setOfficePhone(Object officePhone) {
                this.officePhone = officePhone;
            }
        }
    }
}
