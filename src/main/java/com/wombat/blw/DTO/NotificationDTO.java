package com.wombat.blw.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class NotificationDTO {

    private Integer id;
    private Integer senderId;
    private Integer receiveListId;
    private String content;
    private Date createdAt;


    public NotificationDTO(Integer id, Integer sender_id, Integer receiveList_id, String content, Date createdAt) {
        this.id = id;
        this.senderId = sender_id;
        this.receiveListId = receiveList_id;
        this.content = content;
        this.createdAt = createdAt;
    }

    public NotificationDTO() {

    }
}
