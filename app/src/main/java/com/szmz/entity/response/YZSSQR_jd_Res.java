package com.szmz.entity.response;

import com.szmz.entity.IEntity;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/29 0029下午 3:21
 */

public class YZSSQR_jd_Res extends BaseResponse<YZSSQR_jd_Res.ResultBean>{

    public class ResultBean implements IEntity{
        private String NAME;
        private String type;
        private String typeName;
        private String FLOW_RESULT;
        private String FLOW_RESULT_NAME;
        private String ID;

        private String CardID;
        private String currentNode;
        private String allNode;

        public String getCardID() {
            return CardID;
        }

        public void setCardID(String cardID) {
            CardID = cardID;
        }

        public String getCurrentNode() {
            return currentNode;
        }

        public void setCurrentNode(String currentNode) {
            this.currentNode = currentNode;
        }

        public String getAllNode() {
            return allNode;
        }

        public void setAllNode(String allNode) {
            this.allNode = allNode;
        }

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getFLOW_RESULT() {
            return FLOW_RESULT;
        }

        public void setFLOW_RESULT(String FLOW_RESULT) {
            this.FLOW_RESULT = FLOW_RESULT;
        }

        public String getFLOW_RESULT_NAME() {
            return FLOW_RESULT_NAME;
        }

        public void setFLOW_RESULT_NAME(String FLOW_RESULT_NAME) {
            this.FLOW_RESULT_NAME = FLOW_RESULT_NAME;
        }

        public String getId() {
            return ID;
        }

        public void setId(String id) {
            this.ID = id;
        }


    }

}


