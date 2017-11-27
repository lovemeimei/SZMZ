package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/6 0006下午 2:00
 */

public class JZ_Login_Code_Req extends BaseRequest{

    private String uuid;
    private String account;
    private String SystemId;
    private String type;

    public JZ_Login_Code_Req(String uuid, String account, String systemId) {

        this.uuid = uuid;
        this.account = account;
        SystemId = systemId;
        type="A001";

        setMd5Key(Md5Util.getMd5(uuid+account+systemId));
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getSystemId() {
        return SystemId;
    }

    public void setSystemId(String systemId) {
        SystemId = systemId;
    }
}
