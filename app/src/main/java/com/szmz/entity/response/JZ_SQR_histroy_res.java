package com.szmz.entity.response;

import android.content.Intent;

import com.szmz.entity.IEntity;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/12/1 0001下午 2:59
 */

public class JZ_SQR_histroy_res extends BaseResponse<JZ_SQR_histroy_res.ResultBean>{

    public class ResultBean implements IEntity{

        private String name;
        private String type;
        private String dealTime;
        private String id;

        private String sex;
        private String idcard;
        private String address;
        private String salvationType;
        private String poorReason;
        private String applyDate;
        private String population;
        private String ensuremoney;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDealTime() {
            return dealTime;
        }

        public void setDealTime(String dealTime) {
            this.dealTime = dealTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getSalvationType() {
            return salvationType;
        }

        public void setSalvationType(String salvationType) {
            this.salvationType = salvationType;
        }

        public String getPoorReason() {
            return poorReason;
        }

        public void setPoorReason(String poorReason) {
            this.poorReason = poorReason;
        }

        public String getApplyDate() {
            return applyDate;
        }

        public void setApplyDate(String applyDate) {
            this.applyDate = applyDate;
        }

        public String getPopulation() {
            return population;
        }

        public void setPopulation(String population) {
            this.population = population;
        }

        public String getEnsuremoney() {
            return ensuremoney;
        }

        public void setEnsuremoney(String ensuremoney) {
            this.ensuremoney = ensuremoney;
        }
    }
}
