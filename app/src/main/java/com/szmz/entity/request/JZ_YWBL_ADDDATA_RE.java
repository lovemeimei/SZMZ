package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * Created by bz on 2017/11/28.
 */

public class JZ_YWBL_ADDDATA_RE extends BaseListRequest {

    private String familyIds;//家庭ID
    private String dicId;//资料分类：
//            20203028:入户调查资料，
//            20203029：民主评议资料，
//            20203030：审核公示资料，
//            20203031：入户抽查资料，
//            20203032：审批公示资料

    private String pic;//照片（Base64编译后的字节码）
    private String address;//地址


    public JZ_YWBL_ADDDATA_RE(String familyIds, String dicId, String pic,
                              String address) {
        this.familyIds = familyIds;
        this.dicId = dicId;
        this.pic = pic;
        this.address = address;
        setMd5Key(Md5Util.getMd5(familyIds + dicId + pic + address));
    }
}
