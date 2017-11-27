package com.szmz.entity.response;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/21 0021下午 3:37
 */

public class JZ_tj1 extends BaseResponse<JZ_tj1.ResultBean>{

    public class ResultBean{
        private String rescueCategoryName;
        private String disName;
        private int pernum;

        public String getDisName() {
            return disName;
        }

        public void setDisName(String disName) {
            this.disName = disName;
        }

        public String getRescueCategoryName() {
            return rescueCategoryName;
        }

        public void setRescueCategoryName(String rescueCategoryName) {
            this.rescueCategoryName = rescueCategoryName;
        }

        public int getPernum() {
            return pernum;
        }

        public void setPernum(int pernum) {
            this.pernum = pernum;
        }
    }
}
