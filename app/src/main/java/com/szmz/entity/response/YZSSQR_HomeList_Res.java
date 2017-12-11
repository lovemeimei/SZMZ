package com.szmz.entity.response;

import com.szmz.entity.IEntity;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/12/6 0006下午 4:59
 */

public class YZSSQR_HomeList_Res extends BaseResponse<YZSSQR_HomeList_Res.ResultBean>{

    public class ResultBean implements IEntity{

        private String ID;
        private String NOTE_TITLE;
        private String NOTE_CONTENT;
        private String ADD_TIME;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getNOTE_TITLE() {
            return NOTE_TITLE;
        }

        public void setNOTE_TITLE(String NOTE_TITLE) {
            this.NOTE_TITLE = NOTE_TITLE;
        }

        public String getNOTE_CONTENT() {
            return NOTE_CONTENT;
        }

        public void setNOTE_CONTENT(String NOTE_CONTENT) {
            this.NOTE_CONTENT = NOTE_CONTENT;
        }

        public String getADD_TIME() {
            return ADD_TIME;
        }

        public void setADD_TIME(String ADD_TIME) {
            this.ADD_TIME = ADD_TIME;
        }
    }
}
