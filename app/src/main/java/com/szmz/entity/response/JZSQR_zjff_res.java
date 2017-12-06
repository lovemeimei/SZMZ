package com.szmz.entity.response;

import com.szmz.entity.IEntity;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/12/1 0001下午 3:09
 */

public class JZSQR_zjff_res extends BaseResponse<JZSQR_zjff_res.ResultBean>{

    public class ResultBean implements IEntity{

        private String bpmBizTitle;
        private String dealTime;
        private String id;

        public String getBpmBizTitle() {
            return bpmBizTitle;
        }

        public void setBpmBizTitle(String bpmBizTitle) {
            this.bpmBizTitle = bpmBizTitle;
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
    }
}
