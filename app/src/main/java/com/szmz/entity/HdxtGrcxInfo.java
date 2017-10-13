package com.szmz.entity;

import java.util.List;

/**
 * Created by bz on 2017/9/27.
 */

public class HdxtGrcxInfo implements IEntity {
    private String batchId;
    private String applyId;
    private String applyName;
    private String idCardNo;
    private String bizCategory;
    private String entrustTime;
    private String currentNode;
    private List<TotalNodeBean> totalNode;
    private String filePath;
    private boolean isDownLoading = false;
    private long reference;

    public boolean isDownLoading() {
        return isDownLoading;
    }

    public void setDownLoading(boolean downLoading) {
        isDownLoading = downLoading;
    }

    public long getReference() {
        return reference;
    }

    public void setReference(long reference) {
        this.reference = reference;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * sex : 女
     */

    private String sex;

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public static class TotalNodeBean implements IEntity {
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
