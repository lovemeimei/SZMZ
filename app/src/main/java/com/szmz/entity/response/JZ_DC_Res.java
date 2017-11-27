package com.szmz.entity.response;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/23 0023上午 9:45
 */

public class JZ_DC_Res extends BaseResponse<JZ_DC_Res.ResultBean>{

    public class ResultBean{
        private String countAll;
        private String gradeHousehold;
        private String countHousehold;
        private String gradeDemocratic;
        private String countDemocratic;
        private String gradeCheckPublic;
        private String countCheckPublic;
        private String gradeHouseholdRandom;
        private String countHouseholdRandom;
        private String gradeApprovalPublic;
        private String countApprovalPublic;


        public String getCountHousehold() {
            return countHousehold;
        }

        public void setCountHousehold(String countHousehold) {
            this.countHousehold = countHousehold;
        }

        public String getCountDemocratic() {
            return countDemocratic;
        }

        public void setCountDemocratic(String countDemocratic) {
            this.countDemocratic = countDemocratic;
        }

        public String getCountCheckPublic() {
            return countCheckPublic;
        }

        public void setCountCheckPublic(String countCheckPublic) {
            this.countCheckPublic = countCheckPublic;
        }

        public String getCountHouseholdRandom() {
            return countHouseholdRandom;
        }

        public void setCountHouseholdRandom(String countHouseholdRandom) {
            this.countHouseholdRandom = countHouseholdRandom;
        }

        public String getCountApprovalPublic() {
            return countApprovalPublic;
        }

        public void setCountApprovalPublic(String countApprovalPublic) {
            this.countApprovalPublic = countApprovalPublic;
        }

        public String getCountAll() {
            return countAll;
        }

        public void setCountAll(String countAll) {
            this.countAll = countAll;
        }

        public String getGradeHousehold() {
            return gradeHousehold;
        }

        public void setGradeHousehold(String gradeHousehold) {
            this.gradeHousehold = gradeHousehold;
        }

        public String getGradeDemocratic() {
            return gradeDemocratic;
        }

        public void setGradeDemocratic(String gradeDemocratic) {
            this.gradeDemocratic = gradeDemocratic;
        }

        public String getGradeCheckPublic() {
            return gradeCheckPublic;
        }

        public void setGradeCheckPublic(String gradeCheckPublic) {
            this.gradeCheckPublic = gradeCheckPublic;
        }

        public String getGradeHouseholdRandom() {
            return gradeHouseholdRandom;
        }

        public void setGradeHouseholdRandom(String gradeHouseholdRandom) {
            this.gradeHouseholdRandom = gradeHouseholdRandom;
        }

        public String getGradeApprovalPublic() {
            return gradeApprovalPublic;
        }

        public void setGradeApprovalPublic(String gradeApprovalPublic) {
            this.gradeApprovalPublic = gradeApprovalPublic;
        }
    }
}
