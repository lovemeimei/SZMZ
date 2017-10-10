package com.szmz.entity;

import java.util.List;

/**
 * Created by bz on 2017/9/30.
 */

public class HD_SQR_GRCX_JDCK implements IEntity {


    /**
     * batchId : 10000000001
     * applyId : 10000001
     * applyName : 张三
     * idCardNo : 610324
     * bizCategory : 城乡低保
     * entrustTime : 2017-09-11
     * currentNode : 待审核
     * totalNode : [{"node":"待审核"},{"node":"待审批"}]
     */

    private String batchId;
    private String applyId;
    private String applyName;
    private String idCardNo;
    private String bizCategory;
    private String entrustTime;
    private String currentNode;
    private List<TotalNodeBean> totalNode;

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getBizCategory() {
        return bizCategory;
    }

    public void setBizCategory(String bizCategory) {
        this.bizCategory = bizCategory;
    }

    public String getEntrustTime() {
        return entrustTime;
    }

    public void setEntrustTime(String entrustTime) {
        this.entrustTime = entrustTime;
    }

    public String getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(String currentNode) {
        this.currentNode = currentNode;
    }

    public List<TotalNodeBean> getTotalNode() {
        return totalNode;
    }

    public void setTotalNode(List<TotalNodeBean> totalNode) {
        this.totalNode = totalNode;
    }

    public static class TotalNodeBean {
        /**
         * node : 待审核
         */

        private String node;

        public String getNode() {
            return node;
        }

        public void setNode(String node) {
            this.node = node;
        }
    }
}
