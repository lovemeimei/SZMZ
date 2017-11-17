package com.szmz.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by bz on 2017/11/7.
 */

@Table(name = "YwblSageData")
public class YwblSaveData implements IEntity {
    @Column(name = "id", isId = true)
    private String id;
    @Column(name = "jsonStr")
    private String jsonStr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }
}
