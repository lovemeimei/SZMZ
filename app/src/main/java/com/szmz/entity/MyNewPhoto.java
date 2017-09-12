package com.szmz.entity;

import java.util.List;

/**
 * Created by bz on 2017/9/12.
 */

public class MyNewPhoto implements IEntity {

    public MyNewPhoto(String path) {
        this.FileUrl = path;
        this.isNetImage = false;
    }

    private double lng;
    private double lat;
    private String time;
    private String address;
    private String mark;
    private boolean isNetImage = true;

    public boolean isNetImage() {
        return isNetImage;
    }

    public void setNetImage(boolean isNetImage) {
        this.isNetImage = isNetImage;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    private List<MyNewPhoto> list;
    private static final long serialVersionUID = 1L;
    private String Id;
    private String CreateUid;
    private String CreateDate;
    private String HiddenDangerInfoId;
    private String FileType;
    private String FileUrl;
    private String ThumbnailFileUrl;
    private String FileName;
    private String UUID;
    private String Longitude;
    private String Latitude;
    private String UpdateUid;
    private String UpdateDate;
    private String DeleteUid;
    private String DeleteFlag;
    private String DeleteDate;
    private String Status;
    private String DeparmentName;
    private String RoleName;
    private String RealName;

    public List<MyNewPhoto> getList() {
        return list;
    }

    public void setList(List<MyNewPhoto> list) {
        this.list = list;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getCreateUid() {
        return CreateUid;
    }

    public void setCreateUid(String createUid) {
        CreateUid = createUid;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public String getHiddenDangerInfoId() {
        return HiddenDangerInfoId;
    }

    public void setHiddenDangerInfoId(String hiddenDangerInfoId) {
        HiddenDangerInfoId = hiddenDangerInfoId;
    }

    public String getFileType() {
        return FileType;
    }

    public void setFileType(String fileType) {
        FileType = fileType;
    }

    public String getFileUrl() {
        return FileUrl;
    }

    public void setFileUrl(String fileUrl) {
        FileUrl = fileUrl;
    }

    public String getThumbnailFileUrl() {
        return ThumbnailFileUrl;
    }

    public void setThumbnailFileUrl(String thumbnailFileUrl) {
        ThumbnailFileUrl = thumbnailFileUrl;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String uUID) {
        UUID = uUID;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getUpdateUid() {
        return UpdateUid;
    }

    public void setUpdateUid(String updateUid) {
        UpdateUid = updateUid;
    }

    public String getUpdateDate() {
        return UpdateDate;
    }

    public void setUpdateDate(String updateDate) {
        UpdateDate = updateDate;
    }

    public String getDeleteUid() {
        return DeleteUid;
    }

    public void setDeleteUid(String deleteUid) {
        DeleteUid = deleteUid;
    }

    public String getDeleteFlag() {
        return DeleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        DeleteFlag = deleteFlag;
    }

    public String getDeleteDate() {
        return DeleteDate;
    }

    public void setDeleteDate(String deleteDate) {
        DeleteDate = deleteDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getDeparmentName() {
        return DeparmentName;
    }

    public void setDeparmentName(String deparmentName) {
        DeparmentName = deparmentName;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

}

