package com.wombat.blw.DO;

import lombok.Data;

import java.util.Date;

@Data
public class Assignment {

    private Integer userId;
    private Integer itemId;
    private Integer status;
    private Date startTime;

    public Assignment(Integer userId, Integer itemId) {
        this.userId = userId;
        this.itemId = itemId;
    }

    public Assignment() {
    }
}
