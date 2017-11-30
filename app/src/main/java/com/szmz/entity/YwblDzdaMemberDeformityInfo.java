package com.szmz.entity;

/**
 * Created by bz on 2017/11/23.
 */

public class YwblDzdaMemberDeformityInfo implements IEntity {
    private String deformityId;//	成员残疾Id
    private String deformityTypeName;//	成员残疾类型
    private String deformityLevelName;//	成员残疾等级

    public String getDeformityId() {
        return deformityId;
    }

    public void setDeformityId(String deformityId) {
        this.deformityId = deformityId;
    }

    public String getDeformityTypeName() {
        return deformityTypeName;
    }

    public void setDeformityTypeName(String deformityTypeName) {
        this.deformityTypeName = deformityTypeName;
    }

    public String getDeformityLevelName() {
        return deformityLevelName;
    }

    public void setDeformityLevelName(String deformityLevelName) {
        this.deformityLevelName = deformityLevelName;
    }
}
