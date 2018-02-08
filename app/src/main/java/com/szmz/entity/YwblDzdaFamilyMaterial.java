package com.szmz.entity;

/**
 * 家庭资料信息
 * Created by bz on 2017/10/23.
 */

public class YwblDzdaFamilyMaterial implements IEntity {
    private String id;//	id
    private String materialType;//	资料类别
    private String filename;//文件名
    private String fileurl;//文件地址
    private String nodeCode;
    private String nodeName;

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }
}
