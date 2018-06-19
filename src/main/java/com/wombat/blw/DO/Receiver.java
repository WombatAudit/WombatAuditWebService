package com.wombat.blw.DO;

import lombok.Data;

@Data
public class Receiver {

    private String listId;
    private Integer userId;
    private Integer isRead;

    public Receiver() {
    }

    public Receiver(String listId, Integer userId, Integer isRead) {
        this.listId = listId;
        this.userId = userId;
        this.isRead = isRead;
    }
}
