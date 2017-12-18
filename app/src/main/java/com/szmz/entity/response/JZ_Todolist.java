package com.szmz.entity.response;

import com.szmz.entity.IEntity;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/10/17 0017下午 3:34
 */

public class JZ_Todolist extends BaseResponse<JZ_Todolist.ResultBean> {

    public class ResultBean implements IEntity {


        /**
         * id : A0E91DA0AED944A6BDA9E300C3A636B7
         * taskId : 1037537
         * title : 水飞-532531198307072855-农村低保-家庭批量复核
         * description : 办理中
         * processDefinitionName : 家庭批量复核
         * taskCreateTime : 1513502186000
         * processDefinitionId :
         * batchReview:33:963046
         * taskName : 区县审批
         */

        private String id;
        private String taskId;
        private String title;
        private String description;
        private String processDefinitionName;
        private long taskCreateTime;
        private String processDefinitionId;
        private String taskName;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTaskId() {
            return taskId;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getProcessDefinitionName() {
            return processDefinitionName;
        }

        public void setProcessDefinitionName(String processDefinitionName) {
            this.processDefinitionName = processDefinitionName;
        }

        public long getTaskCreateTime() {
            return taskCreateTime;
        }

        public void setTaskCreateTime(long taskCreateTime) {
            this.taskCreateTime = taskCreateTime;
        }

        public String getProcessDefinitionId() {
            return processDefinitionId;
        }

        public void setProcessDefinitionId(String processDefinitionId) {
            this.processDefinitionId = processDefinitionId;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }
    }
}
