package com.szmz.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * 业务办理提交的数据保存本地类
 * Created by bz on 2017/11/7.
 */

@Table(name = "YwblSageData")
public class YwblSaveDataRequest implements IEntity {
    @Column(name = "id", isId = true)
    private String id;
    @Column(name = "jsonStr")
    private String jsonStr;
    @Column(name = "imageJsonStr")
    private String imageJsonStr;
    @Column(name = "type")
    private int type;//保存数据类型（1入户调查，2民主评议，3入户抽查，4审核公示，5审批公示）
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "userID")
    private String userID;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    public String getImageJsonStr() {
        return imageJsonStr;
    }

    public void setImageJsonStr(String imageJsonStr) {
        this.imageJsonStr = imageJsonStr;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
