package com.szmz.entity.response;

import com.szmz.entity.IEntity;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/30 0030上午 11:17
 */

public class HD_TJ_HDDX extends BaseResponse<HD_TJ_HDDX.ResultBean>{
    public class ResultBean implements IEntity{

        /**
         * areaName : 攀枝花
         * bizCategory : 城乡低保
         * checkObjectCount : 100
         */

        private String areaName;
        private String bizCategory;
        private int checkObjectCount;

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getBizCategory() {
            return bizCategory;
        }

        public void setBizCategory(String bizCategory) {
            this.bizCategory = bizCategory;
        }

        public int getCheckObjectCount() {
            return checkObjectCount;
        }

        public void setCheckObjectCount(int checkObjectCount) {
            this.checkObjectCount = checkObjectCount;
        }
    }
}
