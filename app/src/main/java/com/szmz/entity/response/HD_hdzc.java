package com.szmz.entity.response;

import com.szmz.entity.IEntity;

/**
 * @author qieyixuan
 * @created at 2017年10月08
 */

public class HD_hdzc extends BaseResponse<HD_hdzc.ResultBean>{

    public class ResultBean implements IEntity{

        /**
         * fileTitle : 核对资料
         * filePath : http://127.0.0.1:21/file
         */

        private String fileTitle;
        private String filePath;

        public String getFileTitle() {
            return fileTitle;
        }

        public void setFileTitle(String fileTitle) {
            this.fileTitle = fileTitle;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }
    }
}
