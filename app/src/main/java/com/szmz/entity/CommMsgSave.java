package com.szmz.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/22 0022下午 3:31
 */

@Table(name = "commMsg")
public class CommMsgSave implements IEntity{

    @Column(name = "id",isId = true)
    private String id;
    @Column(name = "content")
    private String content;
    @Column(name = "title")
    private String title;
    @Column(name = "sender")
    private String sender;
    @Column(name = "type")
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
