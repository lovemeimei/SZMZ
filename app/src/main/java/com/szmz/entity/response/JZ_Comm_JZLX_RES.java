package com.szmz.entity.response;

import com.bigkoo.pickerview.model.IPickerViewData;
import com.szmz.entity.IEntity;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/7 0007上午 9:52
 */

public class JZ_Comm_JZLX_RES extends BaseResponse<JZ_Comm_JZLX_RES.ResultBean>{

    public class ResultBean implements IEntity,IPickerViewData {
        private String id;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public String getPickerViewText() {
            return type;
        }
    }
}
