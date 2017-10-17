package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/10/17 0017上午 11:12
 */

public class JZ_Comm_modifyInfo extends BaseRequest{
    private String personalId;
    private String emaile;
    private String officePhone;

    public JZ_Comm_modifyInfo(String personalId, String emaile, String officePhone) {
        this.personalId = personalId;
        this.emaile = emaile;
        this.officePhone = officePhone;

        setMd5Key(Md5Util.getMd5(personalId+emaile+officePhone));
    }
}
