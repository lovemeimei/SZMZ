package com.szmz.entity.request;

import com.szmz.entity.IEntity;
import com.szmz.utils.Md5Util;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/7 0007下午 2:38
 */

public class JZ_Search_workerDetail_Req extends BaseRequest{

   private String id;
   private String userId;

    public JZ_Search_workerDetail_Req(String id, String userId) {
        this.id = id;
        this.userId = userId;

        setMd5Key( Md5Util.getMd5(id+userId));

    }
}
