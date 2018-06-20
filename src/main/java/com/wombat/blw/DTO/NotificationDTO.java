package com.wombat.blw.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class NotificationDTO {

    private String content;
    private Date time;
    private String listId;

    public NotificationDTO() {
    }

    public NotificationDTO(String content, Date time, String listId) {
        this.content = content;
        this.time = time;
        this.listId = listId;
    }
}
