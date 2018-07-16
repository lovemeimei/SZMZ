package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

public class JZ_GetLocation_List_req extends BaseListRequest{

    public JZ_GetLocation_List_req(String userId, String dicId, String type, String userName, String keyWords,int currentPage) {
        this.userId = userId;
        this.dicId = dicId;
        this.type = type;
        this.userName = userName;
        this.keyWords = keyWords;
        CurrentPage = currentPage;

        setMd5Key(Md5Util.getMd5(userId+dicId+type+userName+keyWords+currentPage+PageSize));
    }

    private String userId;
    private String dicId;
    private String type;
    private String userName;
    private String keyWords;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDicId() {
        return dicId;
    }

    public void setDicId(String dicId) {
        this.dicId = dicId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }
}
