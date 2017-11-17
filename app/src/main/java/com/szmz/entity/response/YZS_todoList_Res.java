package com.szmz.entity.response;

import com.szmz.entity.IEntity;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/8 0008下午 3:14
 */

public class YZS_todoList_Res extends BaseResponse<YZS_todoList_Res.ResultBean>{

    public class ResultBean implements IEntity{

        private String Title;
        private String FlowName;
        private String NodeName;
        private String Lab;
        private String StarterName;
        private String RDT;
        private String ADT;
        private String SDT;

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public String getFlowName() {
            return FlowName;
        }

        public void setFlowName(String flowName) {
            FlowName = flowName;
        }

        public String getNodeName() {
            return NodeName;
        }

        public void setNodeName(String nodeName) {
            NodeName = nodeName;
        }

        public String getLab() {
            return Lab;
        }

        public void setLab(String lab) {
            Lab = lab;
        }

        public String getStarterName() {
            return StarterName;
        }

        public void setStarterName(String starterName) {
            StarterName = starterName;
        }

        public String getRDT() {
            return RDT;
        }

        public void setRDT(String RDT) {
            this.RDT = RDT;
        }

        public String getADT() {
            return ADT;
        }

        public void setADT(String ADT) {
            this.ADT = ADT;
        }

        public String getSDT() {
            return SDT;
        }

        public void setSDT(String SDT) {
            this.SDT = SDT;
        }
    }
}
