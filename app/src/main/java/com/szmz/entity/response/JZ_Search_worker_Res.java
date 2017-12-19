package com.szmz.entity.response;

import com.szmz.entity.IEntity;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/7 0007下午 2:44
 */

public class JZ_Search_worker_Res extends BaseResponse<JZ_Search_worker_Res.ResultBean> {

    public class ResultBean implements IEntity {
        //"id":"af96bf1f7edd44bdaae4b88559f9b800","dealTime":1544803200000,"name":"杨幻巧","type":"城市低保";
        public String id;
        public String dealTime;
        public String name;
        public String type;


        public String sex;//	性别	String
        public String idcard;//身份证号	String
        public String address;//	地址	String
        public String salvationType;//	救助类型	String
        public String poorReason;//	主要贫困原因	String
        public String applyDate;//申请日期	String
        public String population;//家庭人口	String
        public String ensuremoney;//	救助金额	double

    }
}
