package com.szmz.entity.response;

import com.szmz.entity.IEntity;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/10/17 0017下午 4:05
 */

public class JZ_Todo_MenuTree extends BaseResponse<JZ_Todo_MenuTree.ResultBean>{

    public class ResultBean implements IEntity{

        private String id;
        private String functionname;
        private String parentfunctionid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFunctionname() {
            return functionname;
        }

        public void setFunctionname(String functionname) {
            this.functionname = functionname;
        }

        public String getParentfunctionid() {
            return parentfunctionid;
        }

        public void setParentfunctionid(String parentfunctionid) {
            this.parentfunctionid = parentfunctionid;
        }
    }
}
