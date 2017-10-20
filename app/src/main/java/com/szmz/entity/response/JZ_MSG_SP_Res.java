package com.szmz.entity.response;

import com.szmz.entity.IEntity;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/10/18 0018上午 10:15
 */

public class JZ_MSG_SP_Res extends BaseResponse<JZ_MSG_SP_Res.ResultBean>{

    public class ResultBean implements IEntity{

        private String id;
        private String result;
        private String resultreason;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getResultreason() {
            return resultreason;
        }

        public void setResultreason(String resultreason) {
            this.resultreason = resultreason;
        }
    }
}
