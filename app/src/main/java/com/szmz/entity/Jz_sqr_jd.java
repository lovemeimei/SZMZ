package com.szmz.entity;

/**
 * Created by bz on 2017/12/4.
 */

public class Jz_sqr_jd implements IEntity {
    private String id;//	家庭ID	string
    private String name;//姓名	string
    private String typeName;//;//	救助类别	string
    private String bpmStatus;//流程状态	string

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getBpmStatus() {
        return bpmStatus;
    }

    public void setBpmStatus(String bpmStatus) {
        this.bpmStatus = bpmStatus;
    }
}
