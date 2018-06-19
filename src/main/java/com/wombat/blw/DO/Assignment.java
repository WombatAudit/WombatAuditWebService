package com.wombat.blw.DO;

import lombok.Data;

import java.util.Date;

@Data
public class Assignment {

    private Integer assignerId;
    private Integer assigneeId;
    private Integer itemId;
    private Integer status;
    private Date startTime;

    public Assignment() {
    }
}
