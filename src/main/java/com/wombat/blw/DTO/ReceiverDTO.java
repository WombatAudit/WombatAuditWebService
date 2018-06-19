package com.wombat.blw.DTO;

import lombok.Data;

@Data
public class ReceiverDTO {

    private Integer listId;
    private Integer userId;
    private Integer isRead;

    public ReceiverDTO() {
    }

    public ReceiverDTO(Integer listId, Integer userId, Integer isRead) {

        this.listId = listId;
        this.userId = userId;
        this.isRead = isRead;
    }
}
