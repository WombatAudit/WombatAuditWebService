package com.wombat.blw.DO;

import lombok.Data;

import java.util.Date;

@Data
public class Notification {

    private Integer id;
    private Integer senderId;
    private String receiveListId;
    private String content;
    private Date createdAt;

    public Notification(Integer id, Integer senderId, String receiveListId, String content, Date createdAt) {
        this.id = id;
        this.senderId = senderId;
        this.receiveListId = receiveListId;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Notification() {

    }
}
