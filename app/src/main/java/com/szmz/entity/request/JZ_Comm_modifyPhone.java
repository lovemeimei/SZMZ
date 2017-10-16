package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/10/16 0016下午 5:23
 */

public class JZ_Comm_modifyPhone extends BaseRequest{
    private String oldPhone;
    private String newPhone;
    private String checkCode;

    public JZ_Comm_modifyPhone(String oldPhone, String newPhone, String checkCode) {
        this.oldPhone = oldPhone;
        this.newPhone = newPhone;
        this.checkCode = checkCode;

        setMd5Key(Md5Util.getMd5(oldPhone+newPhone+checkCode));
    }
}
